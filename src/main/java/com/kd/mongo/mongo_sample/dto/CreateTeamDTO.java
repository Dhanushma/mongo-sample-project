package com.kd.mongo.mongo_sample.dto;


import com.kd.mongo.mongo_sample.model.Address;
import com.kd.mongo.mongo_sample.model.Team;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTeamDTO {

    @Size(min = 1, message = "Team name should be longer than 1 character")
    @NotNull(message = "Team Name should not be null")
    private String name;

    @NotNull(message = "Team Acronym should not be null")
    private String acronym;

    private Address address;

    public Team toTeam() {
        Team team = new Team();
        team.setAcronym(acronym);
        team.setName(name);
        team.setAddress(address);
        return team;
    }

}
