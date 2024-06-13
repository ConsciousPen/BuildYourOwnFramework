package reading_files;

public class ContactData implements Comparable<ContactData> {
    private String firstName;
    private String lastName;
    private String address1;
    private String home;
    private String mobilephonenumber;
    private String workphonenumber;
    private String email1;
    private String email2;
    private String byear;
    private String secondaryaddress;
    private String secondaryhomephonenumber;
    private String id;

    public ContactData(String firstName, String lastName, String address1,
                       String home, String mobilePhoneNumber, String workPhoneNumber,
                       String email1, String email2, String bYear,
                       String secondaryAddress, String secondaryHomePhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.home = home;
        this.mobilephonenumber = mobilePhoneNumber;
        this.workphonenumber = workPhoneNumber;
        this.email1 = email1;
        this.email2 = email2;
        this.byear = bYear;
        this.secondaryaddress = secondaryAddress;
        this.secondaryhomephonenumber = secondaryHomePhoneNumber;
    }

    public ContactData() {
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public ContactData withHome(String home) {
        this.home = home;
        return this;
    }

    public ContactData withMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilephonenumber = mobilePhoneNumber;
        return this;
    }

    public ContactData withWorkPhoneNumber(String workPhoneNumber) {
        this.workphonenumber = workPhoneNumber;
        return this;
    }

    public ContactData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withBirthDayYear(String bYear) {
        this.byear = bYear;
        return this;
    }

    public ContactData withSecondaryAddress(String secondaryAddress) {
        this.secondaryaddress = secondaryAddress;
        return this;
    }

    public ContactData withSecondaryPhoneNumber(String secondaryHomePhoneNumber) {
        this.secondaryhomephonenumber = secondaryHomePhoneNumber;
        return this;
    }

    @Override
    public String toString() {
        return "GroupData [name= " + firstName + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        return firstName.equals(that.firstName);

    }

    @Override
    public int hashCode() {
        return firstName.hashCode();
    }

    public int compareTo(ContactData other) {
        return (firstName).toLowerCase().compareTo((other.firstName).toLowerCase());
    }


    /**
     * Header / Setter block
     */

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getMobilephonenumber() {
        return mobilephonenumber;
    }

    public void setMobilephonenumber(String mobilephonenumber) {
        this.mobilephonenumber = mobilephonenumber;
    }

    public String getWorkphonenumber() {
        return workphonenumber;
    }

    public void setWorkphonenumber(String workphonenumber) {
        this.workphonenumber = workphonenumber;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getByear() {
        return byear;
    }

    public void setByear(String byear) {
        this.byear = byear;
    }

    public String getSecondaryaddress() {
        return secondaryaddress;
    }

    public void setSecondaryaddress(String secondaryaddress) {
        this.secondaryaddress = secondaryaddress;
    }

    public String getSecondaryhomephonenumber() {
        return secondaryhomephonenumber;
    }

    public void setSecondaryhomephonenumber(String secondaryhomephonenumber) {
        this.secondaryhomephonenumber = secondaryhomephonenumber;
    }

    public String getId() {
        return id;
    }

    public ContactData setId(String id) {
        this.id = id;
        return this;
    }
}