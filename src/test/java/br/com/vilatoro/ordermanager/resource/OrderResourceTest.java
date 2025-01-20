package br.com.vilatoro.ordermanager.resource;

import br.com.vilatoro.ordermanager.domain.OrderEntity;
import br.com.vilatoro.ordermanager.domain.ProductEntity;
import br.com.vilatoro.ordermanager.enums.OrderStatus;
import br.com.vilatoro.ordermanager.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class OrderResourceTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderResource orderResource;

    private MockMvc mockMvc;
    private OrderEntity order;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderResource).build();

        var product = new ProductEntity();
        product.setId(1L);
        product.setPreco(10.0);

        order = new OrderEntity();
        order.setId(1L);
        order.setStatus(OrderStatus.PENDENTE);
        order.setProducts(Collections.singletonList(product));
    }

    @Test
    void createOrder() throws Exception {
        when(orderService.processOrder(any(OrderEntity.class))).thenReturn(order);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(order)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void getAllOrders() throws Exception {
        when(orderService.getAllOrders()).thenReturn(Collections.singletonList(order));

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }
}