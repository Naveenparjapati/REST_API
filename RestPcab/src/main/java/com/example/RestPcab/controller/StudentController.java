package com.example.RestPcab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.RestPcab.dto.StudentDTO;
import com.example.RestPcab.entity.Student;
import com.example.RestPcab.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
	
	@Autowired
	StudentService service;
	
	//save the rec
	@PostMapping("/student")
	@Operation(summary = "Save Record")
    public ResponseEntity<Object>  saveStusent(@RequestBody StudentDTO dto)
    {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.saveStusent(dto));
    }
	
	@PostMapping("/students")
	@Operation(summary = "Save multiple students")
	public List<Student> saveStudents(@RequestBody List<StudentDTO> dtos) {
	    return service.saveStudents(dtos);
	}
	
	
	//fetch by id
	@GetMapping("student/{id}")
	@Operation(summary = "Fetch a student by ID")
	public ResponseEntity<Student> fetchbyid(@PathVariable Long id)
	{
		Student student=service.getStudentById(id);
		if(student !=null)
		{
			return ResponseEntity.status(HttpStatus.OK).body(student);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
     
	//fetch all records
	@GetMapping("/students")
	@Operation(summary = "Fetch all records")
	public ResponseEntity<List<Student>>  FetchAll(
			@RequestParam(defaultValue = "id") String sort,@RequestParam(defaultValue = "false") boolean desc,
			 @RequestParam(defaultValue = "1") int page,
			 @RequestParam(defaultValue = "10") int data){
		return ResponseEntity.status(HttpStatus.OK).body(service.fetchAll(sort,desc,page,data));
				
	}
	
	@DeleteMapping("/student/{id}")
	@Operation(summary = "Delete by Id")
	public ResponseEntity<String> deletByid(@PathVariable long id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.deletByid(id)); 
	}
	

	@PostMapping("/student/{id}")
	@Operation(summary = "update student by id")
	public ResponseEntity<Student> updatestudent(@PathVariable long id,@RequestBody Student updatedStude)
	{
		Student stu=service.updatestudent(id,updatedStude);
		if (stu != null) {
	        return ResponseEntity.status(HttpStatus.OK).body(stu);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
		
	}
}
