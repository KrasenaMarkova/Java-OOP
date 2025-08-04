package working_with_abstraction_lab01.pointinrectangle02;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Rectangle rectangle = parseRectangle(scanner.nextLine());

        int n = Integer.parseInt(scanner.nextLine());

        while (n-- > 0) {
            Point p = parsePoint(scanner.nextLine());
            boolean contains = rectangle.contains(p);
            System.out.println(contains);

        }
    }

    private static Point parsePoint(String s) {
        int[] coordination = parseArr(s);

        return new Point(coordination[0], coordination[1]);
    }

    private static Rectangle parseRectangle(String str) {
        int[] coordination = parseArr(str);
        Point a = new Point(coordination[0], coordination[1]);
        Point b = new Point(coordination[2], coordination[3]);

        return new Rectangle(a, b);
    }

    private static int[] parseArr(String str) {
        return Arrays.stream(str.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
