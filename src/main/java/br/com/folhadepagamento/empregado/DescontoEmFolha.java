package br.com.folhadepagamento.empregado;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DescontoEmFolha {
    private LocalDate dia;
    private BigDecimal valor;

    public DescontoEmFolha(LocalDate dia, BigDecimal valor) {
        this.dia = dia;
        this.valor = valor;
    }

    public BigDecimal obterValor() {
        return this.valor;
    }

    public LocalDate obterDia() {
        return this.dia;
    }
}
