package br.com.folhadepagamento.empregado;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface Afiliacao {
    BigDecimal calcularDescontos(ChequeSalario chequeSalario, LocalDate inicioDoPeriodo, LocalDate fimDoPeriodo);
}
