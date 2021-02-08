package ru.job4j.chat_rest_api.service.message;

import ru.job4j.chat_rest_api.domian.Message;
import ru.job4j.chat_rest_api.domian.Person;
import ru.job4j.chat_rest_api.domian.Room;

import java.util.List;

public interface MessageService {
    List<Message> findMessagesByRoomId(int roomId);

    List<Message> findMessagesByPersonId(int personId);

    Message findMessageById(int id);

    Room findRoomById(int roomId);

    Person findPersonById(int personId);

    Message saveMessage(Message message);

    void deleteMessage(Message message);
}
