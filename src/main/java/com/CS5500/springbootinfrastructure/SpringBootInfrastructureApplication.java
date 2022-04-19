package com.CS5500.springbootinfrastructure;

import com.CS5500.springbootinfrastructure.dao.DateLog;
import com.CS5500.springbootinfrastructure.parser.Jsonparser;
import com.CS5500.springbootinfrastructure.repos.DateLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.io.IOException;

/**
 * Uncomment and run the saveToRemote function to populate the database with the
 * first 11 entries
 */
@Configuration
@EnableJpaRepositories
@SpringBootApplication
public class SpringBootInfrastructureApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootInfrastructureApplication.class);



	public static void main(String[] args) throws IOException {
		SpringApplication.run(SpringBootInfrastructureApplication.class, args);
	}

	@Bean
	public CommandLineRunner saveToRemote(DateLogRepository dlRepo) {
		return (args) -> {

			/*DateLog[] dates = Jsonparser.getPOJO();
			int count = 0;
			for (DateLog log : dates) {
				if (count > 5)
					break;
				dlRepo.save(log);
				count++;
			}

			log.info("Save ended");*/
		};
	}
}
