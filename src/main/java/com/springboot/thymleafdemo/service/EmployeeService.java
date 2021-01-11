package com.springboot.thymleafdemo.service;

import java.util.List;

import com.springboot.thymleafdemo.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();
	
	public Employee getEmployee(int id);
	
	public void addEmployee(Employee emp);
	
	public void deleteEmployee(int id);
	
}
