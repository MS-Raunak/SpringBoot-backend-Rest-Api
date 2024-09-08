package com.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{

}
