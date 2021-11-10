package temp;

import jakarta.persistence.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "testBean")
@RequestScoped
@Entity
@Table(name="testtable")
public class TestBean {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ids_gen")
    @SequenceGenerator(name="ids_gen", sequenceName = "pointids", allocationSize = 1)
    private Integer id;
    private String name;
    private String surname;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
