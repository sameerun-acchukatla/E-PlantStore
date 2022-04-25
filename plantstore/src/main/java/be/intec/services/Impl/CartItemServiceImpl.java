package be.intec.services.Impl;

import be.intec.models.*;
import be.intec.repositories.CartItemRepository;
import be.intec.repositories.PlantToCartItemRepository;
import be.intec.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private PlantToCartItemRepository plantToCartItemRepository;

    @Override
    public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {

        return cartItemRepository.findByShoppingCart(shoppingCart);
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {

        BigDecimal bigDecimal = new BigDecimal(cartItem.getPlant().getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));

        bigDecimal = bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
        cartItem.setSubtotal(bigDecimal);

        cartItemRepository.save(cartItem);

        return cartItem;
    }

    @Override
    public CartItem addPlantToCartItem(Plant plant, User user, int qty) {

        List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());

        for (CartItem cartItem : cartItemList){
            if (plant.getId() == cartItem.getPlant().getId())
            {
                cartItem.setQty(cartItem.getQty() + qty);
                cartItem.setSubtotal(new BigDecimal(plant.getOurPrice()).multiply(new BigDecimal(qty)));
                cartItemRepository.save(cartItem);
                return  cartItem;
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(user.getShoppingCart());
        cartItem.setPlant(plant);

        cartItem.setQty(qty);
        cartItem.setSubtotal(new BigDecimal(plant.getOurPrice()).multiply(new BigDecimal(qty)));
        cartItem = cartItemRepository.save(cartItem);

        PlantToCartItem plantToCartItem = new PlantToCartItem();
        plantToCartItem.setPlant(plant);
        plantToCartItem.setCartItem(cartItem);
        plantToCartItemRepository.save(plantToCartItem);

        return cartItem;
    }

    @Override
    public CartItem findById(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    @Override
    public void removeCartItem(CartItem cartItem) {

        plantToCartItemRepository.deleteByCartItem(cartItem);
        cartItemRepository.delete(cartItem);

    }

    @Override
    public CartItem save(CartItem cartItem) {

        return cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItem> findByOrder(Order order) {

        return cartItemRepository.findByOrder(order);
    }








}
