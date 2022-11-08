import java.io.Serializable;

public class TypeErasure {
    /**
     *      In order to implement generics, Java uses type erasure
     * this is how generic Java code is handled.
     *      - replace all type parameters in generic types with their bounds or Object
     * if the type parameters are unbounded
     *      - uses type casts if necessary
     *      - sometimes it generates extra methods : the so called bridge methodss
     * in order to maintain polymorphism with generic types
     *
     *      In the bytecode the following code is equivalent
     *      List<Integer> list = new ArrayList();
     *      list.add(3);
     *      Integer num = list.get(0);
     *      -------------------------------------
     *      List list = new ArrayList<>();
     *      list.add(3);
     *      Integer num = (Integer) list.get(0);
     *
     * @param args
     */
    public static void main(String[] args) {
        FirstScore fs = new FirstScore();
        fs.setItem(new String("ddd"));

        SecondScore ss = new SecondScore();
        ss.setItem("ddd");
    }
}

class FirstScore<T> { // same with FirstScore<Object>
    private T item; // Object item

    public T getItem() { // Object getItem()
        return this.item;
    }

    public void setItem(T item) {// Object item
        this.item = item;
    }

}

class SecondScore<T extends Serializable> { // same with SecondScore<Serializable>
    private T item; // Serializable item

    public T getItem() { // Serializable getItem()
        return this.item;
    }

    public void setItem(T item) {// Serializable item
        this.item = item;
    }

}