package com.aakarsh.gptgeneratedpokemonapp.controller;

import com.aakarsh.gptgeneratedpokemonapp.PokemonNotFoundException;
import com.aakarsh.gptgeneratedpokemonapp.model.Pokemon;
import com.aakarsh.gptgeneratedpokemonapp.model.Type;
import com.aakarsh.gptgeneratedpokemonapp.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pokemon")
public class PokemonController {
    private final PokemonRepository pokemonRepository;

    public PokemonController(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @GetMapping
    public List<Pokemon> getAllPokemon(@RequestParam(required = false) Type type) {
        if (type != null) {
            return pokemonRepository.findByType(type);
        }
        return pokemonRepository.findAll();
    }

    @GetMapping("/{index}")
    public Pokemon getPokemonByIndex(@PathVariable int index) {
        return pokemonRepository.findById(index)
                .orElseThrow(() -> new PokemonNotFoundException(index));
    }

    @PostMapping
    public Pokemon createPokemon(@RequestBody Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    @PutMapping("/{index}")
    public Pokemon updatePokemon(@PathVariable int index, @RequestBody Pokemon pokemon) {
        pokemon.setIndex(index);
        return pokemonRepository.save(pokemon);
    }

    @DeleteMapping("/{index}")
    public void deletePokemonByIndex(@PathVariable int index) {
        pokemonRepository.deleteById(index);
    }
}
