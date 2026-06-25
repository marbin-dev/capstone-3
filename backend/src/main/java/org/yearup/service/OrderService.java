package org.yearup.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.models.*;
import org.yearup.repository.OrderLineItemRepository;
import org.yearup.repository.OrderRepository;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineItemRepository orderLineItemRepository;
    private final ShoppingCartService shoppingCartService;
    private final ProfileService profileService;

    public OrderService(OrderRepository orderRepository, OrderLineItemRepository orderLineItemRepository, ShoppingCartService shoppingCartService, ProfileService profileService) {
        this.orderRepository = orderRepository;
        this.orderLineItemRepository = orderLineItemRepository;
        this.shoppingCartService = shoppingCartService;
        this.profileService = profileService;
    }

    public Order checkout(int userId) {
        // Get the current user's shopping cart.
        ShoppingCart cart = shoppingCartService.getByUserId(userId);

        // Do not create an order if the cart is empty.
        if(cart.getItems().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cart is Empty bro");
        }

        // Get the user's profile so the order can use their shipping information.
        Profile profile = profileService.getByUserId(userId);

        // Create a new order using the user's profile information.
        Order order = new Order();
        order.setUserId(userId);
        order.setDate(LocalDateTime.now());
        order.setAddress(profile.getAddress());
        order.setCity(profile.getCity());
        order.setState(profile.getState());
        order.setZip(profile.getZip());
        order.setShippingAmount(0);

        // Save the order first so it gets an order id.
        Order saveOrder = orderRepository.save(order);

        // Create one order line item for each item in the cart.
        for (ShoppingCartItem item : cart.getItems().values()) {
            OrderLineItem lineItem = new OrderLineItem();

            lineItem.setOrderId(saveOrder.getOrderId());
            lineItem.setProductId(item.getProduct().getProductId());
            lineItem.setSalesPrice(item.getProduct().getPrice());
            lineItem.setQuantity(item.getQuantity());
            lineItem.setDiscount(item.getDiscountPercent());

            orderLineItemRepository.save(lineItem);

        }
        // Clear the cart after the order is created.
        shoppingCartService.clearCart(userId);

        // Return the saved order to the controller.
        return saveOrder;
    }
}
