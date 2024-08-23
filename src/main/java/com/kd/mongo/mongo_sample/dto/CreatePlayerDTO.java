package com.kd.mongo.mongo_sample.dto;

import com.kd.mongo.mongo_sample.model.Player;
import com.kd.mongo.mongo_sample.model.PlayerPosition;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Schema(
        name="Player",
        description = "Add player"
)
public class CreatePlayerDTO {

    @NotNull(message = "Player name should not be null")
    @Size(min=0, message = "Name should be greater than one Character for a player")
    private String name;

    private Date birthDate;

    private PlayerPosition position;

    private boolean isAvailable;


    public Player toPlayer(){
        Player player = new Player();
        player.setPosition(position);
        player.setName(name);
        player.setAvailable(isAvailable);
        player.setBirthdate(birthDate);
        return player;

    }
}
