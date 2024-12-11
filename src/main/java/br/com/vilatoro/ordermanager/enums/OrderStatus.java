package br.com.vilatoro.ordermanager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OrderStatus {
    CANCELADO("CANCELADO"),
    PROCESSANDO("PROCESSANDO"),
    PENDENTE( "PENDENTE"),
    PROCESSADO( "PROCESSADO"),
    ERRO( "ERRO");

    @Getter
    private final String descricao;
}
