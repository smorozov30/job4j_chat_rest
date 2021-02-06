package ru.job4j.chat_rest_api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.chat_rest_api.domian.Message;

import java.util.Optional;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    @Override
    @Query("SELECT DISTINCT m FROM Message m JOIN FETCH m.room r JOIN FETCH m.author a JOIN FETCH a.role WHERE m.id = :id")
    Optional<Message> findById(Integer id);

    @Query("SELECT DISTINCT m FROM Message m JOIN FETCH m.room r JOIN FETCH m.author a JOIN FETCH a.role WHERE r.id = :roomId")
    Iterable<Message> findByRoomId(@Param("roomId") int id);

    @Query("SELECT DISTINCT m FROM Message m JOIN FETCH m.room r JOIN FETCH m.author a JOIN FETCH a.role WHERE a.id = :personId")
    Iterable<Message> findByPersonId(@Param("personId") int id);
}
