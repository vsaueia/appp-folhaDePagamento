package br.com.folhadepagamento.pagamento.classificacao;

import br.com.folhadepagamento.empregado.CartaoDePonto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ClassificacaoPorHora implements ClassificacaoDePagamento {
    private BigDecimal valorPorHora;
    private Map<LocalDate, CartaoDePonto> cartoesDePonto = new HashMap<>();

    public ClassificacaoPorHora(BigDecimal valorPorHora) {
        this.valorPorHora = valorPorHora;
    }

    public BigDecimal obterValorPorHora() {
        return this.valorPorHora;
    }

    public CartaoDePonto obterCartaoPontoDoDia(LocalDate dataBase) {
        return cartoesDePonto.get(dataBase);
    }

    public void adicionarCartaoPonto(CartaoDePonto cartaoDePonto) {
        this.cartoesDePonto.put(cartaoDePonto.obterDia(), cartaoDePonto);
    }
}
