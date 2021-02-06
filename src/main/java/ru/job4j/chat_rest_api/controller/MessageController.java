package ru.job4j.chat_rest_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat_rest_api.domian.Message;
import ru.job4j.chat_rest_api.domian.Person;
import ru.job4j.chat_rest_api.domian.Room;
import ru.job4j.chat_rest_api.repository.MessageRepository;
import ru.job4j.chat_rest_api.repository.PersonRepository;
import ru.job4j.chat_rest_api.repository.RoomRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/")
public class MessageController {

    private final RoomRepository roomRepository;
    private final PersonRepository personRepository;
    private final MessageRepository messageRepository;

    public MessageController(RoomRepository roomRepository, PersonRepository personRepository, MessageRepository messageRepository) {
        this.roomRepository = roomRepository;
        this.personRepository = personRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping("room/{roomId}/message/")
    public List<Message> findByRoomId(@PathVariable int roomId) {
        return StreamSupport.stream(
                messageRepository.findByRoomId(roomId).spliterator(), false
        ).collect(Collectors.toList());
    }

    @GetMapping("person/{personId}/message/")
    public List<Message> findByPersonId(@PathVariable int personId) {
        return StreamSupport.stream(
                messageRepository.findByPersonId(personId).spliterator(), false
        ).collect(Collectors.toList());
    }

    @GetMapping("message/{id}")
    public ResponseEntity<Message> findById(@PathVariable int id) {
        var person = messageRepository.findById(id);
        return new ResponseEntity<Message>(
                person.orElse(new Message()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("room/{roomId}/person/{personId}/message/")
    public ResponseEntity<Message> create(@PathVariable int roomId,
                                          @PathVariable int personId,
                                          @RequestBody Message message) {
        Room room = roomRepository.findById(roomId).orElse(new Room());
        Person person = personRepository.findById(personId).orElse(new Person());
        if (room.getId() == 0 || person.getId() == 0) {
            return ResponseEntity.notFound().build();
        }
        message.setRoom(room);
        message.setAuthor(person);
        return new ResponseEntity<Message>(
                messageRepository.save(message),
                HttpStatus.CREATED
        );
    }

    @PutMapping("message/")
    public ResponseEntity<Void> update(@RequestBody Message message) {
        Message temp = messageRepository.findById(message.getId()).orElse(new Message());
        if (temp.getId() == 0) {
            return ResponseEntity.notFound().build();
        }
        temp.setText(message.getText());
        messageRepository.save(temp);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("message/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Message message = new Message();
        message.setId(id);
        messageRepository.delete(message);
        return ResponseEntity.ok().build();
    }
}
