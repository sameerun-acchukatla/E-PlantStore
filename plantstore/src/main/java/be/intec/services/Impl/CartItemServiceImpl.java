package be.intec.services.Impl;

import be.intec.models.*;
import be.intec.repositories.CartItemRepository;
import be.intec.repositories.PlantToCartItemRepository;
import be.intec.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {


    private final CartItemRepository cartItemRepository;
    private final PlantToCartItemRepository plantToCartItemRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository, PlantToCartItemRepository plantToCartItemRepository) {
        this.cartItemRepository = cartItemRepository;
        this.plantToCartItemRepository = plantToCartItemRepository;
    }

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
                cartItem.setSubtotal(new BigDecimal(plant.getOurPrice()).multiply(new BigDecimal(cartItem.getQty())));
                cartItemRepository.save(cartItem);
                return  cartItem;
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(user.getShoppingCart());
        cartItem.setPlant(plant);

        cartItem.setQty(qty);
        cartItem.setSubtotal(new BigDecimal(plant.getOurPrice()).multiply(new BigDecimal(qty)));
        cartItemRepository.save(cartItem);

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
    @Transactional
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
