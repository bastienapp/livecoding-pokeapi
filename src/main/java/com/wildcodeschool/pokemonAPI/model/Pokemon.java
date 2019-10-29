package com.wildcodeschool.pokemonAPI.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Pokemon {

    private int id;
    private String name;
    private int height;
    private Types[] types;

    public Pokemon() {
    }

    public Pokemon(int id, String name, int height, Types[] types) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.types = types;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Types[] getTypes() {
        return types;
    }

    public void setTypes(Types[] types) {
        this.types = types;
    }
}
