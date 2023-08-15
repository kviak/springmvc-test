package ru.kviak.springmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kviak.springmvc.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {}
