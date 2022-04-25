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
public class UserBilling {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     Long id;
     String userBillingName;
     String userBillingStreet1;
     String userBillingStreet2;
     String userBillingCity;
     String userBillingState;
     String userBillingCountry;
     String userBillingZipcode;

    @OneToOne(cascade= CascadeType.ALL)
    private UserPayment userPayment;

}
