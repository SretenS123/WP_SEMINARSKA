package com.example.seminarksa_wp.web;

import com.example.seminarksa_wp.model.Event;
import com.example.seminarksa_wp.model.ShoppingCart;
import com.example.seminarksa_wp.model.Ticket;
import com.example.seminarksa_wp.model.User;
import com.example.seminarksa_wp.model.exceptions.EventIdNotExistException;
import com.example.seminarksa_wp.service.EventService;
import com.example.seminarksa_wp.service.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final EventService eventService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, EventService eventService) {
        this.shoppingCartService = shoppingCartService;
        this.eventService = eventService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest request,
                                      Authentication authentication,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        User user = (User) authentication.getPrincipal();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(user.getId());
        model.addAttribute("events", this.shoppingCartService.listAllEventsInShoppingCart(shoppingCart.getId()));
        model.addAttribute("bodyContent", "shopping-cart");
        return "master-template";

    }

    @PostMapping("/add-event/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest request, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            ShoppingCart shoppingCart = this.shoppingCartService.addEventsToShoppingCart(user.getId(), id);
            return "redirect:/shopping-cart";

        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }
    @RequestMapping("/delete/{id}")
    public String deleteProductInShoppingCart(@PathVariable Long id,Authentication authentication)
    {
      User user = (User) authentication.getPrincipal();
        this.shoppingCartService.deleteEventToShoppingCart(user.getId(), id);
        return "redirect:/shopping-cart";
    }
    @PostMapping("/buy/{id}")
    public String buyTicket(@PathVariable Long id,
                            @RequestParam Integer quantity,
                            Authentication authentication)
    {
        User user = (User) authentication.getPrincipal();

        this.shoppingCartService.buyTicket(user.getId(),id,quantity);
        return "redirect:/shopping-cart";
    }

}
