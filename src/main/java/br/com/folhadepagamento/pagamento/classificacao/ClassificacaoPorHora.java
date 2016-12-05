package br.com.folhadepagamento.pagamento.classificacao;

import java.math.BigDecimal;

public class ClassificacaoPorHora implements ClassificacaoDePagamento {
    private BigDecimal valorPorHora;

    public ClassificacaoPorHora(BigDecimal valorPorHora) {
        this.valorPorHora = valorPorHora;
    }

    public BigDecimal obterValorPorHora() {
        return this.valorPorHora;
    }
}
