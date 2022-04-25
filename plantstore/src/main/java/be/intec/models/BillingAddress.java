package be.intec.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class BillingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     Long id;
     String BillingAddressName;
     String BillingAddressStreet1;
     String BillingAddressStreet2;
     String BillingAddressCity;
     String BillingAddressState;
     String BillingAddressCountry;
     String BillingAddressZipcode;

    @OneToOne
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillingAddress that = (BillingAddress) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BillingAddress{" +
                "id=" + id +
                ", BillingAddressName='" + BillingAddressName + '\'' +
                ", BillingAddressStreet1='" + BillingAddressStreet1 + '\'' +
                ", BillingAddressStreet2='" + BillingAddressStreet2 + '\'' +
                ", BillingAddressCity='" + BillingAddressCity + '\'' +
                ", BillingAddressState='" + BillingAddressState + '\'' +
                ", BillingAddressCountry='" + BillingAddressCountry + '\'' +
                ", BillingAddressZipcode='" + BillingAddressZipcode + '\'' +
                ", order=" + order +
                '}';
    }
}
