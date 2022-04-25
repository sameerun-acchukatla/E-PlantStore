package be.intec.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name="user_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     Long id;
     Date orderDate;
     Date shippingDate;
     String shippingMethod;
     String orderStatus;
     BigDecimal orderTotal;

    @OneToMany(mappedBy = "order", cascade=CascadeType.ALL )
    private List<CartItem> cartItemList;

    @OneToOne(cascade=CascadeType.ALL)
    private ShippingAddress shippingAddress;

    @OneToOne(cascade=CascadeType.ALL)
    private BillingAddress billingAddress;

    @OneToOne(cascade=CascadeType.ALL)
    private Payment payment;

    @ManyToOne
    private User user;

    // Have to add equals() and Hash() methods & toString() also
}
