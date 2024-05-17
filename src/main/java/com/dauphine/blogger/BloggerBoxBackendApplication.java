package com.dauphine.blogger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@OpenAPIDefinition(
    info = @Info(
        title = "Blogger box backend",
        description = "Blogger box endpoints and APIs",
        contact = @Contact(name = "Minh", email = "binh-minh.nguyen@dauphine.eu"),
        version = "1.0.0"
    )
)
@EnableJpaRepositories("com.dauphine.blogger.repositories")
public class BloggerBoxBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(BloggerBoxBackendApplication.class, args);
  }

}
