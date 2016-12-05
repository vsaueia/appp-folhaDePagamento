package br.com.folhadepagamento.pagamento;

import br.com.folhadepagamento.pagamento.interfaces.ClassificacaoDePagamento;

import java.math.BigDecimal;

public class ClassificacaoAssalariado implements ClassificacaoDePagamento{

    private BigDecimal salario;

    public ClassificacaoAssalariado(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal obterSalario() {
        return salario;
    }
}
