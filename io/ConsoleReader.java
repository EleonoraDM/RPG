package io;

import io.interfaces.Reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ConsoleReader implements Reader {
    private BufferedReader bf;
    private CommandsProcessor processor;

    public ConsoleReader(){
        this.bf = new BufferedReader(new InputStreamReader(System.in));
        this.processor = new CommandsProcessor();
    }

    @Override
    public String readLine() throws IOException {
        String input = this.bf.readLine();
        String[] tokens = input.split("\\s+");
        String command = tokens[0];
        String[] data = Arrays.copyOfRange(tokens, 1, tokens.length);

        return this.processor.commandExecution(command, data);
    }
}
