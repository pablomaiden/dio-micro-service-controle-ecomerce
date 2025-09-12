import java.util.Date;

plugins {
	java
	id("org.springframework.boot") version "3.4.10-SNAPSHOT"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "br.com.dio"
version = "0.0.1-SNAPSHOT"
description = "Micro Service Store Front"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/snapshot") }
}

var mapStructVersion = "1.6.3"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("org.mapstruct:mapstruct:$mapStructVersion")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.4")


	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")

	annotationProcessor("org.projectlombok:lombok")
	annotationProcessor("org.mapstruct:mapstruct-processor:$mapStructVersion")
	annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.amqp:spring-rabbit-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named("build") {
	doLast {
		val trigger = file("src/main/resources/trigger.txt")
		if (!trigger.exists()) {
			trigger.createNewFile()
		}
		trigger.writeText(Date().toString())
	}
}

//tasks.named<JavaExec>("bootRun"){
//	jvmArgs = listOf("Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=*:5005")
//}

tasks.withType<Test> {
	useJUnitPlatform()
}
