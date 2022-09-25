
public class AdminCommand implements Command {
    public void run(Terminal terminal, String input) {
        // hvis brugeren har tastet det rigtige password
        String password = input.split(" ")[0];
        if (password.equals(terminal.adminPassword)) {
            displayAdminSettings(terminal);
        } else {
            System.out.println("Forkert Kodeord!");
            App.awaitEnter(terminal);
        }
    }

    public void displayAdminSettings(Terminal terminal) {
        while (true) {
            App.clear();
            App.divider();
            System.out.println("1) Se Billet Pris");
            System.out.println("2) Se Balance");
            System.out.println("3) Tilbage");
            String input = terminal.scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("Billet Prisen er: " + terminal.ticketPrice + " dkk");
                    App.awaitEnter(terminal);
                    break;
                case "2":
                    System.out.println("Maskinen har: " + terminal.totalBalance + " dkk");
                    App.awaitEnter(terminal);
                    break;
                case "3":
                    return;
            }
        }

    }
}
