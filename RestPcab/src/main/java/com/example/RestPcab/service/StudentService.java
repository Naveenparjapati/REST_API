package com.example.RestPcab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.RestPcab.dto.StudentDTO;
import com.example.RestPcab.entity.Student;
import com.example.RestPcab.repository.StudentRepository;

@Service
public class StudentService {

	
	@Autowired
	StudentRepository repository;
	
	public Object saveStusent(StudentDTO dto) {
		// TODO Auto-generated method stub
		if(!repository.existsByMobile(dto.getMobile()))
		{
			Student student=new Student(dto);
			repository.save(student);
			return student;
		}else {
			 return "A student with this mobile number already exists.";
		}
		
	}

	
	public List<Student> saveStudents(List<StudentDTO> dtos) {
        List<Student> savedStudents = new ArrayList<>();
        
        for (StudentDTO dto : dtos) {
            if (repository.existsByMobile(dto.getMobile())) {
                throw new IllegalArgumentException("Student with mobile " + dto.getMobile() + " already exists");
            }
            savedStudents.add(repository.save(new Student(dto)));
        }
        
        return savedStudents;
    }


	public Student getStudentById(Long id) {
        return repository.findById(id).orElse(null); // Fetch student by ID, return null if not found
    }


	public String deletByid(long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
		return "del succ fu";
	}


	public Student updatestudent(long id, Student updatedStude) {
		// TODO Auto-generated method stub
		 return repository.findById(id).map(student -> {
	            student.setName(updatedStude.getName());
	            //student.setEmail(updatedStudent.getEmail());
	            // Update other fields as necessary
	            return repository.save(student); // Save the updated student
	        }).orElse(null); // Return null if the student is not found
		
	}


	
		
		public List<Student> fetchAll(String sort, boolean desc, int page, int data) {
			
			Sort sortBasedOn = Sort.by(sort);
			if (desc)
				sortBasedOn = sortBasedOn.descending();
			
			Pageable pageable = PageRequest.of(page - 1, data, sortBasedOn);
			
			Page<Student> records = repository.findAll(pageable);
			
			if(!records.isEmpty())
				return records.getContent();
			else
				return null;
		}



}
