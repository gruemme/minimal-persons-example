package com.example.spring_boot.persons.controller;

import com.example.spring_boot.persons.model.Person;
import com.example.spring_boot.persons.repository.PersonRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/** This is the person endpoint to interact with the person resource. */
@RestController
@RequestMapping("/persons")
@AllArgsConstructor
public class PersonController {
  private PersonRepository personRepository;

  /**
   * Returns all persons that are stored in the database.
   *
   * @return person list.
   */
  @GetMapping
  public List<Person> getAllPersons() {
    return personRepository.findAll();
  }

  /**
   * Creates a new person entry.
   *
   * @param person
   * @return the newly created person.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Person createPerson(@RequestBody Person person) {
    return personRepository.save(person);
  }

  /**
   * Gets the data of a single person.
   *
   * @param id ID of en existing person.
   */
  @GetMapping("/{id}")
  public Person getPerson(@PathVariable("id") Long id) {
    return personRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  /**
   * Deletes a person.
   *
   * @param id ID of en existing person.
   */
  @DeleteMapping("/{id}")
  @Transactional
  public void deletePerson(@PathVariable("id") Long id) {
    if (personRepository.removeById(id) == 0) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Updates an existing person. Note: All properties will be overwritten.
   *
   * @param id ID of en existing person.
   * @param personFromRequest New properties values for the person to update.
   * @return the updated created person.
   */
  @PutMapping("/{id}")
  public Person updatePerson(@PathVariable("id") Long id, @RequestBody Person personFromRequest) {
    Optional<Person> optionalPersonFromDb = personRepository.findById(id);
    Person personFromDb =
        optionalPersonFromDb.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    copyAllPropertiesExceptId(personFromRequest, personFromDb);
    personRepository.save(personFromDb);

    return personFromDb;
  }

  private void copyAllPropertiesExceptId(Person source, Person destination) {
    destination.setName(source.getName());
    destination.setHeight(source.getHeight());
    destination.setWeight(source.getWeight());
    destination.setSex(source.getSex());
    destination.setDateOfBirth(source.getDateOfBirth());
    destination.setPicture(source.getPicture());
  }
}
