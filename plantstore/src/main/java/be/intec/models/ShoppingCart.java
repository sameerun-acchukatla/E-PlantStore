    package be.intec.models;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import lombok.AccessLevel;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.experimental.FieldDefaults;

    import javax.persistence.*;
    import java.math.BigDecimal;
    import java.util.List;

    @Data
    @NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Entity
    public class ShoppingCart {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        Long id;
        BigDecimal GrandTotal;

        @OneToMany(mappedBy="shoppingCart", cascade= CascadeType.ALL, fetch=FetchType.LAZY)
        @JsonIgnore
        private List<CartItem> cartItemList;

        @OneToOne(cascade = CascadeType.ALL)
        private User user;

    }
