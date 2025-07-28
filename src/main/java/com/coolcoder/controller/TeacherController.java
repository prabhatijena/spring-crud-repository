package com.coolcoder.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coolcoder.entity.Teacher;
import com.coolcoder.repository.TeacherRepository;
//import com.coolcoder1.entity.Product;

@RestController
@RequestMapping("/login")
public class TeacherController {
	@Autowired
	private TeacherRepository repository;
	@PostMapping("/post")
	public ResponseEntity<Object> create(@RequestBody Teacher teacher){
		repository.save(teacher);
		return ResponseEntity.status(HttpStatus.CREATED).body("Teacher inserted Successfully... ");
	}
	@GetMapping("/get")
	public ResponseEntity<Object> getAllTeachers(){
		//repository.findAll();
		return ResponseEntity.status(HttpStatus.FOUND).body(repository.findAll());
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> update(@PathVariable Integer id,@RequestBody Teacher teacher){
		long startTime = System.currentTimeMillis();
		Optional<Teacher> teah = repository.findById(id);
		if(teah.isPresent()) {
			Teacher existingTeacher = teah.get();
			existingTeacher.setName(teacher.getName());
			existingTeacher.setSalary(teacher.getSalary());
			repository.save(existingTeacher);
			long endTime = System.currentTimeMillis();
			System.out.println("Time taken= "+(endTime-startTime)+"ms");
		    return ResponseEntity.status(HttpStatus.OK).body("Teacher updated successfully for id:" +id);
		    
		}else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not for id:" +id);
		}
			
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable Integer id){
		return ResponseEntity.status(HttpStatus.OK).body("Teacher deleted successfully for id:"+id);
	}
}
