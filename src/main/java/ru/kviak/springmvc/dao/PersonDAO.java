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
    public Optional<Person> show(String email){
        return Optional.empty();
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


//    public void testMultipleUpdate(){
//        List<Person> people = create1000people();
//
//        long before = System.currentTimeMillis();
//
//        for (Person person: people){
//            jdbcTemplate.update("INSERT INTO Person(name, email, age) VALUES (?,?,?)",
//                    person.getName(), person.getEmail(), person.getAge());
//        }
//
//        long after = System.currentTimeMillis();
//
//        System.out.println(after-before);
//    }
//
//    public void testBatchUpdate(){
//        List<Person> people = create1000people();
//        long before = System.currentTimeMillis();
//
//        jdbcTemplate.batchUpdate("INSERT INTO Person(name, email, age) VALUES (?,?,?)",
//                new BatchPreparedStatementSetter() {
//                    @Override
//                    public void setValues(PreparedStatement ps, int i) throws SQLException {
//                        ps.setString(1, people.get(i).getName());
//                        ps.setString(2, people.get(i).getEmail());
//                        ps.setInt(3, people.get(i).getAge());
//                    }
//
//                    @Override
//                    public int getBatchSize() {
//                        return people.size();
//                    }
//                });
//
//        long after = System.currentTimeMillis();
//        System.out.println(after-before);
//    }
//
//    private List<Person> create1000people() {
//        List<Person> people = new ArrayList<>();
//
//        for (int i = 0; i < 1000; i++) {
//            people.add(new Person(i, i+"a", i+10, i+"asd"+"@.mail.ru"));
//        }
//        return people;
//    }
}
