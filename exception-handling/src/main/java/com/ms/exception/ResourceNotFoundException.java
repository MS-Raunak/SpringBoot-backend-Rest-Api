package com.ms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
	String resourseName;  //Object name in which exception might be raised
	String fieldName;     // particular variable name which is responsible to throw exception(ex:id)
	Integer fieldValue;  // the value of that variable due to which exception throw(ex: id:121 and 121 is not exist in db)
	
	public ResourceNotFoundException(String resourseName, String fieldName, Integer fieldValue) {
		super(resourseName + " not found with " + fieldName + ": " + fieldValue);
		
		this.resourseName = resourseName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
}
