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

        // Get the logged-in user's username from the JWT token.
        String userName = principal.getName();

        // Use the username to find the user id.
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        // Return the shopping cart that belongs to this user.
        return shoppingCartService.getByUserId(userId);
    }

    @PostMapping("/products/{productId}")
    public ResponseEntity<ShoppingCart> addProduct(@PathVariable int productId, Principal principal) {

        // Get the logged-in user's username from the JWT token.
        String userName = principal.getName();

        // Use the username to find the user id.
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        // Add the selected product to this user's cart.
        ShoppingCart cart = shoppingCartService.addProduct(userId, productId);

        // Return status 201 because a cart item was created or added.
        return ResponseEntity.status(201).body(cart);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<ShoppingCart> updateItem(@PathVariable int productId, @RequestBody ShoppingCartItem shoppingCartItem, Principal principal) {

        // Get the logged-in user's username from the JWT token.
        String userName = principal.getName();

        // Use the username to find the user id.
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        // Update the quantity for the selected product in this user's cart.
        ShoppingCart cart = shoppingCartService.updateProduct(userId, productId, shoppingCartItem.getQuantity());

        // Return the updated cart.
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping
    public ResponseEntity<ShoppingCart> clearCart(Principal principal) {

        // Get the logged-in user's username from the JWT token.
        String userName = principal.getName();

        // Use the username to find the user id.
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        // Remove all items from this user's cart.
        ShoppingCart cart = shoppingCartService.clearCart(userId);

        // Return the empty updated cart.
        return ResponseEntity.ok(cart);
    }
}
