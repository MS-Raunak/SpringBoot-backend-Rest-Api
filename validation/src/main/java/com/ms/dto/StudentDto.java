package com.ms.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentDto {
	private Integer id;

	@NotEmpty(message = "name must not be empty") //@NotNull+@NotBlank -> NotNull accepts space but NotBlank can't accept
	@Size(min = 3, message = "name lenght must be more thant 3 chars")
	private String name;
	
	@NotNull(message = "age must not be null")
	private Integer age;
	
	@NotEmpty
	@Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
	private String email;
}
