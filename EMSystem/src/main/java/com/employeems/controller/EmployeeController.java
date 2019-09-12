package com.employeems.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employeems.exception.ResourceNotFoundException;
import com.employeems.model.Employee;
import com.employeems.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository EmpRepo;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return EmpRepo.findAll();
	}
	
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable(value="id") Long empId) throws ResourceNotFoundException{
		
		Employee employee = EmpRepo.findById(empId).orElseThrow(()-> new ResourceNotFoundException("Employee" + empId + "not found"));				
		return ResponseEntity.ok().body(employee);
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@Valid @RequestBody Employee employee) {
		return EmpRepo.save(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee newEmployee, @PathVariable(value="id") Long empId ) throws ResourceNotFoundException {
		Employee employee = EmpRepo.findById(empId).orElseThrow(()-> new ResourceNotFoundException("Employee " + empId + "not found" ));
		employee.setName(newEmployee.getName());
		employee.setAddress(newEmployee.getAddress());
		employee.setPosition(newEmployee.getPosition());
		final Employee updatedEmployee = EmpRepo.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value="id") Long empId) throws ResourceNotFoundException{
		Employee employee = EmpRepo.findById(empId).orElseThrow(()-> new ResourceNotFoundException("Employee not found in ID: " + empId));
		EmpRepo.deleteById(empId);
		Map<String,Boolean> responce= new HashMap<>();
		responce.put("Deleted", true);
		return responce;
		
	}
}

