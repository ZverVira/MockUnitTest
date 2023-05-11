package com.epam.tamentoring.test;

import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnitTests {

    @Test
    public void addProductsToCart() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product(1, "Book", 30.0, 1);
        Product product2 = new Product(2, "Doll", 100.0, 1);
        products.add(product1);
        products.add(product2);
        ShoppingCart shoppingCart = new ShoppingCart(products);
        shoppingCart.addProductToCart(product1);
        shoppingCart.addProductToCart(product2);
        assertEquals(2, shoppingCart.getProducts().size());
    }

    @Test
    public void shouldThrowExceptionIfRemoveFromCartNonExistingProduct() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product(1, "Book", 30.0, 1);
        Product product2 = new Product(2, "Doll", 100.0, 1);
        Product product3 = new Product(3, "Apple", 5.0, 1);
        products.add(product1);
        products.add(product2);
        ShoppingCart shoppingCart = new ShoppingCart(products);
        shoppingCart.addProductToCart(product1);
        shoppingCart.addProductToCart(product2);
        Exception exception = assertThrows(ProductNotFoundException.class, () -> shoppingCart.removeProductFromCart(product3));
        assertEquals(String.format("Product is not found in the cart: %s", product3), exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionIfGetFromCartNonExistingProduct() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product(1, "Book", 30.0, 1);
        Product product2 = new Product(2, "Doll", 100.0, 1);
        Product product3 = new Product(3, "Apple", 5.0, 1);
        products.add(product1);
        products.add(product2);
        ShoppingCart shoppingCart = new ShoppingCart(products);
        shoppingCart.addProductToCart(product1);
        shoppingCart.addProductToCart(product2);
        Exception exception = assertThrows(ProductNotFoundException.class, () -> shoppingCart.getProductById(3));
        assertEquals(String.format("Product with %s ID is not found in the shopping cart!", product3.getId()), exception.getMessage());
    }

    @Test
    public void removeProductsFromCart() {
        List<Product> products = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart(products);
        Product product1 = new Product(1, "Book", 30.0, 1);
        Product product2 = new Product(2, "Doll", 100.0, 1);
        shoppingCart.addProductToCart(product1);
        shoppingCart.addProductToCart(product2);
        shoppingCart.removeProductFromCart(product2);
        assertEquals(1, shoppingCart.getProducts().size());
    }

    @Test
    public void getTotalCartPrice() {
        List<Product> products = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart(products);
        Product product1 = new Product(1, "Book", 30.0, 2);
        Product product2 = new Product(2, "Doll", 100.0, 1);
        shoppingCart.addProductToCart(product1);
        shoppingCart.addProductToCart(product2);
        assertEquals(160.0, shoppingCart.getCartTotalPrice());
    }
}
