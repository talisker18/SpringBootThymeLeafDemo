package com.henz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.henz.entity.Employee;
import com.henz.repository.EmployeeRepository;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping({"/showEmployees","/","/list"}) //multiple paths
	public ModelAndView showEmployees() {
		ModelAndView mv = new ModelAndView("list-employees");
		List<Employee> list = employeeRepository.findAll();
		mv.addObject("employees",list);
		return mv;
	}
	
	@GetMapping("/addEmployeeForm")
	public ModelAndView addEmployeeForm() {
		ModelAndView mv = new ModelAndView("add-employee-form");
		Employee emp = new Employee(); //add employee object already here so we can POST it directly to /saveEmployee
		mv.addObject("employee",emp);
		return mv;
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute Employee employee) {
		this.employeeRepository.save(employee);
		return "redirect:/showEmployees";
	}
	
	/*
	 * this method returns the same template as the /addEmployeeForm path
	 * on this template we have the 'save' button. when we click on that button, a new employee
	 * will be created and not updated. to make it an update instead of creation of new employee, we have to
	 * add a hidden input field with the employee id on the form in add-employee-form.
	 * then when we hit 'save', this employee id will be sent to /saveEmployee API.
	 * if the @ModelAttribute Employee employee has an employee id, which is true in the case of an update, Jpa repo will use the save()
	 * method to update the existing data record. If there is no id (when we are creating new employee), Jpa's save() method will create
	 * new data record
	 * 
	 * IMPORTANT: to make the update working, we also need a setter method of the employee id. but we dont need a args constructor
	 * 
	 * */
	@GetMapping("/showUpdateForm")
	public ModelAndView showUpdateForm(@RequestParam Long employeeId) {
		ModelAndView mv = new ModelAndView("add-employee-form");
		Employee emp = this.employeeRepository.findById(employeeId).get(); //findById returns optional. With get(), we get the Employee object
		mv.addObject("employee", emp); //we use same template as when adding new employee
		return mv;
	}
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam Long employeeId) {
		this.employeeRepository.deleteById(employeeId);
		return "redirect:/showEmployees";
	}

}
