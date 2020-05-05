package com.raywenderlich;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Payload implements Serializable  {

    @JsonProperty("body")
    private Post body;

    public Post getBody() {
        return body;
    }

    public void setBody(Post body) {
        this.body = body;
    }



}
