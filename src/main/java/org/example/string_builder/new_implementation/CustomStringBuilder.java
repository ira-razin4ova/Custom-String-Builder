package org.example.string_builder.new_implementation;

import java.util.ArrayDeque;

public class CustomStringBuilder {

    private StringBuilder string;
    private ArrayDeque<String> history;


    public CustomStringBuilder(String initialString) {
        this.string = new StringBuilder(initialString);
        this.history = new ArrayDeque<>();
    }

    public CustomStringBuilder append(String string) {
        this.history.push(this.string.toString());
        this.string.append(string);
        return this;
    }

    public CustomStringBuilder undo() {
        if (this.history.isEmpty()) {
            return this;
        }

        String previousState = this.history.pop();

        this.string.setLength(0);
        this.string.append(previousState);
        return this;
    }

    @Override
    public String toString() {
        return this.string.toString();
    }

    public StringBuilder getString() {
        return string;
    }

    public void setString(StringBuilder string) {
        this.string = string;
    }

    public ArrayDeque<String> getHistory() {
        return history;
    }

    public void setHistory(ArrayDeque<String> history) {
        this.history = history;
    }
}
