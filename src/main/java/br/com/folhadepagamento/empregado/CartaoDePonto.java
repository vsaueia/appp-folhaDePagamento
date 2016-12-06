package br.com.folhadepagamento.empregado;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CartaoDePonto {
    private final BigDecimal horasLancadas;
    private final LocalDate dia;

    public CartaoDePonto(LocalDate dia, BigDecimal horasLancadas) {
        this.dia = dia;
        this.horasLancadas = horasLancadas;
    }

    public BigDecimal obterQuantidadeDeHoras() {
        return horasLancadas;
    }

    public LocalDate obterDia() {
        return dia;
    }
}
