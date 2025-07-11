package ed.hluschenko.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;
   @Column(name="first_name")
    private String firstName;
   @Column(name = "last_name")
    private String lastName;
    private String phone;
}
