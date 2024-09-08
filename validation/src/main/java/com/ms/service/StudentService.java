package com.ms.service;

import com.ms.dto.StudentDto;
import java.util.List;


public interface StudentService {
	public List<StudentDto> getAllStudents();
	public StudentDto saveStudent(StudentDto studentDto);
	public StudentDto getStudnStudentById(Integer id);
	public StudentDto updateStudentById(StudentDto studentDto, Integer id);
}
