import java.util.Scanner;

public class Terminal {
    public Terminal(Scanner scanner) {

    }
 
    public void run(Command command) {
        if (command instanceof HelpCommand) {
            HelpCommand helpCommand = (HelpCommand)command;
        }
    }
}