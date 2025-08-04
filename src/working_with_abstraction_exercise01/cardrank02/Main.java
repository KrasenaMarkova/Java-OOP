package working_with_abstraction_exercise01.cardrank02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CardRang[] cardRangs = CardRang.values();

        System.out.println("Card Ranks:");

        for (CardRang cardRang : cardRangs) {
            int ordinal = cardRang.ordinal();
            System.out.printf("Ordinal value: %d; Name value: %s\n", ordinal, cardRang);
        }
    }
}
