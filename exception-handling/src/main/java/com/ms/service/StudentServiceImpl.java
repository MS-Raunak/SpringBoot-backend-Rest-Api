package com.ms.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.dto.StudentDto;
import com.ms.exception.ResourceNotFoundException;
import com.ms.model.Student;
import com.ms.repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentRepo studentRepo;
	@Autowired
	ModelMapper mapper;

	@Override
	public StudentDto saveStudent(StudentDto studentDto) {
		Student student = mapper.map(studentDto, Student.class);
		Student savedStudent = studentRepo.save(student);
		
		StudentDto saveStudentDto = mapper.map(savedStudent, StudentDto.class);
		
		return saveStudentDto;
	}

	@Override
	public StudentDto getStudnStudentById(Integer id) {
		Optional<Student> byId = studentRepo.findById(id);
		
		if (!byId.isPresent()) {
			throw new ResourceNotFoundException("User", "Id", id);
		}
		
		
		Student student = byId.get();
		StudentDto studentDto = mapper.map(student, StudentDto.class);
		return studentDto;
	}

}
