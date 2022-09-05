import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        final Scanner scanner = new Scanner(System.in);

        Terminal terminal = new Terminal(scanner);
        while (true) {
            final String[] input = scanner.nextLine().split(" ");

            switch (input[0]) {
                case "help": terminal.run(new HelpCommand());
                case "admin": terminal.run(new AdminCommand(input[1]));
                case "buy":
            }
            // TODO(Bech): ...
        }
    }
}
