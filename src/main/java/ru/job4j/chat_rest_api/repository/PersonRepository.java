package ru.job4j.chat_rest_api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.chat_rest_api.domian.Person;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Override
    @Query("SELECT p FROM Person p JOIN FETCH p.role")
    Iterable<Person> findAll();

    @Override
    @Query("SELECT p FROM Person p JOIN FETCH p.role WHERE p.id = :id")
    Optional<Person> findById(@Param("id") Integer integer);
}
