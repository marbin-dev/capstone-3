let cartService;

class ShoppingCartService {

    cart = {
        items: [],
        total: 0
    };

    addToCart(productId)
    {
        const url = `${config.baseUrl}/cart/products/${productId}`;

        axios.post(url, {})
            .then(response => {
                this.setCart(response.data);
                this.updateCartDisplay();
                alert("Product added to cart!");
            })
            .catch(error => {
                const data = {
                    error: "Add to cart failed."
                };

                templateBuilder.append("error", data, "errors");
            });
    }

    setCart(data)
    {
        this.cart = {
            items: [],
            total: 0
        };

        this.cart.total = data.total;

        for (const [key, value] of Object.entries(data.items)) {
            this.cart.items.push(value);
        }
    }

    loadCart()
    {
        const url = `${config.baseUrl}/cart`;

        axios.get(url)
            .then(response => {
                this.setCart(response.data);
                this.updateCartDisplay();
            })
            .catch(error => {
                const data = {
                    error: "Load cart failed."
                };

                templateBuilder.append("error", data, "errors");
            });
    }

    loadCartPage()
    {
        // Clear the main section before rebuilding the cart page.
        const main = document.getElementById("main");
        main.innerHTML = "";

        // Create the checkout information box on the left side.
        let div = document.createElement("div");
        div.classList = "filter-box";

        let h2 = document.createElement("h2");
        h2.innerText = "Checkout";
        div.appendChild(h2);

        let p = document.createElement("p");
        p.innerText = "Review your items before placing your order.";
        div.appendChild(p);

        main.appendChild(div);

        // Create the main cart content area.
        const contentDiv = document.createElement("div");
        contentDiv.id = "content";
        contentDiv.classList.add("content-form");

        const cartHeader = document.createElement("div");
        cartHeader.classList.add("cart-header");

        const h1 = document.createElement("h1");
        h1.innerText = "Cart";
        cartHeader.appendChild(h1);

        const buttonContainer = document.createElement("div");

        const clearButton = document.createElement("button");
        clearButton.classList.add("btn");
        clearButton.classList.add("btn-danger");
        clearButton.innerText = "Clear";
        clearButton.addEventListener("click", () => this.clearCart());
        buttonContainer.appendChild(clearButton);

        const checkoutButton = document.createElement("button");
        checkoutButton.classList.add("btn");
        checkoutButton.classList.add("btn-success");
        checkoutButton.innerText = "Checkout";
        checkoutButton.addEventListener("click", () => this.checkout());
        buttonContainer.appendChild(checkoutButton);

        cartHeader.appendChild(buttonContainer);

        contentDiv.appendChild(cartHeader);
        main.appendChild(contentDiv);

        // Add each cart item to the cart page.
        this.cart.items.forEach(item => {
            this.buildItem(item, contentDiv);
        });
    }

    buildItem(item, parent)
    {
        let outerDiv = document.createElement("div");
        outerDiv.classList.add("cart-item");

        let div = document.createElement("div");
        outerDiv.appendChild(div);

        let h4 = document.createElement("h4");
        h4.innerText = item.product.name;
        div.appendChild(h4);

        let photoDiv = document.createElement("div");
        photoDiv.classList.add("photo");

        let img = document.createElement("img");
        img.src = `images/products/${item.product.imageUrl}`;
        img.alt = item.product.name;

        // If an image does not load, use the smartphone image as a backup.
        img.onerror = function () {
            this.src = "images/products/smartphone.jpg";
        };

        img.addEventListener("click", () => {
            showImageDetailForm(item.product.name, img.src);
        });

        photoDiv.appendChild(img);

        let priceH4 = document.createElement("h4");
        priceH4.classList.add("price");
        priceH4.innerText = `$${item.product.price}`;
        photoDiv.appendChild(priceH4);

        outerDiv.appendChild(photoDiv);

        let descriptionDiv = document.createElement("div");
        descriptionDiv.innerText = item.product.description;
        outerDiv.appendChild(descriptionDiv);

        let quantityDiv = document.createElement("div");
        quantityDiv.innerText = `Quantity: ${item.quantity}`;
        outerDiv.appendChild(quantityDiv);

        parent.appendChild(outerDiv);
    }

    clearCart()
    {
        const url = `${config.baseUrl}/cart`;

        axios.delete(url)
            .then(response => {
                this.cart = {
                    items: [],
                    total: 0
                };

                this.cart.total = response.data.total;

                for (const [key, value] of Object.entries(response.data.items)) {
                    this.cart.items.push(value);
                }

                this.updateCartDisplay();
                this.loadCartPage();
                alert("Cart cleared!");
            })
            .catch(error => {
                const data = {
                    error: "Empty cart failed."
                };

                templateBuilder.append("error", data, "errors");
            });
    }

    updateCartDisplay()
    {
        try {
            const itemCount = this.cart.items.length;
            const cartControl = document.getElementById("cart-items");

            cartControl.innerText = itemCount;
        }
        catch (e) {
            // If the cart display is not available, do nothing.
        }
    }

    checkout()
    {
        const url = `${config.baseUrl}/orders`;

        axios.post(url, {})
            .then(response => {
                const data = {
                    message: "Order placed successfully!"
                };

                templateBuilder.append("message", data, "messages");
                alert("Checkout successful! Your order has been placed.");

                // Clear the cart after successful checkout
                this.cart = {
                    items: [],
                    total: 0
                };

                this.updateCartDisplay();
                this.loadCartPage();
            })
            .catch(error => {
                const data = {
                    error: "Checkout failed. Please try again."
                };

                templateBuilder.append("error", data, "errors");
            });
    }
}

document.addEventListener('DOMContentLoaded', () => {
    cartService = new ShoppingCartService();

    if (userService.isLoggedIn()) {
        cartService.loadCart();
    }
});