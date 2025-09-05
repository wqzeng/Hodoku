package com.hodoku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hodoku", "solver", "sudoku"})
public class HodokuApplication {
    public static void main(String[] args) {
        SpringApplication.run(HodokuApplication.class, args);
    }
}