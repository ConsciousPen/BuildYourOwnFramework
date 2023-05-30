package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListExercise {
    static List<Integer> numbers = new ArrayList<Integer>() {{
        add(1);
        add(2);
        add(3);
        add(4);
        add(5);
        add(6);
    }};

    public static void reverse(List<Integer> list) {
        for(int startIndex=0, mid=list.size()/2, endIndex=list.size()-1; startIndex<mid; startIndex++,endIndex--) {

            int num1 = list.get(startIndex);
            int num2 = list.get(endIndex);
            list.set(startIndex, num2);
            list.set(endIndex, num1);
        }
    }

    public static void main(String args[]) {
        System.out.println("Numbers : " + numbers);
        reverse(numbers);
        System.out.println("Numbers after : " + numbers);

    }

    public ListExercise() {
    }
//    private static List<Integer> reverse(List<Integer> numbers) {
//        Collections.reverse(numbers);
//        return numbers;
//    }
}
