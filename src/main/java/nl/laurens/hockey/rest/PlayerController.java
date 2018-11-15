package nl.laurens.hockey.rest;

import nl.laurens.hockey.model.Player;
import nl.laurens.hockey.persistence.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/players")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;


    @PostMapping
    public Player create(@RequestBody Player player) {

        return this.playerRepository.save(player);
    }

    @GetMapping
    public Iterable<Player> list() {

        Iterable<Player> players = this.playerRepository.findAll();

        return players;

    }

    @GetMapping("{id}")
    public Player get(@PathVariable long id) {
        Optional<Player> optionalPlayer = this.playerRepository.findById(id);

        if (optionalPlayer.isPresent()) {
            return optionalPlayer.get();
        } else {
            throw new IllegalArgumentException();
        }
    }

    @PutMapping("{id}")
    public Player update(@PathVariable long id, @RequestBody Player player) {
        Optional<Player> optionalPlayer = this.playerRepository.findById(id);

        if (optionalPlayer.isPresent()) {

            Player target = optionalPlayer.get();
            target.setName(player.getName());
            target.setPosition(player.getPosition());
            target.setRugnummer(player.getRugnummer());

            this.playerRepository.save(target);

            return target;

        } else {
            throw new IllegalArgumentException();
        }
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        Optional<Player> optionalPlayer = this.playerRepository.findById(id);

        if (optionalPlayer.isPresent()) {
            this.playerRepository.deleteById(id);
        }
    }


}
