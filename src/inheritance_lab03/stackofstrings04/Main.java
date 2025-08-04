package inheritance_lab03.stackofstrings04;

public class Main {
    public static void main(String[] args) {

        StackOfStrings strings = new StackOfStrings();

        strings.push("one");
        strings.push("two");
        strings.push("three");

        System.out.println(strings.isEmpty());
        System.out.println(strings.peek());

        System.out.println(strings.pop());
        System.out.println(strings.pop());
        System.out.println(strings.pop());

    }
}
