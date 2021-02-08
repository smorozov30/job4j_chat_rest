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
import ru.job4j.chat_rest_api.domian.Role;
import ru.job4j.chat_rest_api.service.role.RoleService;

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
class RoleControllerTest {

    @MockBean
    private RoleService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenFindAllThenReturnListOfRoleInJSON() throws Exception {
        when(service.findAllRoles()).thenReturn(List.of(Role.of("ROLE_USER")));

        MvcResult mvcResult = this.mockMvc.perform(get("/role/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String expected = "[{\"id\":0,\"name\":\"ROLE_USER\"}]";
        assertThat(mvcResult.getResponse().getContentAsString(), is(expected));
    }

    @Test
    void whenFindByIdZeroThenReturnRoleInJSONAndStatusOK() throws Exception {
        Role role = Role.of("ROLE_USER");
        role.setId(1);
        when(service.findRoleById(1)).thenReturn(role);

        MvcResult mvcResult = this.mockMvc.perform(get("/role/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String expected = "{\"id\":1,\"name\":\"ROLE_USER\"}";
        assertThat(mvcResult.getResponse().getContentAsString(), is(expected));
    }

    @Test
    void whenCreateNewRoleThenReturnSavedRoleAndStatusCreated() throws Exception {
        Role role = Role.of("ROLE_USER");
        when(service.saveRole(any())).thenReturn(role);

        String data = "{\"name\":\"ROLE_USER\"}";
        MvcResult mvcResult = this.mockMvc.perform(post("/role/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(data))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        String expected = "{\"id\":0,\"name\":\"ROLE_USER\"}";
        assertThat(mvcResult.getResponse().getContentAsString(), is(expected));
    }

    @Test
    void whenUpdateRoleThenReturnStatusOk() throws Exception {
        String data = "{\"name\":\"ROLE_USER\"}";
        this.mockMvc.perform(put("/role/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(data))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void whenDeleteThenReturnStatusOk() throws Exception {
        this.mockMvc.perform(delete("/role/0"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}