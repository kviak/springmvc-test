package ru.kviak.springmvc.dao;

import org.springframework.stereotype.Component;
import ru.kviak.springmvc.models.Person;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private final List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Denis"));
        people.add(new Person(++PEOPLE_COUNT, "Jame"));
        people.add(new Person(++PEOPLE_COUNT, "Tom"));
    }
    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream()
                .filter(person -> person.getId() == id)
                .findAny()
                .orElse(null);
    }

    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
    public void update(int id, Person updatePerson){
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatePerson.getName());
    }

    public void delete(int id){
        people.removeIf(person -> person.getId() == id);
    }
}
