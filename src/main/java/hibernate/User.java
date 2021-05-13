package hibernate;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer idUser;

    @Getter @Setter
    @Column(name="firstname")
    private String firstName;

    @Getter @Setter
    @Column(name="lastname")
    private String lastName;

    @Getter @Setter
    @Column(name="birthdate")
    private String birthDate;

    @Override
    public String toString() {
        return "\t" + firstName + " " + lastName + " " + birthDate ;
    }

    public User(String firstName, String lastName, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

}