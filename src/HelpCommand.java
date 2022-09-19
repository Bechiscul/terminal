public class HelpCommand implements Command {

    public void run(Terminal terminal) {
        test(terminal);
        App.awaitEnter(terminal);
    }

    public void test(Terminal terminal) {
        App.divider();
        System.out.println("1) hjælp 1");
        System.out.println("2) hjælp 2");
        System.out.println("3) tilbage");
        App.divider();

        final String input = terminal.scanner.nextLine();
        switch (input) {
            case "1":
                System.out.println("hjælp til dig <3");
            case "2":
                System.out.println("imagine at få hjælp");
            case "3":
                return;
        }
    }
}
