import java.util.Arrays;

public class HelpCommand implements Command {

    public void run(Terminal terminal, String _input) {
        String[] labels = {"Hjælp Til Admin", "Hjælp Til Brugeren"};
        Command[] commands = {
            (Terminal _t, String _i) -> {
                App.clear();
                App.divider();
                System.out.println("""
                    For at komme ind på admin menuen
                    Skal du skrive \"3 kodeord\", hvor
                    \"Kodeord\", erstattes med admin kodeordet.
                    Koden kan ændres ved at slette config.txt filen eller ved at manipulere i den
                """);
                App.divider();
                App.awaitEnter(terminal);
            },
            (Terminal _t, String _i) -> {
                App.clear();
                App.divider();

                System.out.println("""
                Der navigeres ved at skrive valg med tastaturet
                Når der logges ud, vil alt information blive slettet om brugeren!
                Og det indsatte beløb vil blive tilbagebetalt
                """);

                App.divider();
                App.awaitEnter(terminal);
            }
        };

        Menu menu = new Menu(Arrays.asList(labels), Arrays.asList(commands), true);
        menu.run(terminal, "");
    }
}
