package generics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UsingUpperAndLowerBoundedWildcards {

    /*In the previous lectures we have discussed wildcards -
    both upper bounded and lower bounded wildcards.
        In this exercise, your task is to implement copy method
            (it is present in Collections by the way)
    that's able to copy the items from a source list into a destination list.
    Use wildcards with the appropriate bounds!

    Hint: you have to copy the items from one of the lists into another list (so there are read and write operations accordingly).

    Good luck!*/
    /*public static <T> void copy(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }*/

    /**
     * When to use
     * <p>
     * there is so called get and put principle
     * use UPPER bounded wildcard(extends) when you only get values out of a structure or collection
     * use LOWER bounded wildcard(super) when you only put values into a structure or collection
     * <p>
     * DO NOT USE WILDCARDS if you want to DO READ AND WRITE as well
     * we use BOUNDED TYPE parameters in this cases
     */

    public static <T> void copy(List<? extends T> source, List<? super T> destination) {
        for (T t : source) {
            destination.add(t);
        }
    }

    /* public static <T extends String> void copy(List<T> source, List<T> dest) {
         for (T t : source) {
             dest.add(t);
         }
     }
 */
    interface Fruit {
    }

    static class Apple implements Fruit {
    }

    static class Pear implements Fruit {
    }

    static class Tomato implements Fruit {
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Adam", "dendi", "Kevin");
        List<String> destinationNames = new ArrayList<>();
        copy(names, destinationNames);
        System.out.println(destinationNames);

        listFun();
    }

    public static void listFun() {
        List<? extends Fruit> basket = List.of(new Apple(), new Pear(), new Tomato());
        List<Fruit> basket1 = new ArrayList<Fruit>();
        basket1.add(new Apple());
        basket1.add(new Pear());
        basket1.add(new Tomato());
// basket1.add(new Fruit()); //Compile time error
// basket1.add(new Object()); //Compile time error

        List<? extends Fruit> fridge = new ArrayList<>();

        List<? super Apple> basketSuper = List.of(new Apple(), new Pear());


        List<? super Apple> superBasket3 = new ArrayList<Object>();
        List<? super Apple> superBasket1 = List.of(new Apple(), new Pear(), new Object(), 123, 12.45);
//        List<Apple> basket2 = new ArrayList<Apple>();
//        basket2.add(new Apple());
//        basket2.add(new Apple());
//        basket2.add(new Apple());
//        List<Fruit> fridge2 = new ArrayList<Fruit>();
//
        copy(fridge, basket1);
    }
}
