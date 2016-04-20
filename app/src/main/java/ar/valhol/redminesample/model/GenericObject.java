package ar.valhol.redminesample.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class GenericObject {
    long id;
    String name;

    public GenericObject() {
    }

    public GenericObject(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
