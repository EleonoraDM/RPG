import models.actions.OneVsOne;
import models.interfaces.Action;
import models.interfaces.Targetable;
import models.participants.Warrior;
import models.participants.Wizard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        //CreateParticipant Warr Warrior
        //CreateParticipant Wizz Wizard

        Targetable hero1 = new Warrior("Warr");
        Targetable hero2 = new Wizard("Wizz");

        List<Targetable> participants = new ArrayList<>();
        participants.add(hero1);
        participants.add(hero2);

        Action action = new OneVsOne();
        String result = action.executeAction(participants);

        System.out.println(result);


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
