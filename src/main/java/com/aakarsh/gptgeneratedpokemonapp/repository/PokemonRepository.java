package com.aakarsh.gptgeneratedpokemonapp.repository;

import com.aakarsh.gptgeneratedpokemonapp.model.Pokemon;
import com.aakarsh.gptgeneratedpokemonapp.model.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends MongoRepository<Pokemon, Integer> {
    List<Pokemon> findByType(Type type);
}
