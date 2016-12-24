package database.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @ElementCollection
    @Column(name="methods")
    private List<String> methods = new ArrayList<>();

    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getLogin() {
        return this.login;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public User setPassword(String pass) {
        this.password = pass;
        return this;
    }

    public List<String> getMethods() {
        return methods;
    }

    public User setMethods(List<String> methods) {
        this.methods = methods;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(login).append("]");
        return sb.toString();
    }
}
