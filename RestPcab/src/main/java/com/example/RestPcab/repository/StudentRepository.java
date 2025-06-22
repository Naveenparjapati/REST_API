package com.example.RestPcab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.RestPcab.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	boolean existsByMobile(Long mobile);

}
