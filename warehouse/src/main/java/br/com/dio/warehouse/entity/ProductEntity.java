package br.com.dio.warehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.*;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Setter
@Getter
@ToString
public class ProductEntity {

    @Id
    private UUID id;
    private String name;
    private BigDecimal price;

    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = ALL, orphanRemoval = true)
    private Set<StockEntity> stocks = new HashSet<>();

    @PrePersist
    private void prePersiste(){
        this.id = UUID.randomUUID();
    }

    public StockEntity decStock(){
        var stock = stocks.stream()
                .filter(s-> s.getStatus().equals(StockStatus.AVAILABLE))
                .min(Comparator.comparing(StockEntity::getSoldPrice))
                .orElseThrow();

        stock.decAmount();
        return stock;
    }

    public BigDecimal getPrice(){
        return stocks.stream()
                .filter(s-> s.getStatus().equals(StockStatus.AVAILABLE))
                .min(Comparator.comparing(StockEntity::getSoldPrice))
                .orElseThrow().getSoldPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProductEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
