package br.com.pedido.core.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pedido {
    private Long id;
    private String cliente;
    private String produto;
    private int quantidade;

}