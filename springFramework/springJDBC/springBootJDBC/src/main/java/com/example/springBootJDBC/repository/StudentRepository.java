package com.example.springBootJDBC.repository;

import com.example.springBootJDBC.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Student student) {
        String sql = "insert into student (rollno, name, marks) values (?,?,?)";
        int rows = jdbcTemplate.update(sql,
                student.getRollNo(), student.getName(), student.getMarks());
        System.out.println(rows + " rows affected");

    }

//    public List<Student> findAll() {
//        String sql = "select * from student";
//        RowMapper<Student> rowMapper = new RowMapper<Student>() {
//            @Override
//            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Student student = new Student();
//                student.setRollNo(rs.getInt("rollno")); // using columnLabel here
//                student.setName(rs.getString(2)); // using columnIndex here
//                student.setMarks(rs.getInt("marks")); // using columnLabel here
//                return student;
//            }
//        };
//        return jdbcTemplate.query(sql,rowMapper);
//    }

    // Lambda Version
    public List<Student> findAll() {
        String sql = "select * from student";

        return jdbcTemplate.query(sql,(rs,rowNum)->{
            Student student = new Student();
            student.setRollNo(rs.getInt("rollno")); // using columnLabel here
            student.setName(rs.getString(2)); // using columnIndex here
            student.setMarks(rs.getInt("marks")); // using columnLabel here
            return student;
        });
    }
}
