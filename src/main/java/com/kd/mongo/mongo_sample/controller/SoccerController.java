package com.kd.mongo.mongo_sample.controller;

import com.kd.mongo.mongo_sample.dto.CreatePlayerDTO;
import com.kd.mongo.mongo_sample.dto.CreateTeamDTO;
import com.kd.mongo.mongo_sample.exception.PlayerNotFoundException;
import com.kd.mongo.mongo_sample.exception.TeamNotFoundException;
import com.kd.mongo.mongo_sample.model.Player;
import com.kd.mongo.mongo_sample.model.Team;

import com.kd.mongo.mongo_sample.repository.PlayerRepository;
import com.kd.mongo.mongo_sample.repository.TeamRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
@Tag(
        name = "Soccer Controller",
        description = "Controller to execute CRUD operations for Soccer"
)
public class SoccerController {

    private TeamRepository teamRepository;
    private PlayerRepository playerRepository;


    // create a team
    @Operation(
            summary = "Method to create a Team",
            description = "HTTP method to create a Team in soccer service"
    )
    @ApiResponse(
            responseCode = "201", description = "new Team is created"
    )
    @PostMapping("/teams")
    public ResponseEntity<Team> createTeam(@RequestBody @Valid CreateTeamDTO createTeamDTO) {

        log.info("Creating a team");
        Team team = teamRepository.save(createTeamDTO.toTeam());
        return new ResponseEntity<>(team, HttpStatus.CREATED);
    }

    // create a player
    @PostMapping("/players")
    public ResponseEntity<Player> createPlayer(@RequestBody @Valid CreatePlayerDTO createPlayerDTO) {
        Player player = playerRepository.save(createPlayerDTO.toPlayer());
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    //update a player
    @PutMapping("/players/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable String id, @RequestBody CreatePlayerDTO createPlayerDTO) {
        Optional<Player> opplayer = playerRepository.findById(id);
        if (opplayer.isEmpty()) {
            throw new PlayerNotFoundException("Player is not available with the id " + id);
        }
        Player p = opplayer.get();
        p.setAvailable(createPlayerDTO.isAvailable());
        p.setName(createPlayerDTO.getName());
        p.setPosition(createPlayerDTO.getPosition());

        Player playerUpdated = playerRepository.save(p);
        return new ResponseEntity<>(playerUpdated, HttpStatus.OK);
    }

    @PutMapping("/teams/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable String id, @RequestBody CreateTeamDTO createTeamDto) {
        Optional<Team> optionalTeam = teamRepository.findById(id);

        if (optionalTeam.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        Team teamToUpdate = optionalTeam.get()
                .setAddress(createTeamDto.getAddress())
                .setName(createTeamDto.getName())
                .setAcronym(createTeamDto.getAcronym());

        Team teamUpdated = teamRepository.save(teamToUpdate);

        return new ResponseEntity<>(teamUpdated, HttpStatus.OK);
    }

    @Operation(
            summary = "Method to delete Team by Id",
            description = "Method to delete Team by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Team is deleted"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Team is not available with the given Id in db"
    )
    @DeleteMapping("/teams/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable String id) {
        Optional<Team> teamToDelete = teamRepository.findById(id);
        if (teamToDelete.isEmpty()) {
            throw new TeamNotFoundException("Team is not available with the id " + id);
        }
        teamRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Add Player to a Team
    @PostMapping("/teams/{id}/players")
    public ResponseEntity<Team> addPlayersToTeam(@PathVariable String id, @RequestBody List<String> playerIds) {
        Optional<Team> team = teamRepository.findById(id);
        if (team.isEmpty()) {
            throw new TeamNotFoundException("Team is not available with the id " + id);
        }
        Team temToUpdate = team.get();

        Set<Player> playerSet = playerIds.stream()
                .map(i -> playerRepository.findById(i))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        temToUpdate.setPlayers(playerSet);
        Team updatedTeam = teamRepository.save(temToUpdate);
        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
    }

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getTeams(){
        return  new ResponseEntity<>(teamRepository.findAll(), HttpStatus.OK);
    }

    //Pagination
    @GetMapping("/teamsInPages")
    public ResponseEntity<Page<Team>> getTeambyPages(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<Team> pages = teamRepository.findAll(pageable);
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    //Sorting
    @GetMapping("/sortedTeams")
    public ResponseEntity<List<Team>> getSorted(
            @RequestParam(defaultValue = "id,asc") String sort
    ){
        String[] arr = sort.split(",");
        Sort.Direction direction = Sort.Direction.fromString(arr[1]);
        Sort sortby = Sort.by(direction, arr[0]);
        List<Team> teams = teamRepository.findAll(sortby);
        return  new ResponseEntity<>(teams, HttpStatus.OK);
    }


    //search
    @GetMapping("/search")
    public ResponseEntity<List<Player>> searchProducts(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") boolean isAvailable
    ) {
        List<Player> players = playerRepository.search(name, isAvailable);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/player/{id}")
    // to use caching by SpringBoot cuncurrentHashMap
    @Cacheable(value= "playerCache,", key="#id")
    public ResponseEntity<Player> getPlayerById(@PathVariable String id){

        Optional<Player> optionalPlayer = playerRepository.findById(id);
        if(optionalPlayer.isEmpty()){
            throw  new PlayerNotFoundException("Player not found with the given id");
        }
        Player player = optionalPlayer.get();
        return  new ResponseEntity<>(player, HttpStatus.OK);

    }





}
