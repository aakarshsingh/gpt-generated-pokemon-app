# gpt-generated-pokemon-app

This is a re-write of https://github.com/aakarshsingh/pokemon-app by using just GPT promopts. I used the following prompts with GPT 3.5 (Default) model:

- I want to build a Pokemon CRUD applications using Java. What are my options?
- How can I do it with Spring Boot? 
- What are some options for database with Spring Boot?
- Can I use MongoDB for this?
- Ok so we will do this with Spring Boot and MongoDB. How can I create a Spring Boot project? What is the structure? Can you give me the starting POM?
- For this, I want to create a Pokemon entity. It should have a index which is the Pokedex number of the Pokemon. This can also be the @Id. Then we want the name, a list of moves in String. And finally the type of the Pokemon. The type should be governed by an Enum 'Type'. This enum should represent common Pokemon types.
- The entity class looks good but can we do it using Lombok? Add these annotations on the class: Data, Builder, NoArgsConstructor, AllArgsConstructor
- What dependency do I need to add for Lombok?
- How can I interact with MongoDB from Spring Boot?
- Can I skip the Service and directly using the data repository that we created with the Controller?
- I want the requests to start with '/pokemon' can we drop the prefix of 'api'
- Can we modify the default GetMapping to include an optional parameter 'type' and return pokemon by type. If no 'type' is passed, we can return all 
- I dont like using * wildcard in imports, can you expand the imports?
- Can you replace import org.springframework.web.bind.annotation.* with the actual imports used rather than using '*' wildcard
- I dont see a findByType in the PokemonRepository, what do I need to put there to make it work?
- Can you define a getPokemonByIndex GET mapping?
- Can we define a custom PokemonNotFoundException which can be thrown in cases when we dont find a Pokemon? The exception should take in an index and throw a custom message like '("Pokemon #" + index + " not found");'
- Can you add the correct ResponseStatus to PokemonNotFoundException
- Can I have a PostMapping and a DeleteMapping?
- Can you generate some Unit Tests for this project with as high a coverage as possible?
