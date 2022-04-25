package be.intec.repositories;

import be.intec.models.CartItem;
import be.intec.models.Order;
import be.intec.models.ShoppingCart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

    List<CartItem> findByOrder(Order order);
}
