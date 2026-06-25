package org.yearup.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.yearup.models.CartItem;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.repository.ShoppingCartRepository;

import java.util.List;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }

    public ShoppingCart getByUserId(int userId) {

        // Create a ShoppingCart object.
        ShoppingCart cart = new ShoppingCart();

        // Get all cart rows that belong to this user.
        List<CartItem> cartItemList = shoppingCartRepository.findByUserId(userId);

        // Convert each database cart row into a ShoppingCartItem.
        for (CartItem cartItem : cartItemList) {

            // Look up the full product information for this cart item.
            Product product = productService.getById(cartItem.getProductId());

            ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
            shoppingCartItem.setProduct(product);
            shoppingCartItem.setQuantity(cartItem.getQuantity());

            // Store the item in the cart using the product id as the key.
            cart.getItems().put(product.getProductId(), shoppingCartItem);
        }

        return cart;
    }

    public ShoppingCart addProduct(int userId, int productid) {

        // Check if this product is already in the user's cart.
        CartItem cartItem = shoppingCartRepository.findByUserIdAndProductId(userId, productid);

        if (cartItem == null) {

            // If the product is not in the cart yet, create a new cart row.
            cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setProductId(productid);
            cartItem.setQuantity(1);
        } else {

            // If the product is already in the cart, increase the quantity by one.
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }

        // Save the new or updated cart row.
        shoppingCartRepository.save(cartItem);

        // Return the updated cart.
        return getByUserId(userId);
    }

    public ShoppingCart updateProduct(int userId, int productID, int quantity) {

        // Find the cart row for this user and product.
        CartItem cartItem = shoppingCartRepository.findByUserIdAndProductId(userId, productID);

        if (cartItem != null) {

            // Update the quantity and save the change.
            cartItem.setQuantity(quantity);
            shoppingCartRepository.save(cartItem);
        }

        // Return the updated cart.
        return getByUserId(userId);
    }

    @Transactional
    public ShoppingCart clearCart(int userId) {

        // Delete all cart rows that belong to this user.
        shoppingCartRepository.deleteByUserId(userId);

        // Return the user's now-empty cart.
        return getByUserId(userId);
    }
}
