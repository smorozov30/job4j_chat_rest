package ru.job4j.chat_rest_api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.job4j.chat_rest_api.ChatRestApiApplication;
import ru.job4j.chat_rest_api.domian.Message;
import ru.job4j.chat_rest_api.domian.Person;
import ru.job4j.chat_rest_api.domian.Role;
import ru.job4j.chat_rest_api.domian.Room;
import ru.job4j.chat_rest_api.repository.MessageRepository;
import ru.job4j.chat_rest_api.repository.PersonRepository;
import ru.job4j.chat_rest_api.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ChatRestApiApplication.class)
@AutoConfigureMockMvc
class MessageControllerTest {

    @MockBean
    private MessageRepository messageRepository;
    @MockBean
    private RoomRepository roomRepository;
    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenFindAllByRoomIdThenReturnListOfMessageInJSON() throws Exception {
        Message message = Message.of("message");
        message.setCreated(null);
        Room room = Room.of("room");
        Person author = Person.of("name", "lastname");
        Role role = Role.of("ROLE_ADMIN");
        author.setRole(role);
        message.setRoom(room);
        message.setAuthor(author);

        when(messageRepository.findByRoomId(anyInt())).thenReturn(List.of(message));

        MvcResult mvcResult = this.mockMvc.perform(get("/room/0/message/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String expected = "[{\"id\":0," +
                            "\"text\":\"message\"," +
                            "\"created\":null," +
                            "\"room\":{" +
                                        "\"id\":0," +
                                        "\"name\":\"room\"" +
                                      "}," +
                            "\"author\":{" +
                                        "\"id\":0," +
                                        "\"name\":\"name\"," +
                                        "\"lastname\":\"lastname\"," +
                                        "\"role\":{" +
                                                  "\"id\":0," +
                                                  "\"name\":\"ROLE_ADMIN\"" +
                                                 "}" +
                                        "}" +
                            "}]";
        assertThat(mvcResult.getResponse().getContentAsString(), is(expected));
    }

    @Test
    void whenFindAllByPersonIdThenReturnListOfMessageInJSON() throws Exception {
        Message message = Message.of("message");
        message.setCreated(null);
        Room room = Room.of("room");
        Person author = Person.of("name", "lastname");
        Role role = Role.of("ROLE_ADMIN");
        author.setRole(role);
        message.setRoom(room);
        message.setAuthor(author);

        when(messageRepository.findByPersonId(anyInt())).thenReturn(List.of(message));

        MvcResult mvcResult = this.mockMvc.perform(get("/person/0/message/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String expected = "[{\"id\":0," +
                            "\"text\":\"message\"," +
                            "\"created\":null," +
                            "\"room\":{" +
                                        "\"id\":0," +
                                        "\"name\":\"room\"" +
                                    "}," +
                            "\"author\":{" +
                                        "\"id\":0," +
                                        "\"name\":\"name\"," +
                                        "\"lastname\":\"lastname\"," +
                                        "\"role\":{" +
                                                "\"id\":0," +
                                                "\"name\":\"ROLE_ADMIN\"" +
                                            "}" +
                                    "}" +
                        "}]";
        assertThat(mvcResult.getResponse().getContentAsString(), is(expected));
    }

    @Test
    void whenCreateNewMessageThenReturnSavedMessageAndStatusCreated() throws Exception {
        Message message = Message.of("message");
        message.setCreated(null);
        Room room = Room.of("room");
        room.setId(1);
        Person author = Person.of("name", "lastname");
        author.setId(1);
        Role role = Role.of("ROLE_ADMIN");
        author.setRole(role);
        message.setAuthor(author);
        message.setRoom(room);

        when(roomRepository.findById(anyInt())).thenReturn(Optional.of(room));
        when(personRepository.findById(anyInt())).thenReturn(Optional.of(author));
        when(messageRepository.save(any())).thenReturn(message);

        String data = "{\"text\":\"text\"}";
        MvcResult mvcResult = this.mockMvc.perform(post("/room/1/person/1/message/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(data))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        String expected = "{\"id\":0," +
                            "\"text\":\"message\"," +
                            "\"created\":null," +
                                "\"room\":{" +
                                        "\"id\":1," +
                                        "\"name\":\"room\"" +
                                    "}," +
                                "\"author\":{" +
                                        "\"id\":1," +
                                        "\"name\":\"name\"," +
                                        "\"lastname\":\"lastname\"," +
                                        "\"role\":{" +
                                                    "\"id\":0," +
                                                    "\"name\":\"ROLE_ADMIN\"" +
                                                "}" +
                                        "}" +
                            "}";

        assertThat(mvcResult.getResponse().getContentAsString(), is(expected));
    }

    @Test
    void whenUpdateMessageThenReturnStatusOk() throws Exception {
        Message message = Message.of("TEXT");
        message.setId(1);
        Message updated = Message.of("text");
        updated.setId(1);

        when(messageRepository.findById(anyInt())).thenReturn(Optional.of(message));
        when(messageRepository.save(any())).thenReturn(updated);

        String data = "{\"text\":\"text\"}";
        this.mockMvc.perform(put("/message/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(data))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    void whenDeleteThenReturnStatusOk() throws Exception {
        this.mockMvc.perform(delete("/message/0"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}