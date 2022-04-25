package be.intec.controllers;

import be.intec.models.Plant;
import be.intec.models.User;
import be.intec.services.PlantService;
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
public class SearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlantService plantService;

    @RequestMapping("/searchByCategory")
    public String searchByCategory(
            @RequestParam("category") String category,
            Model model, Principal principal
    ){
        if(principal!=null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        String classActiveCategory = "active"+category;
        classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
        classActiveCategory = classActiveCategory.replaceAll("&", "");
        model.addAttribute(classActiveCategory, true);

        List<Plant> plantList = plantService.findByCategory(category);

        if (plantList.isEmpty()) {
            model.addAttribute("emptyList", true);
            return "plantshelf";
        }

        model.addAttribute("plantList", plantList);

        return "plantshelf";
    }

    @RequestMapping("/searchPlant")
    public String searchPlant(
            @ModelAttribute("keyword") String keyword,
            Principal principal, Model model
    ) {
        if(principal!=null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        List<Plant> plantList = plantService.blurrySearch(keyword);

        if (plantList.isEmpty()) {
            model.addAttribute("emptyList", true);

            return "plantshelf";
        }

        model.addAttribute("plantList", plantList);

        return "plantshelf";
    }
}
