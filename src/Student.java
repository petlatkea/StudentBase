public class Student {
    private String firstName;
    private String lastName;
    private String middleName;
    private String house;

    public Student(String firstName, String lastName, String middleName, String house) {
        this.firstName = firstName;
        this.lastName = lastName;

        if("".equals(middleName.trim())) {
            this.middleName = null;
        } else {
            this.middleName = middleName;
        }

        this.house = house;
    }

    private Student() {

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

    @Override
    public String toString() {
        if(middleName != null) {
            return firstName + " " + middleName + " " + lastName;
        } else {
            return firstName + " " + lastName;
        }
    }

    public String[] toStrings() {
        return new String[]{firstName, lastName, middleName, house};
    }

    public static String[] getStringNames() {
        return new String[]{"firstName", "lastName", "middleName", "house"};
    }

    public static Student fromStrings(String[] strings) {
        Student newStudent = new Student();

        newStudent.firstName = strings[0];
        newStudent.lastName = strings[1];
        newStudent.middleName = strings[2];
        newStudent.house = strings[3];

        return newStudent;
    }

    public boolean matches(String search) {
        return firstName.toLowerCase().contains(search) ||
               lastName.toLowerCase().contains(search) ||
               (middleName != null && middleName.toLowerCase().contains(search));
    }
}
