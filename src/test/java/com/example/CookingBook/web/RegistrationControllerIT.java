package com.example.CookingBook.web;


import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Value("${mail.host}")
    private String mailHost;

    @Value("${mail.port}")
    private Integer mailPort;
    @Value("${mail.username}")
    private String mailUsername;
    @Value("${mail.password}")
    private String mailPassword;

    private GreenMail greenMail;

    @BeforeEach
    void setUp() {
        greenMail = new GreenMail(new ServerSetup(mailPort, mailHost, "smtp"));
        greenMail.start();
        greenMail.setUser(mailUsername, mailPassword);

    }

    @AfterEach
    void tearDown() {
        greenMail.stop();
    }

    @Test
    @WithAnonymousUser
    void getRegistrationForm_byAnonymous_returnsProperView () throws Exception {

        mockMvc.perform(get("/auth/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/register"));
    }
    @Test
    @WithMockUser
    void getRegistrationForm_byUser_returnsStatusForbidden () throws Exception {

        mockMvc.perform(get("/auth/register"))
                .andExpect(status().isForbidden());
    }

    @Test
    void testRegistration() throws Exception {

        mockMvc.perform(post("/auth/register")
                .param("firstName", "12345")
                .param("lastName", "12345")
                .param("email", "testte@abv.bg")
                .param("password", "12312457")
                .param("confirmPassword", "test1234")
                .with(csrf())
        )                .andExpect(status().is3xxRedirection())
//                .andExpect(model().attributeHasErrors("bindingResult"))
                .andExpect(view().name("redirect:/auth/register"));
    }
}
