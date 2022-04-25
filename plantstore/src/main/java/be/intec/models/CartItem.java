    package be.intec.models;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import lombok.AccessLevel;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.experimental.FieldDefaults;

    import javax.persistence.*;
    import java.math.BigDecimal;
    import java.util.List;
    import java.util.Objects;

    @Data
    @NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Entity
    public class CartItem {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private int qty;
        private BigDecimal subtotal;

        @OneToOne
        private Plant plant;

        @OneToMany(mappedBy = "cartItem")
        @JsonIgnore
        private List<PlantToCartItem> plantToCartItemList;

        @ManyToOne
        @JoinColumn(name="shopping_cart_id")
        private ShoppingCart shoppingCart;

        @ManyToOne
        @JoinColumn(name="order_id")
        private Order order;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CartItem cartItem = (CartItem) o;
            return Objects.equals(id, cartItem.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "CartItem{" +
                    "id=" + id +
                    ", qty=" + qty +
                    ", subtotal=" + subtotal +
                    ", plant=" + plant +
                    ", plantToCartItemList=" + plantToCartItemList +
                    ", shoppingCart=" + shoppingCart +
                    ", order=" + order +
                    '}';
        }
    }
