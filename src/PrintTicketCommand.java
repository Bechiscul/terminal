public class PrintTicketCommand implements Command {
    public void run(Terminal terminal) {
        for (int i = 0; i < terminal.ticketAmount; i++) {
            print(terminal, i);
        }
        App.awaitEnter(terminal);
    }

    public void print(Terminal terminal, int i) {
        System.out.println("Billet " + i + " printet");
    }

}
