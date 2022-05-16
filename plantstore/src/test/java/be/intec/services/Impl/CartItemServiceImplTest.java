package be.intec.services.Impl;

import be.intec.models.*;
import be.intec.repositories.CartItemRepository;
import be.intec.repositories.PlantToCartItemRepository;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CartItemServiceImplTest {

    private CartItemRepository cartItemRepository = mock(CartItemRepository.class);
    private PlantToCartItemRepository plantToCartItemRepository = mock(PlantToCartItemRepository.class);
    private CartItemServiceImpl cartItemService = new CartItemServiceImpl(cartItemRepository,  plantToCartItemRepository);

    @Test
    public void testFindByShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        List<CartItem> cartItemList = new ArrayList<>();

        when(cartItemRepository.findByShoppingCart(shoppingCart)).thenReturn(cartItemList);

        List<CartItem> cartItems = cartItemService.findByShoppingCart(shoppingCart);

        assertThat(cartItems).isSameAs(cartItemList);

    }

    @Test
    public void testUpdateCartItem() {

        CartItem cartItem = new CartItem();
        Plant plant = new Plant();
        plant.setOurPrice(30.50);
        cartItem.setPlant(plant);
        cartItem.setQty(2);

        CartItem updatedCartItem = cartItemService.updateCartItem(cartItem);
        BigDecimal subtotal = updatedCartItem.getSubtotal();

        assertThat(subtotal).isEqualTo(new BigDecimal("61.00"));
        verify(cartItemRepository).save(cartItem);

    }

    @Test
    public void testAddPlantToCartItem() {
        Plant plant1 = new Plant();
        plant1.setId(1L);
        plant1.setOurPrice(30.00);

        Plant plant2 = new Plant();
        plant2.setId(2L);
        plant2.setOurPrice(20.00);

        User user = new User();
        ShoppingCart shoppingCart = new ShoppingCart();
        user.setShoppingCart(shoppingCart);
        CartItem cartItem1 = new CartItem();
        cartItem1.setPlant(plant1);
        cartItem1.setQty(1);

        CartItem cartItem2 = new CartItem();
        cartItem2.setPlant(plant2);
        cartItem2.setQty(1);

        List<CartItem> cartItemList = List.of(cartItem1,cartItem2);

        when(cartItemRepository.findByShoppingCart(shoppingCart)).thenReturn(cartItemList);

        CartItem updatedCartItem = cartItemService.addPlantToCartItem(plant1, user, 1);

        assertThat(updatedCartItem.getQty()).isEqualTo(2);
        assertThat(updatedCartItem.getSubtotal()).isEqualTo(new BigDecimal("60"));

        verify(cartItemRepository).save(updatedCartItem);

    }

    @Test
    public void testAddNewPlantToCartItem() {
        Plant plant1 = new Plant();
        plant1.setId(1L);
        plant1.setOurPrice(30.00);

        Plant plant2 = new Plant();
        plant2.setId(2L);
        plant2.setOurPrice(20.00);

        Plant plant3 = new Plant();
        plant3.setId(3L);
        plant3.setOurPrice(10.00);

        User user = new User();
        ShoppingCart shoppingCart = new ShoppingCart();
        user.setShoppingCart(shoppingCart);
        CartItem cartItem1 = new CartItem();
        cartItem1.setPlant(plant1);
        cartItem1.setQty(1);

        CartItem cartItem2 = new CartItem();
        cartItem2.setPlant(plant2);
        cartItem2.setQty(1);

        List<CartItem> cartItemList = List.of(cartItem1,cartItem2);

        when(cartItemRepository.findByShoppingCart(shoppingCart)).thenReturn(cartItemList);
        CartItem updatedCartItem = cartItemService.addPlantToCartItem(plant3, user, 1);

        assertThat(updatedCartItem.getShoppingCart()).isSameAs(shoppingCart);
        assertThat(updatedCartItem.getPlant()).isSameAs(plant3);
        assertThat(updatedCartItem.getQty()).isEqualTo(1);
        assertThat(updatedCartItem.getSubtotal()).isEqualTo(new BigDecimal("10"));

        verify(cartItemRepository).save(updatedCartItem);
        verify(plantToCartItemRepository).save(any(PlantToCartItem.class));

    }

    @Test
    public void testFindByIdReturnsNull() {
        when(cartItemRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
        CartItem actualCartItem = cartItemService.findById(1L);
        assertEquals(null,actualCartItem);
    }

    @Test
    public void testFindByIdReturnsValidUser(){

        CartItem cartItem = new CartItem();
        when(cartItemRepository.findById(1L)).thenReturn(Optional.of(cartItem));

        CartItem actualCartItem = cartItemService.findById(1L);

        assertEquals(cartItem,actualCartItem);
    }

    @Test
    public void testRemoveCartItem() {
        CartItem cartItem = new CartItem();

        cartItemService.removeCartItem(cartItem);

        verify(plantToCartItemRepository).deleteByCartItem(cartItem);
        verify(cartItemRepository).delete(cartItem);

    }

    @Test
    public void testSave() {
        CartItem cartItem = new CartItem();
        when(cartItemRepository.save(cartItem)).thenReturn(cartItem);

        CartItem savedCartItem = cartItemService.save(cartItem);

        assertThat(savedCartItem).isSameAs(cartItem);

    }

    @Test
    public void testFindByOrder() {
        List<CartItem> cartItemList = new ArrayList<>();
        Order order = new Order();

        when(cartItemRepository.findByOrder(order)).thenReturn(cartItemList);
        List<CartItem> orderList = cartItemService.findByOrder(order);

        assertThat(cartItemList).isSameAs(orderList);
    }
}