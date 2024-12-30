package com.sahil.carts;

import com.sahil.carts.dto.CartContactInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {CartContactInfoDto.class})
@EnableFeignClients
@EnableTransactionManagement
public class CartsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartsApplication.class, args);
	}

}
