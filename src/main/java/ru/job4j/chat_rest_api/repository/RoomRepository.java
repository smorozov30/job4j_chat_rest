package ru.job4j.chat_rest_api.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.chat_rest_api.domian.Room;

public interface RoomRepository extends CrudRepository<Room, Integer> {
}
