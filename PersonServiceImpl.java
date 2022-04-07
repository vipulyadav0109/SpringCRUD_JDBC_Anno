package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.PersonDao;
import com.demo.model.Person;

@Service("personService")
public class PersonServiceImpl implements PersonService {

   @Autowired
   PersonDao personDao;

   public void addPerson(Person person) {
       personDao.addPerson(person);

   }
   public void editPerson(Person person, int personId) {
       personDao.editPerson(person, personId);
   }

   public void deletePerson(int personId) {
       personDao.deletePerson(personId);
   }

   public Person find(int personId) {
       return personDao.find(personId);
   }

   public List < Person > findAll() {
       return personDao.findAll();
   }
}