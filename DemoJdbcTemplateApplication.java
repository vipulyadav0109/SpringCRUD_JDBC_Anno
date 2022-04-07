package com.demo;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.yaml.snakeyaml.nodes.ScalarNode;

import com.demo.model.Person;
import com.demo.service.PersonService;
import com.demo.service.PersonServiceImpl;

@SpringBootApplication
public class DemoJdbcTemplateApplication implements CommandLineRunner, ApplicationContextAware {
	public static void main(String[] args) {
		SpringApplication.run(DemoJdbcTemplateApplication.class, args);

	}

	private ApplicationContext context;
	
	private PersonService personService;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
		personService = (PersonService) context.getBean(PersonServiceImpl.class);

	}

	@Override
	public void run(String... args) throws Exception {
		// savePerson();
		// getByID();
		// deleteByID();
		// updateByID();
		getAll();
	}

	private void savePerson() {

		String[] name = { "vipul", "Rahul", "Amit", "Ram", "Akshay", "Pratik", "Amar" };
		String[] lastname = { "Yadav", "Pawar", "Kapoor", "Jagtap", "Roy", "Sapkal", "Kale" };
		int age[] = { 23, 44, 56, 17, 22, 18, 52 };

		for (int i = 0; i < name.length; i++) {

			Person person = new Person(name[i], lastname[i], age[i]);

			personService.addPerson(person);

		}
		System.out.println("Find All");
		List<Person> persons = personService.findAll();
		for (Person person : persons) {
			System.out.println(person);
		}
	}

	private void getByID() {
		System.out.println("Enter Id For Fetch Record :");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();

		System.out.println(personService.find(id));
	}

	private void deleteByID() {
		System.out.println("Enter Id For Delete Record :");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();

		personService.deletePerson(id);

	}

	private void updateByID() {
		List<Person> persons = personService.findAll();
		for (Person person : persons) {
			System.out.println(person);
		}

		System.out.println("Enter Id For Update Record :");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();

		System.out.println(personService.find(id));

		System.out.println("Enter Details To Update : Name , Last Name, age -");
		System.out.println("Enter Name :");
		String name = sc.next();
		System.out.println("Enter Last Name :");
		String last_name = sc.next();
		System.out.println("Enter age :");
		int age = sc.nextInt();

		Person person = new Person(name, last_name, age);

		personService.editPerson(person, id);
		System.out.println(personService.find(id));

	}

	private void getAll() {

		System.out.println("All Records.........");
		List<Person> persons = personService.findAll();
		for (Person person : persons) {
			System.out.println(person);
		}
	}

}