package br.com.vilatoro.ordermanager.domain;

import br.com.vilatoro.ordermanager.enums.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orderservice")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "orderservice_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @Getter
    @Setter
    private List<ProductEntity> products = new ArrayList<>();

    @Getter
    @Setter
    private Double total;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private OrderStatus status;

    @Version
    @Getter
    private Integer versao;
}