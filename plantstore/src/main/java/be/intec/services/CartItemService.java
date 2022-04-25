package be.intec.services;

import be.intec.models.*;

import java.util.List;

public interface CartItemService {

    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

    CartItem updateCartItem(CartItem cartItem);

    CartItem addPlantToCartItem(Plant plant, User user, int qty);

    CartItem findById(Long id);

    void removeCartItem(CartItem cartItem);

    CartItem save(CartItem cartItem);

    List<CartItem> findByOrder(Order order);
}
