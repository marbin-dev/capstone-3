package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.Order;
import org.yearup.models.OrderLineItem;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.repository.OrderLineItemRepository;
import org.yearup.repository.OrderRepository;

import java.time.LocalDateTime;

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

    public Order checkout(int userId) {

        ShoppingCart cart = shoppingCartService.getByUserId(userId);

        Order order = new Order();
        order.setUserId(userId);
        order.setDate(LocalDateTime.now());

        Order saveOrder = orderRepository.save(order);

        for (ShoppingCartItem item : cart.getItems().values()) {
            OrderLineItem lineItem = new OrderLineItem();

            lineItem.setOrderId(saveOrder.getOrderId());
            lineItem.setProductId(item.getProduct().getProductId());
            lineItem.setSalesPrice(item.getProduct().getPrice());
            lineItem.setQuantity(item.getQuantity());
            lineItem.setDiscount(item.getDiscountPercent());

            orderLineItemRepository.save(lineItem);

        }

        shoppingCartService.clearCart(userId);

        return saveOrder;
    }
}
