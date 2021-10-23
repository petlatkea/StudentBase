public class Student {
    private String firstName;
    private String lastName;
    private String middleName;
    private String house;

    public Student(String firstName, String lastName, String middleName, String house) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.house = house;
    }

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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
}
