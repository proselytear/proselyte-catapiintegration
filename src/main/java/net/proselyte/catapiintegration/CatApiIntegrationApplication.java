package net.proselyte.catapiintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "net.proselyte.catapiintegration.api")
@SpringBootApplication
public class CatApiIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatApiIntegrationApplication.class, args);
	}

}
