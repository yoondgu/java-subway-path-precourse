package subway;

import java.util.Scanner;
import subway.controller.FirstController;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        FirstController.initializeInstance(scanner);
    }
}
