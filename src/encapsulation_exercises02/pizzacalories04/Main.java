package encapsulation_exercises02.pizzacalories04;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");

        String namePizza = input[1];
        int numToppings = Integer.parseInt(input[2]);

        Pizza pizza = new Pizza(namePizza,numToppings);

        input = scanner.nextLine().split("\\s+");

        String flourType = input[1];
        String bakingTechnique = input[2];
        double weight = Double.parseDouble(input[3]);

        pizza.setDough(new Dough(flourType,bakingTechnique,weight));

        input = scanner.nextLine().split("\\s+");

        while(!input[0].equals("END")){

            String toppingType = input[1];
            double weightTopping = Double.parseDouble(input[2]);

            pizza.addTopping(new Topping(toppingType,weightTopping));

            input = scanner.nextLine().split("\\s+");
        }

        System.out.printf("%s - %.2f",pizza.getName(),pizza.getOverallCalories());
    }
}
