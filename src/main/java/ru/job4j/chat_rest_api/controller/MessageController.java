package ru.job4j.chat_rest_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat_rest_api.domian.Message;
import ru.job4j.chat_rest_api.domian.Person;
import ru.job4j.chat_rest_api.domian.Room;
import ru.job4j.chat_rest_api.service.message.MessageService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping("room/{roomId}/message/")
    public List<Message> findByRoomId(@PathVariable int roomId) {
        return new ArrayList<>(service.findMessagesByRoomId(roomId));
    }

    @GetMapping("person/{personId}/message/")
    public List<Message> findByPersonId(@PathVariable int personId) {
        return new ArrayList<>(service.findMessagesByPersonId(personId));
    }

    @GetMapping("message/{id}")
    public ResponseEntity<Message> findById(@PathVariable int id) {
        var message = service.findMessageById(id);
        return new ResponseEntity<Message>(
                message,
                message.getId() != 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("room/{roomId}/person/{personId}/message/")
    public ResponseEntity<Message> create(@PathVariable int roomId,
                                          @PathVariable int personId,
                                          @RequestBody Message message) {
        Room room = service.findRoomById(roomId);
        Person person = service.findPersonById(personId);
        if (room.getId() == 0 || person.getId() == 0) {
            return ResponseEntity.notFound().build();
        }
        message.setRoom(room);
        message.setAuthor(person);
        return new ResponseEntity<Message>(
                service.saveMessage(message),
                HttpStatus.CREATED
        );
    }

    @PutMapping("message/")
    public ResponseEntity<Void> update(@RequestBody Message message) {
        Message temp = service.findMessageById(message.getId());
        if (temp.getId() == 0) {
            return ResponseEntity.notFound().build();
        }
        temp.setText(message.getText());
        service.saveMessage(temp);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("message/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Message message = new Message();
        message.setId(id);
        service.deleteMessage(message);
        return ResponseEntity.ok().build();
    }
}
