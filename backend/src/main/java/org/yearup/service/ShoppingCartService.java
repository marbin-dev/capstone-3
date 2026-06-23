package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.CartItem;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.repository.ShoppingCartRepository;

import java.util.List;

@Service
public class ShoppingCartService {
    // a shopping cart is built from cart rows plus a product lookup for each row
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }


    public ShoppingCart getByUserId(int userId) {
        // load the user's cart rows, look up each product, and build the ShoppingCart
        ShoppingCart cart = new ShoppingCart();
        List<CartItem> cartItemList = shoppingCartRepository.findByUserId(userId);
        for (CartItem cartItem : cartItemList) {
            Product product = productService.getById(cartItem.getProductId());

            ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
            shoppingCartItem.setProduct(product);
            shoppingCartItem.setQuantity(cartItem.getQuantity());

            cart.getItems().put(product.getProductId(), shoppingCartItem);
        }

        return cart;
    }

    // add additional methods here
    public ShoppingCart addProduct(int userId, int productid) {
        CartItem cartItem = shoppingCartRepository.findByUserIdAndProductId(userId, productid);

        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setProductId(productid);
            cartItem.setQuantity(1);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }

        shoppingCartRepository.save(cartItem);
        return getByUserId(userId);
    }

    public ShoppingCart updateProduct(int userId, int productID, int quantity) {

        CartItem cartItem = shoppingCartRepository.findByUserIdAndProductId(userId, productID);

        if (cartItem != null) {
            cartItem.setQuantity(quantity);
            shoppingCartRepository.save(cartItem);

        }
        return getByUserId(userId);
    }
}
