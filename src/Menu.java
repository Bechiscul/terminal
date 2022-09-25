import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu implements Command {
    private ArrayList<String> labels;
    private ArrayList<Command> commands;
    private boolean logout;
    private boolean back;

    public Menu(List<String> labels, List<Command> commands, boolean logout, boolean back) {
        assert labels.size() == commands.size();

        this.labels = new ArrayList<String>(labels);
        this.commands = new ArrayList<Command>(commands);
        this.back = back;
        this.logout = logout;
    }

    public void run(Terminal terminal, String _remainingInput) {
        while (true) {
            App.clear();
            App.divider();
            for (int i = 0; i < this.labels.size(); i++) {
                System.out.println((i + 1) + ") " + this.labels.get(i));
            }

            if (this.back) System.out.println(this.labels.size() + 1 + ") Tilbage");
            App.divider();

            try {
                String input = terminal.scanner.nextLine();
                String[] words = input.split(" ");
                int index = Integer.parseInt(words[0]) - 1;
                if (index == this.labels.size() + 1) break;

                String remainingInput = "";
                if (words.length > 1) {
                    remainingInput = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                }

                this.commands.get(index).run(terminal, remainingInput);
            } catch (Exception e) {
                App.errorMSG(); 
            }
        }
    }
}
