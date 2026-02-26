package com.example.OnlineQuiz.Controller;


import com.example.OnlineQuiz.Dto.UserDto;
import com.example.OnlineQuiz.Service.UserService;
import com.example.OnlineQuiz.Utils.AppUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.mock.mockito.MockBean;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserDto userDto;
    @BeforeEach
    void setup() {
        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("John Doe");
        userDto.setEmail("john@example.com");
        userDto.setPassword("P@ssw0rd123");
        userDto.setRole(AppUtils.Roles.ADMIN);
    }

    @Test
    void givenUserObject_whenCreateUser_thenReturnSavedUser() throws Exception {

        given(userService.createUser(any(UserDto.class))).willAnswer(invocation -> invocation.getArgument(0));


        ResultActions response = mockMvc.perform(
                post("/users/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto))
        );


        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(userDto.getName()))
                .andExpect(jsonPath("$.email").value(userDto.getEmail()));
    }

    @Test
    void givenInvalidUser_whenCreateUser_thenReturnBadRequest() throws Exception {
        UserDto invalidUser = new UserDto();

        ResultActions response = mockMvc.perform(
                post("/users/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidUser))
        );

        response.andDo(print())
                .andExpect(status().isBadRequest());
    }
}

