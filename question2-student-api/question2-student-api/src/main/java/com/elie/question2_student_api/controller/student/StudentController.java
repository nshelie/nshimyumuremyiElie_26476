package com.elie.question2_student_api.controller.student;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elie.question2_student_api.model.student.Student;

@RestController
@RequestMapping("/api/students")
public class StudentController {


    private List<Student> students = new ArrayList<>();

    // Initialize sample students
    public StudentController() {

        students.add(new Student(1L, "Elie", "Nshimye", "elie@example.com", "Computer Science", 3.8));
        students.add(new Student(2L, "Elisa", "Ngonga", "elisa@example.com", "Information Systems", 3.2));
        students.add(new Student(3L, "Nadia", "Uwase", "nadia@example.com", "Computer Science", 3.6));
        students.add(new Student(4L, "Peace", "Juilenne", "peace@example.com", "Networking", 3.0));
        students.add(new Student(5L, "Joselyne", "Uwimpuhwe", "joselyne@example.com", "Computer Science", 3.9));
    }

    // GET all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(students);
    }

    // GET student by ID
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {

        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return ResponseEntity.ok(student);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // GET students by major
    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(@PathVariable String major) {

        List<Student> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getMajor().equalsIgnoreCase(major)) {
                result.add(student);
            }
        }

        return ResponseEntity.ok(result);
    }

    // FILTER by GPA >= minGpa
    @GetMapping("/filter")
    public ResponseEntity<List<Student>> filterByGpa(@RequestParam Double gpa) {

        List<Student> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getGpa() >= gpa) {
                result.add(student);
            }
        }

        return ResponseEntity.ok(result);
    }

    // POST new student
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {

        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    // PUT update student
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId,
                                                 @RequestBody Student updatedStudent) {

        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {

                student.setFirstName(updatedStudent.getFirstName());
                student.setLastName(updatedStudent.getLastName());
                student.setEmail(updatedStudent.getEmail());
                student.setMajor(updatedStudent.getMajor());
                student.setGpa(updatedStudent.getGpa());

                return ResponseEntity.ok(student);
            }
        }

        return ResponseEntity.notFound().build();
    }

}
