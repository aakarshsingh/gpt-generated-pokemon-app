package com.aakarsh.gptgeneratedpokemonapp.controller;

import com.aakarsh.gptgeneratedpokemonapp.model.Pokemon;
import com.aakarsh.gptgeneratedpokemonapp.model.Type;
import com.aakarsh.gptgeneratedpokemonapp.repository.PokemonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class PokemonControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PokemonRepository pokemonRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new PokemonController(pokemonRepository)).build();
    }

    @Test
    public void testGetAllPokemon() throws Exception {
        List<Pokemon> pokemonList = new ArrayList<>(Arrays.asList(
                new Pokemon(1, "Bulbasaur", Arrays.asList("Tackle", "Vine Whip"), Type.GRASS),
                new Pokemon(2, "Charmander", Arrays.asList("Scratch", "Ember"), Type.FIRE),
                new Pokemon(3, "Squirtle", Arrays.asList("Tackle", "Water Gun"), Type.WATER)
        ));
        when(pokemonRepository.findAll()).thenReturn(pokemonList);

        mockMvc.perform(MockMvcRequestBuilders.get("/pokemon"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testGetPokemonByIndex() throws Exception {
        Pokemon pokemon = new Pokemon(1, "Bulbasaur", Arrays.asList("Tackle", "Vine Whip"), Type.GRASS);
        when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));

        mockMvc.perform(MockMvcRequestBuilders.get("/pokemon/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.index").value(1))
                .andExpect(jsonPath("$.name").value("Bulbasaur"))
                .andExpect(jsonPath("$.moves[0]").value("Tackle"))
                .andExpect(jsonPath("$.moves[1]").value("Vine Whip"))
                .andExpect(jsonPath("$.type").value("GRASS"));
    }

    @Test
    public void testGetPokemonByIndexNotFound() throws Exception {
        int index = 100;
        when(pokemonRepository.findById(index)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/pokemon/" + index))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Pokemon #" + index + " not found"));
    }

}