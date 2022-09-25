import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

// Terminal håndterer alt global data og configurations indstillinger.
public class Terminal {
    public Scanner scanner;

    public Boolean loginBool = false;
    public int userBalance;

    public int ticketAmount;
    public String adminPassword;
    public int ticketPrice;
    public int totalBalance;

    public Terminal(Scanner scanner) throws IOException {
        this.scanner = scanner;

        // Hvis der nu skulle ske en fejl, som der ikke bliver håndtere,
        // er det vigtigt at vores program kan genstarte, så maskinen ikke skal sættes op igen,
        // men blot kan huske de forrige indstillinger.
        readConfigFileOrSetup();
    }

    public void readConfigFileOrSetup() throws IOException {

        try {
            File configFile = new File("config.txt");
            Scanner input = new Scanner(configFile);

            // Parser filen, og angiver værdierne
            // Følger et meget simpelt format: key=value
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] kv = line.split("=");
                String key = kv[0];
                String value = kv[1];

                if (key.equals("password")) {
                    this.adminPassword = value;
                }

                if (key.equals("ticketPrice")) {
                    this.ticketPrice = Integer.parseInt(value);
                }
            }
            input.close();

        }

        /*
         * hvis admin ikke har oprettet en fil endnu, (AKA. læsning fra config.txt giver
         * fejl)
         * Skal den oprette en ny fil, med administrators ønskede instillinger.
         */
        catch (FileNotFoundException e) {

            System.out.println("blip blop, doing setup...");
            System.out.println("indsæt password");
            String password = this.scanner.nextLine();
            System.out.println("indsæt billet pris i dkk");
            int ticketPrice = Integer.parseInt(this.scanner.nextLine());
            File newConfigFile = new File("config.txt");
            newConfigFile.createNewFile();

            FileWriter tempFileWriter = new FileWriter("config.txt");
            tempFileWriter.write("password=" + password + "\n");
            tempFileWriter.write("ticketPrice=" + ticketPrice);
            tempFileWriter.close();

            // Her angives de valgte værdier
            this.adminPassword = password;
            this.ticketPrice = ticketPrice;
        }
    }
}