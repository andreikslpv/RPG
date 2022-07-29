package InterfacesAndThread;

public interface Utils {
    String INDENT_1_LEVEL = "  ";
    String INDENT_2_LEVEL = "    ";
    String INDENT_3_LEVEL = "      ";
    String INDENT_4_LEVEL = "        ";
    long DELAY = 500;

    static int getRandom(int bound) {
        return (int) (Math.random() * (bound + 1));
    }
}