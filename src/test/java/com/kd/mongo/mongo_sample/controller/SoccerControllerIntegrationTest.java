package com.kd.mongo.mongo_sample.controller;

import com.kd.mongo.mongo_sample.model.Player;
import com.kd.mongo.mongo_sample.model.PlayerPosition;
import com.kd.mongo.mongo_sample.repository.PlayerRepository;
import com.kd.mongo.mongo_sample.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class SoccerControllerIntegrationTest {

    @MockBean
    private TeamRepository teamRepository;

    @MockBean
    private PlayerRepository playerRepository;


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPlayerByIdTest() throws Exception {
        Long id = 1L;
        Player player = new Player();
        player.setName("Dhanu");
        player.setPosition(PlayerPosition.GOALKEEPER);
        player.setAvailable(true);
        Optional<Player> playerRes = Optional.of(player);
        Mockito.when(playerRepository.findById(Mockito.anyString())).thenReturn(playerRes);
        mockMvc.perform(MockMvcRequestBuilders.get("/player/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
