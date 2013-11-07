package com.reed.birdseye;

public class Message implements Comparable<Message> {

    float id;

    String message;

    Message(String message, float id) {
        this.message = message;
        this.id = id;
    }

    @Override
    public int compareTo(Message o) {
        if (id > o.id) {
            return -1;
        }
        if (id < o.id) {
            return 1;
        }
        return 0;
    }
}