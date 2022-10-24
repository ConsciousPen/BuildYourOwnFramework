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
