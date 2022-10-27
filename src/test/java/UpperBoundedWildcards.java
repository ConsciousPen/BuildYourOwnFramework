import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpperBoundedWildcards {
    /**
    You can read only with UpperBounded, but not add something
    we may want to use wildcards with subtypes so child classes

    we are able to store any subtype of number.
    but java dont know what we are storing exactly

    printAll(List<?> extends T>)
    this method can accept a list of any subclass of T

    What if we want to add an item using an Upper bound?

       List<? extends Number> list = new ArrayList<>();
       list.add(new Integer(23));
       because we are using upper bounded wildcards.
       You cannot gurantee what type of list it is reffering to

    We cannot add an Integer to the list because thy type cannot be guranteed - it may be List<Double>
    We cannot add an Double to the list because thy type cannot be guranteed - it may be List<Integer>

    Lower bounded wildcard will works fine. See class LowerBoundedWildcards
        List<? super Number> list2 = new ArrayList<>();
        list2.add(new Integer(23));

    we can read items from a List<? extends T>, we can not insert (add) items into a List<? extends T>
    you can not guarantee what list it is really pointing to.
    The only thing you can do for sure is to read the items.

     DOES NOT PROVIDE IMMUTABILITY, you can add NULL or execute other operations like sorting
     */

    public static void showAll(List<? extends Number> list) {
        for (Number n : list) {
            System.out.println(n);
        }
    }

    public static double sumAll(List<? extends Number> list) {
        double sum = 0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        return sum;
    }

    public static void main(String[] args) {
        List<? extends Number> l1 = new ArrayList<Integer>();
        List<? extends Number> l2 = new ArrayList<Double>();
        List<? extends Number> l3 = new ArrayList<Float>();

        showAll(l1);
        showAll(l2);
        showAll(l3);

        List<Integer> nums = new ArrayList<>();
        showAll(nums);

        showAll(Arrays.asList(1, 2.2, 3f, 4l));
        System.out.println(sumAll(Arrays.asList(1, 2.2, 3f, 4l)));

        List<? extends Number> list2 = new ArrayList<>();
        //list2.add(new Integer(23));

    }

}
