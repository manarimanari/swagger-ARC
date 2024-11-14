package com.example.student_management;

import com.example.student_management.entity.Student;
import com.example.student_management.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class StudentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}



		@Bean
		public CommandLineRunner start(StudentRepository studentRepository) {
			return args -> {
				// Generate 5 random students with unique names
				for (int i = 0; i < 5; i++) {
					studentRepository.save(new Student( "Nom" + i, "Prénom" + i, new Date()));
				}

				// Print all saved students
				studentRepository.findAll().forEach(System.out::println);
			};
		}
	}

