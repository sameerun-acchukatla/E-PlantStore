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
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     Long id;
     String type;
     String cardName;
     String cardNumber;
     int expiryMonth;
     int expiryYear;
     int cvc; // card verification code
     String holderName;

    @OneToOne
    private Order order;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userPayment")
    private UserBilling userBilling;
}
