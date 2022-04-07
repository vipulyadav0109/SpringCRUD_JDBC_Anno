package com.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.model.Person;

@Repository
//@Qualifier("personDao")
public class PersonDaoImpl implements PersonDao {

  @Autowired
  JdbcTemplate jdbcTemplate;
  
  /**
  CREATE TABLE  person (
  person_id serial PRIMARY KEY,
  first_name varchar(45)  NOT NULL,
  last_name varchar(45)  NOT NULL,
  age integer NOT NULl 
) ;  */

  public void addPerson(Person person) {
      jdbcTemplate.update("INSERT INTO person ( first_name, Last_name, age) VALUES ( ?, ?, ?)",
           person.getFirstName(), person.getLastName(), person.getAge());
      System.out.println("Person Added!!");
  }

	  public void editPerson(Person person, int personId) {
	        jdbcTemplate.update("UPDATE person SET first_name = ? , last_name = ? , age = ? WHERE person_id = ? ",
	            person.getFirstName(), person.getLastName(), person.getAge(), personId);
	        System.out.println("Person Updated!!");
	    }
	 
	    public void deletePerson(int personId) {
	        jdbcTemplate.update("DELETE from person WHERE person_id = ? ", personId);
	        System.out.println("Person Deleted!!");
	    }
	 
	    public Person find(int personId) {
	        Person person = (Person) jdbcTemplate.queryForObject("SELECT * FROM person where person_id = ? ",
	            new Object[] { personId }, new BeanPropertyRowMapper(Person.class));
	 
	        return person;
	    }
	 
	    public List < Person > findAll() {
	        List < Person > persons = jdbcTemplate.query("select * from person", new BeanPropertyRowMapper(Person.class));
	        return persons;
	    }
	}