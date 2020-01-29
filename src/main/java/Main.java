import core.EngineImpl;
import core.interfaces.Engine;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Engine engine = new EngineImpl();
        engine.run();
    }
}
