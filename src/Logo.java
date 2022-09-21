
public class Logo {
    public void run() throws InterruptedException {
        logo(); // der er ikke noget der loader, det ser bare sejt ud!!!
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
            Thread.sleep(650);
            System.out.print(".");
        }
    }
}
