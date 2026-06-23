package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.repository.OrderLineItemRepository;
import org.yearup.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineItemRepository orderLineItemRepository;
    private final ShoppingCartService shoppingCartService;

    public OrderService(OrderRepository orderRepository, OrderLineItemRepository orderLineItemRepository, ShoppingCartService shoppingCartService) {
        this.orderRepository = orderRepository;
        this.orderLineItemRepository = orderLineItemRepository;
        this.shoppingCartService = shoppingCartService;
    }
}
