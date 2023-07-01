package tests;


public class CustomerDetails {
    private String name;
    private String lastname;
    private int age;

    public String getName() {
        return name;
    }

    public CustomerDetails setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public CustomerDetails setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public int getAge() {
        return age;
    }

    public CustomerDetails setAge(int age) {
        this.age = age;
        return this;
    }
}
