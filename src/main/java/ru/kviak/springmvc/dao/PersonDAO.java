package ru.kviak.springmvc.dao;

import org.springframework.stereotype.Component;
import ru.kviak.springmvc.models.Person;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {

    private static final String URL="jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME="postgres";
    private static final String PASSWORD="postgres";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index(){
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));

                people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    public Person show(int id){
                return null;
//        return people.stream()
//                .filter(person -> person.getId() == id)
//                .findAny()
//                .orElse(null);
    }

    public void save(Person person){
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);

        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Person VALUES(" + 1 + ",'" + person.getName() + "'" +
            ",'" + person.getEmail() + "'," + person.getAge() + ")"; // TERRIBLE APPROACH!

            statement.executeUpdate(SQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void update(int id, Person updatePerson){
        Person personToBeUpdated = show(id);
        personToBeUpdated.setAge(updatePerson.getAge());
        personToBeUpdated.setEmail(updatePerson.getEmail());
        personToBeUpdated.setName(updatePerson.getName());
    }

    public void delete(int id){
//        people.removeIf(person -> person.getId() == id);
    }
}
