package com.ms.service;

import com.ms.dto.StudentDto;

public interface StudentService {
	public StudentDto saveStudent(StudentDto studentDto);
	public StudentDto getStudnStudentById(Integer id);
}
