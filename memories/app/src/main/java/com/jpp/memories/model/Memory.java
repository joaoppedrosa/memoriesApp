package com.jpp.memories.model;

/**
 * @author João Pedro Pedrosa, memories on 13-03-2017.
 */

public class Memory {

    private String quote;
    private String image;

    public Memory(){
    }

    public Memory(String quote, String image) {
        this.quote = quote;
        this.image = image;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
