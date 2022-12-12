package com.dh.movie;

import com.dh.movie.model.Movie;
import com.dh.movie.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
@EnableEurekaClient
public class ApiMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiMovieApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(MovieRepository repository) {
        String baseUrl = "www.netflix.com/movies";

        return (args) -> {
            if (!repository.findAll().isEmpty()) {
                return;
            }
            repository.save(new Movie("Película 1", "terror", baseUrl + "/terror/1"));
            repository.save(new Movie("Película 2", "terror", baseUrl + "/terror/2"));
            repository.save(new Movie("Película 3", "comedia", baseUrl + "/comedia/1"));
            repository.save(new Movie("Película 4", "ficcion", baseUrl + "/ficcion/1"));
            repository.save(new Movie("Película 5", "documental", baseUrl + "/documental/1"));
        };
    }

}
