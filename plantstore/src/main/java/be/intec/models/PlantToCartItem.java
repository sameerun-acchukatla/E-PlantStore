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
    public class PlantToCartItem {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        Long id;

        @ManyToOne
        @JoinColumn(name="plant_id")
        private Plant plant;

        @ManyToOne
        @JoinColumn(name="cart_item_id")
        private CartItem cartItem;

        // equals() and HashCode() & toString() should be added here

    }

