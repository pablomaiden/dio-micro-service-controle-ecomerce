package br.com.dio.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Setter
@Getter
@ToString
public class StockEntity {

    @Id
    private UUID id;

    private Long amount;

    private BigDecimal soldPrice;

    private BigDecimal boughtPrice;

    @Enumerated(EnumType.STRING)
    private StockStatus status;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @PrePersist
    private void prePersiste(){
        this.id = UUID.randomUUID();
    }

    public void decAmount(){
        this.amount -= 1;
        if(this.amount == 0){
            this.status = StockStatus.UNAVAILABLE;
        }
    }

    public boolean isUnavailable(){
        return status == StockStatus.UNAVAILABLE;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StockEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(amount, that.amount) && Objects.equals(soldPrice, that.soldPrice) && Objects.equals(boughtPrice, that.boughtPrice) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, soldPrice, boughtPrice, status);
    }
}
