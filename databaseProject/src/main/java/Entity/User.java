package Entity;

public class User {
    private int id;
    private String name;
    private int age;
    private String phoneNumber;
    private int role;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String gender;

    public User() {
        this.id = 00000000;
        this.name = "未登录";
        this.age = 0;
        this.phoneNumber = "00000000000";
        this.role = -1;
        this.gender = "unknown";
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getRole() {
        return role;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
