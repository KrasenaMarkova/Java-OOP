package working_with_abstraction_exercise01;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
/*Finally, you have unlocked the safe and reached the treasure! Inside there are all kinds of gems, cash in different currencies, and gold bullions. Next to you, there is a bag which unfortunately has limited space. You don’t have much time so you need to take as much wealth as possible! But to get a bigger amount of the most valuable items, you need to keep the following rules:
        •	The gold amount in your bag should always be more than or equal to the gem amount at any time
        •	The gem amount should always be more than or equal to the cash amount at any time
        If you read an item that breaks one of these rules you should not put it in the bag. You should always be careful not to exceed the overall bag’s capacity because it will tear down and you will lose everything! You will receive the content of the safe on a single line in the format "{item} {quantity}" pairs, separated by whitespace. You need to gather only three types of items:
        •	Cash - All three letter items
        •	Gem - All items which end on "Gem" (at least 4 symbols)
        •	Gold - this type has only one item with the name - "Gold"
        Each item that does not fall in one of the above categories is useless and you should skip it. Reading item’s names should be CASE-INSENSITIVE, except when the item is Cash. You should aggregate items’ quantities that have the same name.
        If you’ve kept the rules you should escape successfully with a bag full of wealth. Now it’s time to review what you have managed to get out of the safe. Print all the types ordered by the total amount in descending order. Inside a type, order the items first alphabetically in descending order and then by their amount in ascending order. Use the format described below for each type.
        Input
        •	On the first line, you will receive a number that represents the capacity of the bag.
        •	On the second line, you will receive a sequence of item and quantity pairs.
        Output
        Print only the types from which you have items in the bag ordered by Total Amount descending. Inside a type order, the items are first alphabetically in descending order and then by an amount in ascending order. Use the following format for each type:
        "<{type}> ${total amount}"
        "##{item} - {amount}" - each item on new line*/
//https://alpha.judge.softuni.org/contests/1576/compete#60

public class GreedyTimes06 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long numberOfCapacityOfTheBag = Long.parseLong(scanner.nextLine());
        String[] itemsAndQuality = scanner.nextLine().split("\\s+");

        var myBag = new LinkedHashMap<String, LinkedHashMap<String, Long>>();
        long gold = 0;
        long gem = 0;
        long money = 0;

        for (int i = 0; i < itemsAndQuality.length; i += 2) {
            String item = itemsAndQuality[i];
            long quality = Long.parseLong(itemsAndQuality[i + 1]);

            String whatIsTheItem = "";

            if (item.length() == 3) {
                whatIsTheItem = "Cash";
            } else if (item.toLowerCase().endsWith("gem")) {
                whatIsTheItem = "Gem";
            } else if (item.toLowerCase().equals("gold")) {
                whatIsTheItem = "Gold";
            }

            if (whatIsTheItem.equals("")) {
                continue;
            } else if (numberOfCapacityOfTheBag < myBag.values().stream().map(Map::values).flatMap(Collection::stream).mapToLong(e -> e).sum() + quality) {
                continue;
            }

            switch (whatIsTheItem) {
                case "Gem":
                    if (!myBag.containsKey(whatIsTheItem)) {
                        if (myBag.containsKey("Gold")) {
                            if (quality > myBag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (myBag.get(whatIsTheItem).values().stream().mapToLong(e -> e).sum() + quality > myBag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                        continue;
                    }
                    break;
                case "Cash":
                    if (!myBag.containsKey(whatIsTheItem)) {
                        if (myBag.containsKey("Gem")) {
                            if (quality > myBag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (myBag.get(whatIsTheItem).values().stream().mapToLong(e -> e).sum() + quality > myBag.get("Gem").values().stream().mapToLong(e -> e).sum()) {
                        continue;
                    }
                    break;
            }

//            if (!myBag.containsKey(whatIsTheItem)) {
//                myBag.put((whatIsTheItem), new LinkedHashMap<String, Long>());
//            }
            myBag.putIfAbsent((whatIsTheItem), new LinkedHashMap<String, Long>());
            myBag.get(whatIsTheItem).putIfAbsent(item, 0L);
          /*  if (!myBag.get(whatIsTheItem).containsKey(item)) {
                myBag.get(whatIsTheItem).put(item, 0L);
            }*/

            myBag.get(whatIsTheItem).put(item, myBag.get(whatIsTheItem).get(item) + quality);
            if (whatIsTheItem.equals("Gold")) {
                gold += quality;
            } else if (whatIsTheItem.equals("Gem")) {
                gem += quality;
            } else if (whatIsTheItem.equals("Cash")) {
                money += quality;
            }
        }

        for (var x : myBag.entrySet()) {
            Long sumValues = x.getValue().values().stream().mapToLong(l -> l).sum();

            System.out.println(String.format("<%s> $%s", x.getKey(), sumValues));

            x.getValue().entrySet().stream().sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())).forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));

        }
    }
}


