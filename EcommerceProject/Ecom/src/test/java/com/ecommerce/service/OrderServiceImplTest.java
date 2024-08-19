package com.ecommerce.service;

import com.ecommerce.dao.OrderRepository;
import com.ecommerce.entity.Order;
import com.ecommerce.exceptions.OrderNotFoundException;
import com.ecommerce.serviceImpl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize test data
        order = new Order();
        order.setId(1);
        order.setUserId(123);
        order.setProductId(456);
        order.setQuantity(2);
        order.setOrderDate(LocalDateTime.now());
    }

    @Test
    void createOrder_ShouldReturnOrder() {
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);

        assertNotNull(createdOrder);
        assertEquals(1, createdOrder.getId());
        assertEquals(123, createdOrder.getUserId());

        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void updateOrder_ShouldReturnUpdatedOrder() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order updatedOrder = orderService.updateOrder(1, order);

        assertNotNull(updatedOrder);
        assertEquals(1, updatedOrder.getId());
        assertEquals(123, updatedOrder.getUserId());

        verify(orderRepository, times(1)).findById(anyInt());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void updateOrder_ShouldThrowException_WhenOrderNotFound() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.updateOrder(1, order));

        verify(orderRepository, times(1)).findById(anyInt());
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void deleteOrder_ShouldCallDeleteById() {
        when(orderRepository.existsById(anyInt())).thenReturn(true);

        orderService.deleteOrder(1);

        verify(orderRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void deleteOrder_ShouldThrowException_WhenOrderNotFound() {
        when(orderRepository.existsById(anyInt())).thenReturn(false);

        assertThrows(OrderNotFoundException.class, () -> orderService.deleteOrder(1));

        verify(orderRepository, times(1)).existsById(anyInt());
        verify(orderRepository, never()).deleteById(anyInt());
    }

    @Test
    void getOrderById_ShouldReturnOrder() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));

        Order foundOrder = orderService.getOrderById(1);

        assertNotNull(foundOrder);
        assertEquals(1, foundOrder.getId());

        verify(orderRepository, times(1)).findById(anyInt());
    }

    @Test
    void getOrderById_ShouldThrowException_WhenOrderNotFound() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(1));

        verify(orderRepository, times(1)).findById(anyInt());
    }

    @Test
    void getAllOrders_ShouldReturnListOfOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> orderList = orderService.getAllOrders();

        assertNotNull(orderList);
        assertEquals(1, orderList.size());

        verify(orderRepository, times(1)).findAll();
    }
}
