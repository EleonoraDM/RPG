import models.participants.Hero;
import models.participants.HeroImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


//FIXME will be subject to refactoring
        /*ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        Battlefield battleField = new BattlefieldImplementation(writer);

        String line = reader.readLine();
        while (!line.equals("Peace")){
            String[] lineTokens = line.split("\\s+");

            switch (lineTokens[0].toLowerCase()){
                case "createparticipant" :
                    battleField.createParticipant(lineTokens[1], lineTokens[2]);
                    break;
                case "createaction" :
                    battleField.createAction(lineTokens[1],
                            Arrays.copyOf(Arrays.stream(lineTokens).skip(2).toArray(),
                                    Arrays.stream(lineTokens).skip(2).toArray().length,
                                    String[].class));
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }

            line = reader.readLine();
        }
*/
    }
}
