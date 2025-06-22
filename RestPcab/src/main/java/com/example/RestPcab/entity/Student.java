package com.example.RestPcab.entity;

import com.example.RestPcab.dto.StudentDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Student {
	
	public Student(StudentDTO dto)
	{
		this.name=dto.getName();
		this.mobile=dto.getMobile();
		this.maths=dto.getMaths();
		this.science=dto.getScience();
		this.english=dto.getEnglish();
		this.percentage=getPercentage();
	}
	
	@Id
	@GeneratedValue(generator = "id")
	@SequenceGenerator(initialValue = 101001, allocationSize = 1 , name = "id")
    private Long id;
	private String name;
	private Long mobile;
	private Integer maths;
	private Integer science;
	private Integer english;
	private Double percentage;

}
