package br.com.vilatoro.ordermanager.service;

import br.com.vilatoro.ordermanager.domain.OrderEntity;
import br.com.vilatoro.ordermanager.domain.ProductEntity;
import br.com.vilatoro.ordermanager.enums.OrderStatus;
import br.com.vilatoro.ordermanager.exceptions.DuplicateOrderException;
import br.com.vilatoro.ordermanager.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private OrderEntity order;

    @BeforeEach
    public void setUp() {
        var product = new ProductEntity();
        product.setPreco(10.0);

        var product2 = new ProductEntity();
        product2.setPreco(5.0);

        order = new OrderEntity();
        order.setId(1L);
        order.setProdutos(Arrays.asList(product, product2));
        order.setStatus(OrderStatus.PENDENTE);
    }

    @Test
    public void processOrderShouldThrowDuplicateOrderException() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        order.setStatus(OrderStatus.PROCESSADO);

        assertThrows(DuplicateOrderException.class, () -> orderService.processOrder(order));
    }

    @Test
    public void processOrderShouldSaveAndReturnProcessedOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());
        when(orderRepository.save(order)).thenReturn(order);

        OrderEntity processedOrder = orderService.processOrder(order);

        assertThat(processedOrder, notNullValue());
        assertThat(processedOrder.getStatus(), is(OrderStatus.PROCESSADO));
        assertEquals(15.0, processedOrder.getTotal());
        verify(orderRepository).save(order);
        verifyNoMoreInteractions(orderRepository);
    }

    @Test
    public void getAllOrders() {
        when(orderRepository.findAll()).thenReturn(Collections.singletonList(order));
        List<OrderEntity> orders = orderService.getAllOrders();
        assertThat(orders, hasSize(1));
    }
}