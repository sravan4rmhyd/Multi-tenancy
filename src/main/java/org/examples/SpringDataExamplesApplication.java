package org.examples;

import org.examples.domain.Employee;
import org.examples.domain.Employee_Address;
import org.examples.repository.EmployeeAdressRepository;
import org.examples.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataExamplesApplication implements CommandLineRunner{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeAdressRepository adressRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataExamplesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Employee_Address address = new Employee_Address();
		address.setCity("hyderabad");
		address.setCountry("india");
		address.setState("talangana");
		address.setStreet("kukatpally");
		Employee employee = new Employee();
		employee.setEmpName("sravan");
		employeeRepository.createSchema("public", "test");
		SchemaContext.setSchema("test");
		employee = employeeRepository.save(employee);
		address = adressRepository.save(address);
		address.setEmployee(employee);
		adressRepository.save(address);
		System.out.println(employee);
	}

}
