
public class Logo {
    public void run() throws InterruptedException {
        logo(); // Der er ikke noget der loader. Det ser bare sejt ud!!!
    }

    public void logo() throws InterruptedException {
        App.clear();
        String logo = new String("""
                ███████████████████
                █▄─▄▄▀█─▄▄▄▄█▄─▄─▀█
                ██─██─█▄▄▄▄─██─▄─▀█
                ▀▄▄▄▄▀▀▄▄▄▄▄▀▄▄▄▄▀▀
                   Loading""");
        System.out.print(logo);
        for (int i = 0; i < 4; i++) {
            // Den nuværende tråd (main-tråden, siden det er singlethreaded), venter i 4x 650ms.
            Thread.sleep(650);
            System.out.print(".");
        }
    }
}
