package at.htlstp.dwh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"at.htlstp.dwh.repository"})
@EntityScan(basePackages = {"at.htlstp.dwh.model"})
@SpringBootApplication
public class DWHApplication {

	public static void main(String[] args) {
		SpringApplication.run(DWHApplication.class, args);
	}

}
