package ru.kviak.springmvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kviak.springmvc.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional(readOnly = true)
    public List<Person> index(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person p", Person.class)
                .getResultList();
    }

    @Transactional
    public Person show(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }
    @Transactional
    public Optional<Person> show(String email){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select p from Person p where p.email ="+"'"+email + "'", Person.class)
                .getResultList()
                .stream().findAny();
    }
    @Transactional
    public void save(Person person){
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }
    @Transactional
    public void update(int id, Person updatePerson){
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        person.setEmail(updatePerson.getEmail());
        person.setName(updatePerson.getName());
        person.setAge(updatePerson.getAge());
    }
    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }
}
