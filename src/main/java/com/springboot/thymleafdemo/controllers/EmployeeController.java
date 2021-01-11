package com.springboot.thymleafdemo.controllers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.thymleafdemo.entity.Employee;
import com.springboot.thymleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	private EmployeeService empservice;
	
	public EmployeeController(EmployeeService empser) {
		empservice = empser;
	}
	
	@GetMapping("/list")
	public String emplList(Model model) {
		
		List<Employee> emplist = empservice.findAll();
		logger.info(">>>>>>>>>>>>>>>>>>> "+emplist);
//		System.out.println(">>>>>>>>>> "+emplist);
		
		
		 
//		JSONObject json = new JSONObject();
		
		model.addAttribute("employees", emplist);
		
		return "employees/list";
	}
	
	@GetMapping("/showFormForAdd")
	public String ShowFormForAdd(Model model) {
		Employee emp = new Employee();
		
		model.addAttribute("employee", emp);
		
		return "employees/employee-form";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee emp) {
		
		empservice.addEmployee(emp);
		
		return "redirect:/employees/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String ShowFormForUpdate(@RequestParam("employeeId") int id, Model model) throws JSONException {
		
		Employee emp = empservice.getEmployee(id);
		
		model.addAttribute("employee", emp);
		
		String prod = emp.getField();
		
		JSONObject json = new JSONObject(prod);
		
		Iterator<String> hm = json.keys();
		
		
		while(hm.hasNext()) {
			System.out.println(">>>> "+hm.next());
		}
		System.out.println(">>>> "+json.getString("Products"));
		
//		JSONObject jsonObj = new JSONObject(
//		         "{" +
//		            "Name : Adithya," +
//		            "Age : 22, " +
//		            "Salary: 10000.00, " +
//		            "IsSelfEmployee: false " +
//		         "}"
//		      );
//		
//		System.out.println(">>>>>>>>>>>>>>>>>>>  "+ jsonObj.getString("Name"));
		
		return "employees/employee-form";
	}
	
	@GetMapping("delete")
	public String delete(@RequestParam("employeeId") int id) {
		empservice.deleteEmployee(id);
		return "redirect:/employees/list";
	}
	
//	@GetMapping("/lists")
//	public Iterable<Employee> list(){
//		return empservice.findAll();
//	}
}
