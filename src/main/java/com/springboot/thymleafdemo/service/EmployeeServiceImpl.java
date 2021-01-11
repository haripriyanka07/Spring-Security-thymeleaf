package com.springboot.thymleafdemo.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.thymleafdemo.dao.EmployeeRepository;
import com.springboot.thymleafdemo.entity.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository empRepo;
	
	public EmployeeServiceImpl(EmployeeRepository emp) {
		empRepo = emp;
	}
	
	@Override
	public List<Employee> findAll() {
		return empRepo.findAllByOrderByLastNameAsc();
	}

	@Override
	public Employee getEmployee(int id) {
		
		Optional<Employee> result = empRepo.findById(id);
	
		
		Employee emp = null;
		
		if(result.isPresent()) {
			emp = result.get();
		}
		else {
			throw new RuntimeException("Not found");
		}
		return emp;
	}

	@Override
	public void addEmployee(Employee emp) {
		empRepo.save(emp);
	}

	@Override
	public void deleteEmployee(int id) {
		empRepo.deleteById(id);
	}


}
