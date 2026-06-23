package org.yearup.controllers;


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
    public Order checkout(Principal principal){
        String userName = principal.getName();

        User user = userService.getByUserName(userName);
        int userId = user.getId();

        return orderService.checkout(userId);
    }

}
