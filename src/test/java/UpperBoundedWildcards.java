import misc.wildcards.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpperBoundedWildcards {
    /*

         we are able to store any subtype of number.
         but java dont know what we are storing exactly

      // List<? extends Number> list = new ArrayList<>();
       // list.add(new Integer(23)); // because we are using upper bounded wildcards. You cannot gurantee what type of list it is reffering to

        // lower bounded wildcard works fine
        List<? super Number> list2 = new ArrayList<>();
        list2.add(new Integer(23)); // because we are using upper bounded wildcards. You cannot gurantee what type of list it is reffering to


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
    }
}
