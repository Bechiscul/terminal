import java.util.ArrayList;

public class HelpCommand implements Command {

    public void run(Terminal terminal) {
        admin(terminal);

    }

    public void admin(Terminal terminal) {
        while (true) {
            App.clear();
            App.divider();
            System.out.println("1) Hjælp til admin");
            System.out.println("2) Hjælp til brugeren");
            System.out.println("3) tilbage");
            App.divider();

            final String input = terminal.scanner.nextLine(); // tager input fra brugeren
            switch (input) {
                case "1":
                    App.clear();
                    App.divider();
                    System.out.println("For at komme ind på admin menuen \n" +
                            "Skal du skrive \"3 kodeord\", hvor\n" +
                            "\"Kodeord\", erstattes med admin kodeordet" + "\n"
                            + "Koden kan ændres ved at slette config.txt filen\n" +
                            "Eller ved at manipulere i den");
                    App.divider();
                    App.awaitEnter(terminal);
                    break;
                case "2":
                    App.clear();
                    App.divider();
                    System.out.println("Der navigeres ved at skrive valg med tastaturet"
                            + "\nNår der logges ud, vil alt information blive slettet om brugeren!"
                            + "\nOg det indsatte beløb vil blive tilbagebetalt");
                    App.divider();
                    App.awaitEnter(terminal);
                    break;
                case "3":
                    return;
            }
        }
    }
}
