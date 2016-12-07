package br.com.folhadepagamento.empregado;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RelatorioDeVenda {

    private BigDecimal valorDaVenda;
    private LocalDate dia;

    public RelatorioDeVenda(BigDecimal valorDaVenda, LocalDate dia) {
        this.valorDaVenda = valorDaVenda;
        this.dia = dia;
    }

    public BigDecimal obterValorDaVenda() {
        return this.valorDaVenda;
    }

    public LocalDate obterDia() {
        return this.dia;
    }
}
