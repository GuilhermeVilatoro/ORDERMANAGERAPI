package br.com.vilatoro.ordermanager.service;

import br.com.vilatoro.ordermanager.domain.OrderEntity;
import br.com.vilatoro.ordermanager.domain.ProductEntity;
import br.com.vilatoro.ordermanager.enums.OrderStatus;
import br.com.vilatoro.ordermanager.exceptions.DuplicateOrderException;
import br.com.vilatoro.ordermanager.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    @Cacheable(value = "processOrder", key = "#order.id")
    public OrderEntity processOrder(OrderEntity order) {
        Optional<OrderEntity> existingOrder = orderRepository.findById(order.getId());
        if (existingOrder.isPresent() && OrderStatus.PROCESSADO.equals(order.getStatus())) {
            throw new DuplicateOrderException("Pedido duplicado!");
        }

        order.setTotal(order.getProdutos().stream()
                        .filter(product -> Objects.nonNull(product.getPreco()))
                .mapToDouble(ProductEntity::getPreco)
                .sum());

        order.setStatus(OrderStatus.PROCESSADO);
        return orderRepository.save(order);
    }

    @Cacheable(cacheNames = "getAllOrders")
    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }
}
