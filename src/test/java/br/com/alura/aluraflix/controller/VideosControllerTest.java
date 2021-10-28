package br.com.alura.aluraflix.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;
import java.net.URISyntaxException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VideosControllerTest {

    @Autowired
    //classe que simula uma requisição mvc
    private MockMvc mockMvc;

    @Test
    public void shouldResponse400IfDataNotFound() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/videos/{id}", 12))
                .andExpect(MockMvcResultMatchers
                        .status().isNotFound());
    }
}
