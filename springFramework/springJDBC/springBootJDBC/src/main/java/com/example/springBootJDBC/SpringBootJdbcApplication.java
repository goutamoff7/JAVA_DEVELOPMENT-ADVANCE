package com.example.springBootJDBC;

import com.example.springBootJDBC.model.Student;
import com.example.springBootJDBC.model.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringBootJdbcApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootJdbcApplication.class, args);
		Student student = context.getBean(Student.class);
		student.setName("Bapi Mandal");
		student.setRollNo(17);
		student.setMarks(84);
		System.out.println(student);

		StudentService studentService = context.getBean(StudentService.class);
		studentService.addStudent(student);

		List<Student> students =  studentService.getStudents();
		students.forEach(System.out::println);
	}


}
