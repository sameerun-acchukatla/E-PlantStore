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
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     Long id;
     String ShippingAddressName;
     String ShippingAddressStreet1;
     String ShippingAddressStreet2;
     String ShippingAddressCity;
     String ShippingAddressState;
     String ShippingAddressCountry;
     String ShippingAddressZipcode;

    @OneToOne
    private Order order;
}
