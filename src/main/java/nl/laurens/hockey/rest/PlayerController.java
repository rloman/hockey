package nl.laurens.hockey.rest;

import nl.laurens.hockey.model.Player;
import nl.laurens.hockey.persistence.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/players")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;


    @GetMapping
    public Iterable<Player> list() {

        Iterable<Player> players = this.playerRepository.findAll();

        return players;

    }

    @PostMapping
    public Player create(@RequestBody Player player) {

        return this.playerRepository.save(player);
    }

}
