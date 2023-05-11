package com.epam.tamentoring.test;

import com.epam.tamentoring.bo.DiscountUtility;
import com.epam.tamentoring.bo.OrderService;
import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.bo.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class OrderServiceMockTest {

    @Mock
    private DiscountUtility discountUtility;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMock() {
        List<Product> products = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart(products);
        UserAccount user = new UserAccount("John", "Smith", "1990/10/10", shoppingCart);
        when(discountUtility.calculateDiscount(user)).thenReturn(3.0);

        double actualOrderPrice = orderService.getOrderPrice(user);
        double expectedOrderPrice = user.getShoppingCart().getCartTotalPrice() - 3.0;

        assertEquals(expectedOrderPrice,actualOrderPrice);

        verify(discountUtility, times(1)).calculateDiscount(user);
        verifyNoMoreInteractions(discountUtility);

    }
}
