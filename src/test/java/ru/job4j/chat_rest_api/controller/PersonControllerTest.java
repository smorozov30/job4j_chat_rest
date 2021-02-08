package ru.job4j.chat_rest_api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.job4j.chat_rest_api.ChatRestApiApplication;
import ru.job4j.chat_rest_api.domian.Person;
import ru.job4j.chat_rest_api.domian.Role;
import ru.job4j.chat_rest_api.service.person.PersonService;

import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ChatRestApiApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles(value = {"no_auth"})
class PersonControllerTest {

    @MockBean
    private PersonService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenFindAllThenReturnListOfPersonInJSON() throws Exception {
        Person person = Person.of("login", "password");
        Role role = Role.of("ROLE_ADMIN");
        person.setRole(role);
        when(service.findAllPersons()).thenReturn(List.of(person));

        MvcResult mvcResult = this.mockMvc.perform(get("/person/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String expected = "[{\"id\":0," +
                            "\"login\":\"login\"," +
                            "\"password\":\"password\"," +
                            "\"role\":{" +
                                    "\"id\":0," +
                                    "\"name\":\"ROLE_ADMIN\"" +
                                "}" +
                         "}]";
        assertThat(mvcResult.getResponse().getContentAsString(), is(expected));
    }

    @Test
    void whenFindByIdZeroThenReturnRoleInJSONAndStatusOK() throws Exception {
        Person person = Person.of("login", "password");
        Role role = Role.of("ROLE_ADMIN");
        person.setId(1);
        person.setRole(role);
        when(service.findPersonById(1)).thenReturn(person);

        MvcResult mvcResult = this.mockMvc.perform(get("/person/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String expected = "{\"id\":1," +
                            "\"login\":\"login\"," +
                            "\"password\":\"password\"," +
                            "\"role\":{" +
                                    "\"id\":0," +
                                    "\"name\":\"ROLE_ADMIN\"" +
                                "}" +
                            "}";
        assertThat(mvcResult.getResponse().getContentAsString(), is(expected));
    }

    @Test
    void whenCreateNewPersonThenReturnSavedPersonAndStatusCreated() throws Exception {
        Person person = Person.of("login", "password");
        Role role = Role.of("ROLE_ADMIN");
        person.setRole(role);
        when(service.savePerson(any())).thenReturn(person);

        String data = "{\"login\":\"login\",\"password\":\"password\"}";
        MvcResult mvcResult = this.mockMvc.perform(post("/person/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(data))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        String expected = "{\"id\":0," +
                            "\"login\":\"login\"," +
                            "\"password\":\"password\"," +
                            "\"role\":{" +
                                    "\"id\":0," +
                                    "\"name\":\"ROLE_ADMIN\"" +
                                "}" +
                        "}";
        assertThat(mvcResult.getResponse().getContentAsString(), is(expected));
    }

    @Test
    void whenUpdatePersonThenReturnStatusOk() throws Exception {
        String data = "{\"login\":\"login\",\"password\":\"password\"}";
        this.mockMvc.perform(put("/person/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(data))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void whenDeleteThenReturnStatusOk() throws Exception {
        this.mockMvc.perform(delete("/person/0"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}