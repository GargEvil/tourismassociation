package com.tourism.tourismassociation;

import com.tourism.tourismassociation.model.User;
import com.tourism.tourismassociation.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TourismAssociationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TourismAssociationApplication.class, args);
    }

}
