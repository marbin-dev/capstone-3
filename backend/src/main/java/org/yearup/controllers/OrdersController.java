package org.yearup.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yearup.models.Order;
import org.yearup.models.User;
import org.yearup.service.OrderService;
import org.yearup.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/orders")
@PreAuthorize("hasRole('ROLE_USER')")
@CrossOrigin
public class OrdersController {

    private OrderService orderService;
    private UserService userService;

    public OrdersController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Order> checkout(Principal principal){

        // Get the logged-in user's username from the JWT token.
        String userName = principal.getName();

        // Use the username to find the user id.
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        // Create an order from this user's current shopping cart.
        Order order = orderService.checkout(userId);

        // Return status 201 because a new order was created.
        return ResponseEntity.status(201).body(order);
    }

}
