import misc.wildcards.Rectangle;
import misc.wildcards.Shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnboundedWildcards {
    /**
     for example we want to show the items in a List<Rectangle> when the rectangle is a shape

     Integer is a subtype of Number
        List<Integer> is NOT a subtype of List<Number>
     Despite the fact that integer is a number because of inheritance
     A LIST<INTEGER> IS NOT A SUBTYPE OF THE LIST<NUMBER> this is a reason we need wildcards.
     The super type of all kind of types collections are wildcards
    */

    /* Object is the parent class for Integer
     List<Object> is not parent class of List<Integer>*/
    public static void main(String[] args) {
        List<Integer> numbs = Arrays.asList(1, 2, 3);
        List<String> names = Arrays.asList("Adam", "Kevin", "Joe");
        print(numbs);
        print(names);
        List<Rectangle> rectangles = new ArrayList<>();
        drawAll(rectangles);

        List<? extends Number> l1 = new ArrayList<Integer>();
        print2(l1);
    }
/*
    public static void drawAll(List<T> shapes) {
        for(Shape shape : shapes){
            shape.draw();
        }
    }*/

    public static void print(List<?> list) {
        for (Object o : list)
            System.out.println(o);
    }
    public static <T extends Shape> void drawAll(List<T> shapes) {
        for(T shape : shapes){
            shape.draw();
        }
    }
    /** same :
    public static <T extends Shape> void drawAll(List<? extends Shape> shapes) {
        for(Shape shape : shapes){
            shape.draw();
        }
    }
    */

    public static <T extends Number> void print2(List<T> list) {
    }
    //  public <T super Integer> void print(List<T> list)  // Won't compile
}
