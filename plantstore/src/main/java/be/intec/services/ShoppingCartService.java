package be.intec.services;

import be.intec.models.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);

    void clearShoppingCart(ShoppingCart shoppingCart);
}
