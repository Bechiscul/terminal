import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        App.clear();

        final Scanner scanner = new Scanner(System.in);
        Terminal terminal = new Terminal(scanner);

        home(terminal);
    }

    public static void home(Terminal terminal) throws InterruptedException {
        // tegner DSB logo ved start...
        Logo logo = new Logo();
        logo.run();
        App.clear();

        String[] labels = {new String("Hjælp"), new String("Kundemenu"), new String("Admin"), new String("Log Ud")};
        Command[] commands = {
            new HelpCommand(),
            new CustomerCommand(),
            new AdminCommand(),
            (Terminal t, String _i) -> {
                if (t.loginBool) {
                    if (t.userBalance > 0) CustomerCommand.udbetal(terminal);
                    t.loginBool = false;
                    t.ticketAmount = 0;
                    t.userBalance = 0;
                    return;
                }
                    
                System.out.println("Du er ikke logget ind");
                App.awaitEnter(terminal);
            },

            (Terminal t, String _i) -> {
                System.out.println("5");
            }
        }; 
        
        Menu menu = new Menu(Arrays.asList(labels), Arrays.asList(commands), false);
        menu.run(terminal, "");
        App.divider();
    }

    // clear console
    public static void clear() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static void awaitEnter(Terminal terminal) {
        System.out.println("\nTryk 'Enter' for at fortsætte...");
        String awaitString = terminal.scanner.nextLine();
    }

    public static void divider() {
        System.out.println("--------------------------------");
    }

    public static void errorMSG() {
        System.out.println("Hov noget gik galt! prøv igen");
    }
}
