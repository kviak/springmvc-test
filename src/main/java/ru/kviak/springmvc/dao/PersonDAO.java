package ru.kviak.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.kviak.springmvc.models.Person;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person",
                        new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                            .stream()
                            .findAny()
                            .orElse(null); // Хотя бы один объект или NULL.
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person VALUES (1,?,?,?)",
                person.getName(), person.getEmail(), person.getAge());
    }
    public void update(int id, Person updatePerson){
        jdbcTemplate.update("UPDATE Person SET name=?, email=?, age=? WHERE  id=?",
                updatePerson.getName(), updatePerson.getEmail(), updatePerson.getAge(), updatePerson.getId());
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
