package br.com.folhadepagamento.empregado;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SemAfiliacao implements Afiliacao {
    @Override
    public BigDecimal calcularDescontos(ChequeSalario chequeSalario, LocalDate inicioDoPeriodo, LocalDate fimDoPeriodo) {
        return BigDecimal.ZERO;
    }
}
