import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        App.clear();

        final Scanner scanner = new Scanner(System.in);
        Terminal terminal = new Terminal(scanner);
        home(terminal);

    }

    public static void home(Terminal terminal) {
        while (true) {
            App.clear();
            App.divider();
            System.out.println(" 1) Hjælp\n 2) Kundemenu\n 3) Admin menu ");

            if (terminal.loginBool) {
                System.out.println(" 4) Log ud!");
            }

            final String[] input = terminal.scanner.nextLine().split(" ");
            switch (input[0]) {
                case "1":
                    new HelpCommand().run(terminal);
                    break;
                case "2":
                    new CustomerCommand().run(terminal);
                    break;
                case "3":
                    try {
                        new AdminCommand(input[1]).run(terminal);
                    } catch (IndexOutOfBoundsException strE) {
                        System.out.println("Du skal huske at give et password!");
                        App.awaitEnter(terminal);
                    }
                    break;
                case "4": {
                    if (terminal.loginBool) {
                        terminal.loginBool = false;
                        terminal.ticketAmount = 0;
                        terminal.userBalance = 0;
                    }
                    break;
                }
            }
            // TODO(Bech): ...
        }
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
}
