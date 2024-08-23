package com.kd.mongo.mongo_sample.controller;


import com.kd.mongo.mongo_sample.repository.PlayerRepository;
import com.kd.mongo.mongo_sample.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SoccerControllerTest {

    @Mock
    TeamRepository teamRepository;

    @Mock
    PlayerRepository playerRepository;

    @InjectMocks
    SoccerController soccerController;

    @Test
    public void testSample(){

    }
}
