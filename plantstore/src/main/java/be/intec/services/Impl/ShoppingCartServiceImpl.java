package be.intec.services.Impl;

import be.intec.models.CartItem;
import be.intec.models.ShoppingCart;
import be.intec.repositories.ShoppingCartRepository;
import be.intec.services.CartItemService;
import be.intec.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {


    private final CartItemService cartItemService;


    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartServiceImpl(CartItemService cartItemService, ShoppingCartRepository shoppingCartRepository) {
        this.cartItemService = cartItemService;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {

        BigDecimal cartTotal = new BigDecimal(0);

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        for (CartItem cartItem:cartItemList) {
            if (cartItem.getPlant().getInStockNumber() > 0){
                cartItemService.updateCartItem(cartItem);
                cartTotal = cartTotal.add(cartItem.getSubtotal());
            }
        }

        shoppingCart.setGrandTotal(cartTotal);
        shoppingCartRepository.save(shoppingCart);

        return shoppingCart;
    }

    @Override
    public void clearShoppingCart(ShoppingCart shoppingCart) {

        List<CartItem>cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        for (CartItem cartItem : cartItemList) {
            cartItem.setShoppingCart(null);
            cartItemService.save(cartItem);
        }

        shoppingCart.setGrandTotal(new BigDecimal(0));

        shoppingCartRepository.save(shoppingCart);
    }
}
