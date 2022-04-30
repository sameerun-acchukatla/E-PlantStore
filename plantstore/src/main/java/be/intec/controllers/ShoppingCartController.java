package be.intec.controllers;

import be.intec.models.CartItem;
import be.intec.models.Plant;
import be.intec.models.ShoppingCart;
import be.intec.models.User;
import be.intec.services.CartItemService;
import be.intec.services.PlantService;
import be.intec.services.ShoppingCartService;
import be.intec.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private PlantService plantService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping("/cart")
    public String shoppingCart(Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        ShoppingCart shoppingCart = user.getShoppingCart();

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        shoppingCartService.updateShoppingCart(shoppingCart);

        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("shoppingCart", shoppingCart);

        return "shoppingCart";
    }


    @RequestMapping("/addItem")
    public String addItem(
            @ModelAttribute("plant") Plant plant,@ModelAttribute("qty") String qty,Model model, Principal principal){

        User user = userService.findByUsername(principal.getName());
        plant = plantService.findById(plant.getId());

        if (Integer.parseInt(qty) > plant.getInStockNumber()) {
            model.addAttribute("notEnoughStock", true);
            return "forward:/plantDetail?id=" + plant.getId();
        }

        CartItem cartItem = cartItemService.addPlantToCartItem(plant, user, Integer.parseInt(qty));
        model.addAttribute("addPlantSuccess", true);

        return "forward:/plantDetail?id=" + plant.getId();
    }

    @RequestMapping("/updateCartItem")
    public String updateShoppingCart(@ModelAttribute("id") Long cartItemId,@ModelAttribute("qty") int qty) {

        CartItem cartItem = cartItemService.findById(cartItemId);
        cartItem.setQty(qty);
        cartItemService.updateCartItem(cartItem);

        return "forward:/shoppingCart/cart";
    }

    @RequestMapping("/removeItem")
    public String removeItem(@RequestParam("id") Long id) {

        CartItem cartItem = cartItemService.findById(id);
        cartItemService.removeCartItem(cartItem);

        return "forward:/shoppingCart/cart";
    }
}
