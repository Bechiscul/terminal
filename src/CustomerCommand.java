import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class CustomerCommand implements Command {

    public void run(Terminal terminal, String _remainingInput) {
        System.out.println("yehaskdsa");
        initialiseCustomerCommand(terminal);
    }

    // initialiser funktion, og tjekker om sproget er på dansk
    // gør det muligt at have flere forskellige sprogmenuer.
    public void initialiseCustomerCommand(Terminal terminal) {
        if (!terminal.loginBool) {
            login(terminal);
        }

        if (terminal.loginBool) {
            danishBuyCommand(terminal);
        }
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

    // hvis sproget er dansk, initialiser den danske købCommand
    public void danishBuyCommand(Terminal terminal) {
        while (true) {

            App.clear();
            App.divider();
            showUserData(terminal);
            App.divider();

            System.out.println("1) Køb billetter");
            System.out.println("2) Udskriv Biletter");
            System.out.println("3) Se Balance");
            System.out.println("4) Indsæt");
            System.out.println("5) Udbetal");
            System.out.println("6) Tilbage");
            App.divider();
            final String input = terminal.scanner.nextLine();

            // kalder de forskellige funktion baseret på brugerens input
            switch (input) {
                case "1":
                    buyTickets(terminal);
                    break;
                case "2":
                    new PrintTicketCommand().run(terminal, "");
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

    // udbetaler penge tilbage til brugeren
    // I dets møntform, med simpels mulige udbetalingsform.
    public static void udbetal(Terminal terminal) {
        terminal.totalBalance -= terminal.userBalance;

        int[] moneyArr = { 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1 };
        int[] udbetalingsArr = new int[10];

        for (int i = 0; i < moneyArr.length; i++) {
            udbetalingsArr[i] = terminal.userBalance / moneyArr[i];
            terminal.userBalance -= udbetalingsArr[i] * moneyArr[i];
        }
        System.out.println("Du får udbetalt i kontant: ...");
        for (int i = 0; i < udbetalingsArr.length; i++) {
            if (udbetalingsArr[i] >= 1) {
                System.out.println(udbetalingsArr[i] + ":       " + moneyArr[i] + " kr");
            }

        }
        App.awaitEnter(terminal);
    }

    // indsæt beløb til brugerens konto
    private void insert(Terminal terminal) {
        App.clear();
        App.divider();
        while (true) {
            System.out.println("Indsæt et beløb i kontant");
            String input = terminal.scanner.nextLine();
            if (isNumeric(input) && Integer.parseInt(input) > 0) {
                terminal.userBalance += Integer.parseInt(input);
                terminal.totalBalance += Integer.parseInt(input);
                return;
            } else {
                App.clear();
                App.errorMSG();
            }
        }
    }

    // viser brugerens balance
    private void balance(Terminal terminal) {
        App.clear();
        App.divider();
        System.out.println("Du har " + terminal.userBalance + " dkk på din konto");
        App.awaitEnter(terminal);
    }

    // hvis brugeren vil købe billetter
    public void buyTickets(Terminal terminal) {
        App.clear();
        Boolean b = true;
        while (b) {
            showUserData(terminal);
            System.out.println("Vælg antallet af billetter som de ønsker at køber (maks.100)");
            System.out.println("Billet pris: " + terminal.ticketPrice + " dkk");

            String input = terminal.scanner.nextLine();
            int inputInt = 0;
            if (isNumeric(input)) {
                inputInt = Integer.parseInt(input); // hvis tal

                if (inputInt * terminal.ticketPrice > terminal.userBalance) // hvis dyrere end brugeren har
                {
                    int negativeAmm = terminal.ticketPrice * inputInt - terminal.userBalance;
                    System.out.println("Du mangler " + negativeAmm + " dkk");
                    if (!continueOption(terminal)) {
                        b = false;
                    }
                }

                else if (inputInt > 100) {
                    System.out.println("Du kan maks købe 100 biletter! ...");
                    if (!continueOption(terminal)) {
                        b = false;
                    }
                }

                else if (inputInt < 0) {
                    System.out.println("Antallet af biletter kan ikke være under 0! ...");
                    if (!continueOption(terminal)) {
                        b = false;
                    }

                }

                else // alt er iorden
                {
                    terminal.ticketAmount += inputInt; // giver bruger billetter
                    terminal.userBalance -= inputInt * terminal.ticketPrice; // trækker penge fra bruger sum
                    System.out.println("Du har købt " + inputInt + " biletter");
                    b = false;
                }
            }

            else { // hvis ikke tal
                App.errorMSG();
                App.awaitEnter(terminal);
            }
        }
    }

    // Grafisk funktion, tegner bare UI
    public void showUserData(Terminal terminal) {
        App.clear();
        System.out.println("Du har: " + terminal.ticketAmount + " billetter");
        System.out.println("Balance: " + terminal.userBalance + " dkk");
        App.divider();
    }

    // tjekker om input strengen er en integer
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

    // Tjekker om brugen vil fortsætte og returnerer en boolsk værdi
    public boolean continueOption(Terminal terminal) {
        Boolean b = true;
        String arg = "";

        while (b) {
            System.out.println("Vil du fortsætte?\n1) Ja\n2) Nej");
            arg = terminal.scanner.nextLine();

            if (arg.equals("1") || arg.equals("2")) {
                b = false;
            }

            else {
                System.out.println("Der skete en fejl, prøv igen! ...");
            }
        }
        switch (arg) {
            case "1":
                return true;
            default:
                return false;
        }
    }
}