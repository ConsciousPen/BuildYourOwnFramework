package wildcards;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LowerBoundedWildcards {
    /**
     * We may want to use wildcards with supertypes so parent classes
     * this is usually useful when we want to insert items into a generic data structure or collection
     * <p>
     * printAll(List<? super T>)
     * this method can accept a list of any superclass of T
     * <p>
     * addItem(List<? super Integer>)
     * we can add an integer to the list with no problem
     * we can add Numbers or even Objects to the list because Number and Object are superclasses of Integer
     * <p>
     * we can not read items from a List<? super T> just Objects
     * we can insert (add) items into a list <? super T>
     * <p>
     * You can not read items from a list<? super T> because you can not guarantee what a list it is really pointing to - we can read Object exclusively.
     * We can insert subtypes of T into a List<? super T>
     */

    public static void showAll(List<? super Number> list) {
        for (Object num : list) { // no option but to use object.
            System.out.println(num);
        }
    }

    public static void main(String[] args) {
        List<? super Integer> l1 = new ArrayList<Integer>();
        List<? super Double> l2 = new ArrayList<Double>();
        List<? super Float> l3 = new ArrayList<Float>();

        List<Serializable> list = new ArrayList<>();
        list.add("adam");
        list.add("ana");
        list.add("dendi");

        showAll(list);
        List<? super Number> nums = new ArrayList<>();
        nums.add(3);
        nums.add(3.5);
        nums.add(3f);
    }
}
