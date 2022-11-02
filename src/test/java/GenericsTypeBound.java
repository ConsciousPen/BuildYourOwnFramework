public class GenericsTypeBound {
    /**
     *
     *     It is misleading because no matter we are dealing with classes
     *     or interfaces - we have to use the extends keyword
     *     and we can define multiple bounds.
     *
     *    <T extends B1 & B2 & B3>
     */
    public static <T extends Comparable<T>> T calculateMin(T num1, T num2) {
        if (num1.compareTo(num2) < 0) return num1;
        return num2;
    }

    public static <T extends Number> double add(T num1, T num2) {
        return num1.doubleValue() + num2.doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(calculateMin(new Person("Adam", 111), new Person("Anna", 44)));
        System.out.println(add(1.5, 3.14f));
    }

    public static class Person implements Comparable<Person> {
        private int age;
        private String name;

        public int getAge() {
            return this.age;
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Person otherPerson) {
            return Integer.compare(age, otherPerson.getAge());
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}

