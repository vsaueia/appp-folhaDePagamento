package br.com.folhadepagamento.empregado;

import java.math.BigDecimal;

public class SemAfiliacao implements Afiliacao {
    @Override
    public BigDecimal calcularDescontos(ChequeSalario chequeSalario) {
        return BigDecimal.ZERO;
    }
}
