package ru.job4j.chat_rest_api.service.person;

import org.springframework.stereotype.Service;
import ru.job4j.chat_rest_api.domian.Person;
import ru.job4j.chat_rest_api.repository.PersonRepository;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Person> findAllPersons() {
        return (List<Person>) repository.findAll();
    }

    @Override
    public Person findPersonById(int id) {
        return repository.findById(id).orElse(new Person());
    }

    @Override
    public Person savePerson(Person person) {
        return repository.save(person);
    }

    @Override
    public void deletePerson(Person person) {
        repository.delete(person);
    }
}
