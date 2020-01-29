package core;

import common.Commands;
import core.interfaces.Engine;
import io.ConsoleReader;
import io.ConsoleWriter;

import java.io.IOException;

public class EngineImpl implements Engine {
    private ConsoleReader reader;
    private ConsoleWriter writer;

    public EngineImpl() {
        this.reader = new ConsoleReader();
        this.writer = new ConsoleWriter();
    }

    @Override
    public void run() throws IOException {

        while (true){
            String result = null;

            try {
                result = this.reader.readLine();

                if (result.equals(Commands.PEACE.name())){
                    break;
                }
            } catch (IllegalArgumentException | NullPointerException ex) {
                result = ex.getMessage();
            }
            this.writer.writeLine(result);
        }
    }
}
