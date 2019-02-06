package com.in28minutes.jdbc.databasedemo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jdbc.databasedemo.entity.Person;
import com.in28minutes.jdbc.databasedemo.springdata.PersonSpringDataRepository;

@SpringBootApplication
public class SpringDataDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PersonSpringDataRepository personJpaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataDemoApplication.class, args);
	}

	@Override 
	public void run(String... args) throws Exception {
		logger.info("User id 10001 -> {}", personJpaRepository.findById(10001));
		logger.info("Inserting 10004 -> {}", personJpaRepository.save(new Person("Maria", "Amsterdam", new Date())));
		logger.info("Updating 10003 -> {}", personJpaRepository.save(new Person(10003, "Pieter Jonh", "Amsterdam", new Date())));
		personJpaRepository.deleteById(10002);	
		logger.info("All persons -> {}", personJpaRepository.findAll());
	}

}

