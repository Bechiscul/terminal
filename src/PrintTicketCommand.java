public class PrintTicketCommand implements Command {
    public void run(Terminal terminal, String _input) {
        App.clear();
        App.divider();

        // Går igennem antallet af billetter og printer dem en af gangen.
        if (terminal.ticketAmount > 0) {
            for (int i = 0; i < terminal.ticketAmount; i++) {
                print(terminal, i);
            }
        } else {
            System.out.println("Du har ingen biletter at printe!");
        }

        // Når billetterne er printet, kan de ikke printes igen!
        terminal.ticketAmount = 0;
        App.awaitEnter(terminal);
    }

    public void print(Terminal terminal, int i) {
        System.out.println("""
                ███████████████████
                █▄─▄▄▀█─▄▄▄▄█▄─▄─▀█
                ██─██─█▄▄▄▄─██─▄─▀█
                ▀▄▄▄▄▀▀▄▄▄▄▄▀▄▄▄▄▄▀
                """);
        System.out.println("Billet " + (i + 1));
        System.out.println("Pris " + terminal.ticketPrice);
    }

}
