package com.henz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.henz.entity.Employee;
import com.henz.repository.EmployeeRepository;

@SpringBootApplication
public class SpringBootThymeLeafDemoApplication implements CommandLineRunner{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootThymeLeafDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Employee emp = new Employee();
		emp.setName("joel");
		emp.setDepartment("it");
		emp.setEmail("joel");
		this.employeeRepository.save(emp);
	}

}
