package ru.job4j.chat_rest_api.service.person;

import ru.job4j.chat_rest_api.domian.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAllPersons();

    Person findPersonById(int id);

    Person savePerson(Person person);

    void deletePerson(Person person);
}
