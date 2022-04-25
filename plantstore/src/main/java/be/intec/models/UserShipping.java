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
public class UserShipping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     Long id;
     String userShippingName;
     String userShippingStreet1;
     String userShippingStreet2;
     String userShippingCity;
     String userShippingState;
     String userShippingCountry;
     String userShippingZipcode;
     boolean userShippingDefault;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // toString(), equals() and hashcode() should be added if necessary
}
