import java.util.ArrayList;

public class Menu {
    ArrayList<String> texts;
    ArrayList<Runnable> funcs;

    Menu(ArrayList<String> texts, ArrayList<Runnable> funcs) {
        this.texts = texts;
        this.funcs = funcs;
    }

    public void run(String input) {
        for (int i = 0; i < texts.size(); i++) {
            System.out.println((i + 1) + ") " + texts.get(i));
        }
    }
}
