package com.example.student_management;

import com.example.student_management.controller.StudentController;
import com.example.student_management.service.StudentService;
import com.example.student_management.entity.Student;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    void testSaveStudent() {
        // Créer un étudiant fictif
        Student student = new Student();
        student.setId(1);
        student.setNom("Mido");

        // Simuler la sauvegarde de l'étudiant par le service
        when(studentService.save(any(Student.class))).thenReturn(student);

        // Appeler la méthode save du contrôleur pour sauvegarder l'étudiant
        ResponseEntity<Student> response = studentController.save(student);

        // Vérifier que la réponse est correcte
        assertEquals(HttpStatus.CREATED, response.getStatusCode()); // Vérifie si le code de statut est 201 (Créé)
        assertEquals("Mido", response.getBody().getNom()); // Vérifie si le nom de l'étudiant sauvegardé est correct
    }

    @Test
    void testDeleteStudent() {
        // Simuler la suppression d'un étudiant par le service
        when(studentService.delete(1)).thenReturn(true);

        // Appeler la méthode delete du contrôleur pour supprimer l'étudiant
        ResponseEntity<?> response = studentController.delete(1);
        // Vérifier que la réponse est correcte
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()); // Vérifie si le code de statut est 204 (Aucun contenu)
    }

    @Test
    void testFindAllStudents() {
        // Créer des étudiants fictifs
        Student student1 = new Student();
        Student student2 = new Student();

        // Simuler la récupération de tous les étudiants par le service
        when(studentService.findAll()).thenReturn(Arrays.asList(student1, student2));

        // Appeler la méthode findAll du contrôleur pour récupérer tous les étudiants
        ResponseEntity<List<Student>> response = studentController.findAll();

        // Vérifier que la réponse est correcte
        assertEquals(2, response.getBody().size()); // Vérifie si la liste contient 2 étudiants
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Vérifie si le code de statut est 200 (OK)
    }

    @Test
    void testCountStudents() {
        // Simuler le comptage des étudiants par le service
        when(studentService.countStudents()).thenReturn(10L);

        // Appeler la méthode countStudents du contrôleur pour compter les étudiants
        ResponseEntity<Long> response = studentController.countStudents();

        // Vérifier que le nombre retourné est correct et que le statut HTTP est 200 (OK)
        assertEquals(10L, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testFindByYear() {
        // Simuler la récupération des étudiants par année par le service
        when(studentService.findNbrStudentByYear()).thenReturn(Arrays.asList());

        // Appeler la méthode findByYear du contrôleur pour récupérer les étudiants par année
        ResponseEntity<Collection<?>> response = studentController.findByYear();

        // Vérifier que la collection retournée est vide et que le statut HTTP est 200 (OK)
        assertEquals(0, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}