package generics;

import java.util.ArrayList;
import java.util.List;

public class GenericsTypeInference {
  /*
        Compilers ability to look at each method invocation and corresponding declaration
   to determine the type argument/s such as T that make the invocation applicable

         the inference algo determines the types of args the type that the result
   is being assigned or returned if available

    public <T> T getData(T t1, T t2){
        return t2;
    }

   Serializable s = getData("Hello", new ArrayList<String>());

   because of the type inference algorithm we can use instantiation like this :
   List<String> list  = new ArrayList();

        Generic methods and type interference:
    Enables us to invoke a generic method as you would an ordinary method,
    without specifying a type between angle brackets
   */

    public static <T> void addStore(T t, List<Bucket<T>> list) {
        Bucket<T> bucket = new Bucket<>();
        bucket.setItem(t);
        list.add(bucket);
        System.out.println(t.toString() + " has been added to the list...");
    }

    public static void main(String[] args) {
        List<Bucket<String>> list = new ArrayList<>();
        GenericsTypeInference.addStore("adam", list);
        GenericsTypeInference.<String>addStore("adam", list);
    }
}

class Bucket<T> {
    private T item;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
