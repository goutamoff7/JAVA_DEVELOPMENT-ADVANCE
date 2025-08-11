package myPackage;
import jakarta.persistence.*;


@Entity
@Table(name="student_details")
public class Student
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_id")
    int id;
    String name;

    @Embedded  // Certificate class has declared with @Embeddable annotation
    // so @Embedded is optional but mentioning this is a good practice
    private Certificate crtf;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Certificate getCrtf() {
        return crtf;
    }

    public void setCrtf(Certificate crtf) {
        this.crtf = crtf;
    }
}
