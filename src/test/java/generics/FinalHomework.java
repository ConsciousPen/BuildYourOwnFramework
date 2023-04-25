package generics;

import org.testng.annotations.Test;

import java.util.*;

public class FinalHomework {

    @Test
    public void runExercise() {
        // you have to implement the other classes such that the following code can run without any error
        Library<Algorithm> library = new Library<>();
        library.add(new SearchAlgorithm());
        library.add(new SortingAlgorithm());
        library.add(new GraphAlgorithm());

        Algorithm item = library.getLast();

        while (item != null) {
            item.execute();
            item = library.getLast();
        }
    }

    class SearchAlgorithm implements Algorithm {
        @Override
        public void execute() {
            System.out.println("SortingAlgorithm");
        }
    }

    class SortingAlgorithm implements Algorithm {
        @Override
        public void execute() {
            System.out.println("SortingAlgorithm");
        }
    }

    class GraphAlgorithm implements Algorithm {
        @Override
        public void execute() {
            System.out.println("GraphAlgorithm");
        }
    }

    static class Library<A extends Algorithm> {
        ArrayList<A> storage;

        public Library() {
            this.storage = new ArrayList<>();
        }

        public ArrayList<A> getStorage() {
            return storage;
        }

        public A getLast() {
            if (getStorage().size() <= 0) return null;

            A actualItem = getStorage().remove(getStorage().size() - 1);
            return actualItem;
        }

        public <T extends A> void add(T algo) {
            getStorage().add(algo);
        }
    }

    interface Algorithm {
        void execute();
    }
}
