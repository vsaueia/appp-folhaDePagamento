package br.com.folhadepagamento.pagamento.classificacao;

import br.com.folhadepagamento.empregado.ChequeSalario;

import java.math.BigDecimal;

public interface ClassificacaoDePagamento {
    BigDecimal calcularPagamento(ChequeSalario chequeSalario);
}
