package be.intec.services.Impl;

import be.intec.models.CartItem;
import be.intec.models.Plant;
import be.intec.models.ShoppingCart;
import be.intec.repositories.ShoppingCartRepository;
import be.intec.services.CartItemService;
import org.junit.Test;
import org.mockito.verification.VerificationMode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ShoppingCartServiceImplTest {

    CartItemService cartItemService = mock(CartItemService.class);
    ShoppingCartRepository shoppingCartRepository = mock(ShoppingCartRepository.class);
    ShoppingCartServiceImpl shoppingCartService = new ShoppingCartServiceImpl(cartItemService,shoppingCartRepository);

    @Test
    public void testUpdateShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Plant plant1 = new Plant();
        plant1.setInStockNumber(5);
        CartItem cartItem1 = new CartItem();
        cartItem1.setPlant(plant1);
        cartItem1.setSubtotal(new BigDecimal("30.50"));

        Plant plant2 = new Plant();
        plant2.setInStockNumber(3);
        CartItem cartItem2 = new CartItem();
        cartItem2.setPlant(plant2);
        cartItem2.setSubtotal(new BigDecimal("10.60"));
        List<CartItem> cartItemList = List.of(cartItem1,cartItem2);

        when(cartItemService.findByShoppingCart(shoppingCart)).thenReturn(cartItemList);
        ShoppingCart updateShoppingCart = shoppingCartService.updateShoppingCart(shoppingCart);

        verify(cartItemService, times(2)).updateCartItem(any(CartItem.class));

        verify(shoppingCartRepository).save(shoppingCart);
        assertThat(updateShoppingCart.getGrandTotal()).isEqualTo(new BigDecimal("41.10"));


    }

    @Test
    public void testClearShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        CartItem cartItem1 = new CartItem();

        CartItem cartItem2 = new CartItem();
        List<CartItem> cartItemList = List.of(cartItem1,cartItem2);

        when(cartItemService.findByShoppingCart(shoppingCart)).thenReturn(cartItemList);
        shoppingCartService.clearShoppingCart(shoppingCart);

        assertThat(cartItem1.getShoppingCart()).isNull();
        assertThat(cartItem2.getShoppingCart()).isNull();

        verify(cartItemService,times(2)).save(any(CartItem.class));

        assertThat(shoppingCart.getGrandTotal()).isEqualTo(BigDecimal.ZERO);
        verify(shoppingCartRepository).save(shoppingCart);

    }
}