package be.intec.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class UserPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     Long id;
     String type;
     String cardName;
     String cardNumber;
     int expiryMonth;
     int expiryYear;
     int cvc; // card varification code
     String holderName;
     boolean defaultPayment;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userPayment",orphanRemoval = true)
    private UserBilling userBilling;

    // equals() and HashCode() & toSting() should be added

}
