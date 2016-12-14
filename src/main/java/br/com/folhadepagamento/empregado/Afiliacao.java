package br.com.folhadepagamento.empregado;

import java.math.BigDecimal;

public interface Afiliacao {
    BigDecimal calcularDescontos(ChequeSalario chequeSalario);
}
