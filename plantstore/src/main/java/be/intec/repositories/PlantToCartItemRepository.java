package be.intec.repositories;

import be.intec.models.CartItem;
import be.intec.models.PlantToCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantToCartItemRepository extends JpaRepository<PlantToCartItem,Long> {

    void deleteByCartItem(CartItem cartItem);
}
