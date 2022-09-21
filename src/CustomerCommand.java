import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class CustomerCommand implements Command {

    public void run(Terminal terminal) {
        switch (terminal.language) {
            case Danish:
                initialiseCustomerCommand(terminal);
                break;
        }

    }

    public void initialiseCustomerCommand(Terminal terminal) {
        if (!terminal.loginBool) {
            login(terminal);
        }
        danishBuyCommand(terminal);
    }

    public void login(Terminal terminal) {
        App.clear();
        App.divider();
        System.out.println("1) Login");
        System.out.println("2) Tilbage");
        String input = terminal.scanner.nextLine();

        switch (input) {
            case "1":
                terminal.loginBool = true;
                break;
            case "2":
                break;
        }
    }

    public void danishBuyCommand(Terminal terminal) {
        while (true) {
            App.clear();
            App.divider();
            System.out.println("1) Køb billetter");
            System.out.println("2) Udskriv Biletter");
            System.out.println("3) Se Balance");
            System.out.println("4) Indsæt");
            System.out.println("5) Udbetal");
            System.out.println("6) Tilbage");
            App.divider();
            final String input = terminal.scanner.nextLine();

            switch (input) {
                case "1":
                    buyTickets(terminal);
                    break;
                case "2":
                    new PrintTicketCommand().run(terminal);
                    break;
                case "3":
                    balance(terminal);
                    break;
                case "4":
                    insert(terminal);
                    break;
                case "5":
                    udbetal(terminal);
                    break;
                case "6":
                    return;

            }
        }
    }

    private void udbetal(Terminal terminal) {
        App.clear();
        App.divider();
        int udbetaling = 0;
        // int femHundrede = (terminal.userBalance - (terminal.ticketAmount *
        // terminal.ticketPrice)) % 500;
        // int hundrede = (terminal.userBalance - (terminal.ticketAmount *
        // terminal.ticketPrice)) % 100 - femHundrede*500;
        // int halvtreds = (terminal.userBalance - (terminal.ticketAmount *
        // terminal.ticketPrice)) % 50 - hundrede*100 - femHundrede*500;
        // int tyve = (terminal.userBalance - (terminal.ticketAmount *
        // terminal.ticketPrice)) % 20 - hundrede*100 - femHundrede*500 - halvtreds*50;
        // int ti = (terminal.userBalance - (terminal.ticketAmount *
        // terminal.ticketPrice)) % 10;
        // int fem = (terminal.userBalance - (terminal.ticketAmount *
        // terminal.ticketPrice)) % 5;
        // int to = (terminal.userBalance - (terminal.ticketAmount *
        // terminal.ticketPrice)) % 2;
        // int en = (terminal.userBalance - (terminal.ticketAmount *
        // terminal.ticketPrice)) % 1;
        // int enHalv = (terminal.userBalance - (terminal.ticketAmount *
        // terminal.ticketPrice)) % (1 / 2);

    }

    private void insert(Terminal terminal) {
        App.clear();
        App.divider();
        while (true) {
            System.out.println("Indsæt et beløb i kontant");
            String input = terminal.scanner.nextLine();
            if (isNumeric(input)) {
                terminal.userBalance += Integer.parseInt(input);
                return;
            } else {
                App.clear();
                System.out.println("Hov noget gik galt! prøv igen");
            }
        }
    }

    private void balance(Terminal terminal) {
        App.clear();
        App.divider();
        System.out.println("Du har " + terminal.userBalance + " dkk på din konto");
        App.awaitEnter(terminal);
    }

    public void buyTickets(Terminal terminal) {
        App.clear();
        System.out.println("du har " + terminal.ticketAmount + " billetter");
        System.out.println("Vælg antallet af billetter som de ønsker at køber (maks. 10)");
        takeTicketInput(terminal);
        if (flereBiletterPromt(terminal)) {
            return;
        }
    }

    public int ticketInputChecker(int input, Terminal terminal) {
        if (input > 10) {
            System.out.println("Hov du kan maks købe 10 biletter");
            return 0;
        } else if (input < 0) {
            System.out.println("Husk at køb en billet");
            return 0;
        }
        System.out.println("Du har købt " + input + " billetter!");
        System.out.println("");
        terminal.totalBalance += input * terminal.ticketPrice;
        return input;
    }

    public void takeTicketInput(Terminal terminal) {
        String input = terminal.scanner.nextLine();
        if (isNumeric(input)) {
            terminal.ticketAmount += ticketInputChecker(Integer.parseInt(input), terminal);
        } else {
            System.out.println("Hov det er vist ikke et tal! prøv igen");
            buyTickets(terminal);
        }
        return;
    }

    public Boolean flereBiletterPromt(Terminal terminal) {
        System.out.println("Vil du købe flere billetter?");
        System.out.println(" 1) Ja\n 2) Nej");

        String inputBack = terminal.scanner.nextLine();
        if (inputBack.equals(new String("2"))) {
            return true;
        } else if (inputBack.equals(new String("1"))) {
            buyTickets(terminal);
        } else {
            System.out.println("Hov noget gik galt!");
        }

        return true;
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}