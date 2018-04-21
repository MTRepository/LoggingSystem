package pl.michal.logger.models;

// biblioteki lomboka
import lombok.Data;
import lombok.NoArgsConstructor;

// biblioteki jpa
import javax.persistence.*;

// encja - model danych
@Entity
@Table(name = "javatestuser")
@Data
@NoArgsConstructor
public class UserModel {

    // primary key - id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "nick")
    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;
}
