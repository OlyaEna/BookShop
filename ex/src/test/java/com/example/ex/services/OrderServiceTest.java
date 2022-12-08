package com.example.ex.services;

import com.example.ex.dto.GenreDto;
import com.example.ex.model.entity.Genre;
import com.example.ex.model.entity.Order;
import com.example.ex.model.repository.GenreRepository;
import com.example.ex.model.repository.OrderRepository;
import com.example.ex.service.UserService;
import com.example.ex.service.impl.GenreServiceImpl;
import com.example.ex.service.impl.OrderServiceImpl;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderServiceImpl orderService;
    private  UserService userService;


    @BeforeEach
    void setUp() {
        this.orderService
                = new OrderServiceImpl(this.orderRepository, this.userService);

    }


    @Test
    void getAll() {
        orderService.getAllOrders();
        verify(orderRepository).findAll();
    }

    @Test
    public void getAllTest() {
        List<Order> list = new ArrayList<>();
        Order empOne = new Order(1L, null, 12, null, null);
        Order empTwo =new Order(2L, null, 12, null, null);
        Order empThree =new Order(3L, null, 12, null, null);

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(orderRepository.findAll()).thenReturn(list);

        //test
        List<Order> empList = orderService.getAllOrders();

        assertEquals(3, empList.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void getByIdTest() {
        when(orderRepository.getReferenceById(1L)).thenReturn(new Order(1L, null, 12, null, null));

        Order pub = orderService.getOrderById(1L);

        assertEquals(12, pub.getTotalPrice());
    }


    @Test
    public void deleteByIdTest() {
        final Long id = 1L;
        orderService.deleteOrderById(1L);
        orderService.deleteOrderById(1L);
        verify(orderRepository, times(2)).deleteById(id);

    }

    @Test
    public void create() {
        Order order = new Order(1L, null, 12, null, null);
        orderRepository.save(order);
        assertThat(order.getId()).isNotNull();
    }
}
