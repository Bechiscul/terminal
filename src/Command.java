// Command er et interface som bruges til at abstracte de forskellige kommandoer som er i programmet.
public interface Command {
    public void run(Terminal terminal, String remainingInput);
}