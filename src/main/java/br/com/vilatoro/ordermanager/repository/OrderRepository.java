package br.com.vilatoro.ordermanager.repository;

import br.com.vilatoro.ordermanager.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}