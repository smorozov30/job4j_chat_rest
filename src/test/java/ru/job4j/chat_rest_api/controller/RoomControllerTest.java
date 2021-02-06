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
import ru.job4j.chat_rest_api.domian.Role;

import ru.job4j.chat_rest_api.domian.Room;
import ru.job4j.chat_rest_api.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ChatRestApiApplication.class)
@AutoConfigureMockMvc
class RoomControllerTest {

    @MockBean
    private RoomRepository roomRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenFindAllThenReturnListOfRoomInJSON() throws Exception {
        when(roomRepository.findAll()).thenReturn(List.of(Room.of("new room")));

        MvcResult mvcResult = this.mockMvc.perform(get("/room/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String expected = "[{\"id\":0,\"name\":\"new room\"}]";
        assertThat(mvcResult.getResponse().getContentAsString(), is(expected));
    }

    @Test
    void whenFindByIdZeroThenReturnRoomInJSONAndStatusOK() throws Exception {
        when(roomRepository.findById(0)).thenReturn(Optional.of(Room.of("new room")));

        MvcResult mvcResult = this.mockMvc.perform(get("/room/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String expected = "{\"id\":0,\"name\":\"new room\"}";
        assertThat(mvcResult.getResponse().getContentAsString(), is(expected));
    }

    @Test
    void whenCreateNewRoomThenReturnSavedRoomAndStatusCreated() throws Exception {
        Room room = Room.of("new room");
        when(roomRepository.save(any())).thenReturn(room);

        String data = "{\"name\":\"new room\"}";
        MvcResult mvcResult = this.mockMvc.perform(post("/room/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(data))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        String expected = "{\"id\":0,\"name\":\"new room\"}";
        assertThat(mvcResult.getResponse().getContentAsString(), is(expected));
    }

    @Test
    void whenUpdateRoomThenReturnStatusOk() throws Exception {
        String data = "{\"name\":\"new room\"}";
        this.mockMvc.perform(put("/room/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(data))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void whenDeleteThenReturnStatusOk() throws Exception {
        this.mockMvc.perform(delete("/room/0"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}