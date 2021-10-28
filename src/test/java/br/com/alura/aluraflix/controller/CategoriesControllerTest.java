package br.com.alura.aluraflix.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class CategoriesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldResponse400IfDataNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/videos/{id}", 15))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
