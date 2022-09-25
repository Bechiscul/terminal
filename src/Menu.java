import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/// Menu implementerer Command.
/// Den håndterer at skifte mellem forskellige kommandoer. 
/// Eksempelvis HelpCommand er altså en enkelt kommando man kan køre, mens Menu er en kollektion/liste af kommandoer.
public class Menu implements Command {
    private ArrayList<String> labels;
    private ArrayList<Command> commands;
    private boolean back;

    public Menu(List<String> labels, List<Command> commands, boolean back) {
        // Det forventes at der er mindst en label for hver kommando.
        assert labels.size() == commands.size();

        this.labels = new ArrayList<String>(labels);
        this.commands = new ArrayList<Command>(commands);
        this.back = back;
    }

    public void run(Terminal terminal, String _input) {
        while (true) {
            App.clear();
            App.divider();

            // Menu håndterer formattering af kommandoerne.
            // Derfor printes der første de forskellige kommandoers navne (labels)
            for (int i = 0; i < this.labels.size(); i++) {
                System.out.println((i + 1) + ") " + this.labels.get(i));
            }

            // Hvis det er en submenu, burde den indeholde en tilbage knap.
            if (this.back) System.out.println(this.labels.size() + 1 + ") Tilbage");
            App.divider();

            try {
                String input = terminal.scanner.nextLine();
                String[] words = input.split(" ");
                int index = Integer.parseInt(words[0]) - 1;
                if (index == this.labels.size()) break;

                // Det er vigtigt at sætte remainingInput,
                // fordi det tillader os at give ekstra data til de forskellige kommandoer.
                String remainingInput = "";
                if (words.length > 1) {
                    remainingInput = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                }

                // Derefter køres den valgte kommando.
                this.commands.get(index).run(terminal, remainingInput);
            } catch (Exception e) {
                // Hvis der sker en fejl, printes blot en generisk fejl besked.
                App.errorMSG(); 
            }
        }
    }
}
