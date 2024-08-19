package com.ecommerce.service;

import com.ecommerce.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order updateOrder(int id, Order order);
    void deleteOrder(int id);
    Order getOrderById(int id);
    List<Order> getAllOrders();
}
