import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
     *
     * there is so called get and put principle
     *  use UPPER bounded wildcard(extends) when you only get values out of a structure or collection
     *  use LOWER bounded wildcard(super) when you only put values into a structure or collection
     *
     *  DO NOT USE WILDCARDS if you wnat to DO READ AND WRITE as well
     *  we use BOUNDED TYPE parameters in this cases

     */

    public static <T> void copy(List<? extends T> source, List<? super T> destination) {
        for (T t : source) {
            destination.add(t);
        }
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Adam", "dendi", "Kevin");
        List<String> destinationNames = new ArrayList<>();
        copy(names, destinationNames);
        System.out.println(destinationNames);
    }
}
