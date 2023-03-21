package com.aakarsh.gptgeneratedpokemonapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pokemon")
public class Pokemon {
    @Id
    private int index;
    private String name;
    private List<String> moves;
    private Type type;
}
