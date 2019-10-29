package com.wildcodeschool.pokemonAPI.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildcodeschool.pokemonAPI.model.Pokemon;
import com.wildcodeschool.pokemonAPI.model.Types;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Controller
public class PokemonController {

    // RequestParam /pokemon?id=x > Utilisez-le pour un envoie de formulaire
    // PathVariable /pokemon/id/x > Pour le reste

    private static final String POKEMON_API = "https://pokeapi.co/api/v2";

    @GetMapping("/pokemon/{id}")
    public String getPokemon(Model out, @PathVariable String id) {
        WebClient webClient = WebClient.create(POKEMON_API);
        Mono<String> call = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/pokemon/{id}/")
                        .build(id))
                .retrieve()
                .bodyToMono(String.class);
        String response = call.block();

        ObjectMapper objectMapper = new ObjectMapper();
        Pokemon pokemon = null;
        try {
            JsonNode root = objectMapper.readTree(response);
            String name = root.get("name").asText();
            int pokemonId = root.get("id").asInt();
            int height = root.get("height").asInt();

            Types[] types = objectMapper.convertValue(root.get("types"), Types[].class);

            pokemon = new Pokemon(pokemonId, name, height, types);

            out.addAttribute("pokemon", pokemon);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "pokemon";
    }

    @GetMapping("/bulbasaur")
    @ResponseBody
    public String getBulbasaur() {
        String url = "https://pokeapi.co/api/v2/pokemon/1/";
        WebClient webClient = WebClient.create(url);
        Mono<String> call = webClient.get()
                .retrieve()
                .bodyToMono(String.class);
        String response = call.block();

        return response;
    }
}
