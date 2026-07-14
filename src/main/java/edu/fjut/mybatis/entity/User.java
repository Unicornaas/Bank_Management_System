package edu.fjut.mybatis.entity;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String gender;
    private Integer age;
    private String card;
    private String phone;
    private Double money;
    private String role;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getCard() { return card; }
    public void setCard(String card) { this.card = card; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Double getMoney() { return money; }
    public void setMoney(Double money) { this.money = money; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', name='" + name +
                "', gender='" + gender + "', age=" + age + "', money=" + money + "}";
    }
}
