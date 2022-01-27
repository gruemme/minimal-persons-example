package com.example.spring_boot.persons.repository;

import com.example.spring_boot.persons.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
  Integer removeById(Long id);
}
