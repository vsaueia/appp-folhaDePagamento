package br.com.folhadepagamento.pagamento.classificacao;

import br.com.folhadepagamento.empregado.ChequeSalario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClassificacaoAssalariado implements ClassificacaoDePagamento{

    private BigDecimal salario;

    public ClassificacaoAssalariado(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal obterSalario() {
        return salario;
    }

    @Override
    public BigDecimal calcularPagamento(ChequeSalario chequeSalario, LocalDate inicioDoPeriodo, LocalDate fimDoPeriodo) {
        return salario;
    }
}
