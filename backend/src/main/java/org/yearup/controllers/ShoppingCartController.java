package org.yearup.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;
import org.yearup.service.ShoppingCartService;
import org.yearup.service.UserService;

import java.security.Principal;


@RestController
@RequestMapping("/cart")
@PreAuthorize("hasRole('ROLE_USER')")
@CrossOrigin
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;
    private UserService userService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }


    @GetMapping
    public ShoppingCart getCart(Principal principal) {

        String userName = principal.getName();

        User user = userService.getByUserName(userName);
        int userId = user.getId();


        return shoppingCartService.getByUserId(userId);
    }


    @PostMapping("/products/{productId}")
    public ResponseEntity<ShoppingCart> addProduct(@PathVariable int productId, Principal principal) {
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        ShoppingCart cart = shoppingCartService.addProduct(userId, productId);

        return ResponseEntity.status(201).body(cart);
    }



    @PutMapping("/products/{productId}")
    public ResponseEntity<ShoppingCart> updateItem(@PathVariable int productId, @RequestBody ShoppingCartItem shoppingCartItem, Principal principal) {
        String userName = principal.getName();

        User user = userService.getByUserName(userName);
        int userId = user.getId();

        ShoppingCart cart = shoppingCartService.updateProduct(userId, productId, shoppingCartItem.getQuantity());
        return ResponseEntity.ok(cart);
    }



    @DeleteMapping
    public ResponseEntity<ShoppingCart> clearCart(Principal principal){
        String userName = principal.getName();

        User user = userService.getByUserName(userName);
        int userId = user.getId();

        ShoppingCart cart =shoppingCartService.clearCart(userId);

        return ResponseEntity.ok(cart);
    }
}
