package be.intec.services.Impl;

import be.intec.models.Order;
import be.intec.models.User;
import be.intec.repositories.OrderRepository;
import be.intec.services.CartItemService;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class OrderServiceImplTest {

    private OrderRepository orderRepository = mock(OrderRepository.class);
    private CartItemService cartItemService = mock(CartItemService.class);

    private OrderServiceImpl orderService = new OrderServiceImpl(orderRepository,cartItemService);

    @Test
    public void testFindByIdReturnsValidOrder() {

        Order order = new Order();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order actualOrder = orderService.findById(1L);

        assertEquals(order,actualOrder);
    }

    @Test
    public void testFindByIdReturnsNull(){
        when(orderRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
        Order actualOrder = orderService.findById(1L);
        assertEquals(null,actualOrder);
    }

    @Test
    public void testCreateOrder() {
    }
}