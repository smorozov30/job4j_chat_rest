package ru.job4j.chat_rest_api.service.message;

import org.springframework.stereotype.Service;
import ru.job4j.chat_rest_api.domian.Message;
import ru.job4j.chat_rest_api.domian.Person;
import ru.job4j.chat_rest_api.domian.Room;
import ru.job4j.chat_rest_api.repository.MessageRepository;
import ru.job4j.chat_rest_api.repository.PersonRepository;
import ru.job4j.chat_rest_api.repository.RoomRepository;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final RoomRepository roomRepository;
    private final PersonRepository personRepository;
    private final MessageRepository messageRepository;

    public MessageServiceImpl(RoomRepository roomRepository, PersonRepository personRepository, MessageRepository messageRepository) {
        this.roomRepository = roomRepository;
        this.personRepository = personRepository;
        this.messageRepository = messageRepository;
    }


    @Override
    public List<Message> findMessagesByRoomId(int roomId) {
        return (List<Message>) messageRepository.findByRoomId(roomId);
    }

    @Override
    public List<Message> findMessagesByPersonId(int personId) {
        return (List<Message>) messageRepository.findByPersonId(personId);
    }

    @Override
    public Message findMessageById(int id) {
        return messageRepository.findById(id).orElse(new Message());
    }

    @Override
    public Room findRoomById(int roomId) {
        return roomRepository.findById(roomId).orElse(new Room());
    }

    @Override
    public Person findPersonById(int personId) {
        return personRepository.findById(personId).orElse(new Person());
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void deleteMessage(Message message) {
        messageRepository.delete(message);
    }
}
