package ru.job4j.chat_rest_api.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat_rest_api.domian.Person;
import ru.job4j.chat_rest_api.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/users")
public class UserController {

    private PersonRepository users;
    private BCryptPasswordEncoder encoder;

    public UserController(PersonRepository users,
                          BCryptPasswordEncoder encoder) {
        this.users = users;
        this.encoder = encoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        users.save(person);
    }

    @GetMapping("/all")
    public List<Person> findAll() {
        return StreamSupport.stream(
                users.findAll().spliterator(), false
        ).collect(Collectors.toList());
    }
}
