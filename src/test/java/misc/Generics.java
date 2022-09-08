package misc;

public class Generics {
    public static <T extends Comparable<T>> T calculateMin(T num1, T num2) {
        if (num1.compareTo(num2) < 0) return num1;
        return num2;
    }

    public static void main(String[] args) {
        System.out.println(calculateMin(new Person("Adam", 111), new Person("Anna", 44)));
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

