package Entity;

public class User {
    private int id;
    private String name;
    private int age;
    private int phoneNumber;
    private int role;

    public User() {
        this.id = 00000000;
        this.name = "未登录";
        this.age = 0;
        this.phoneNumber = 0;
        this.role = -1;
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

    public int getPhoneNumber() {
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

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
