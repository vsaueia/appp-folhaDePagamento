package br.com.folhadepagamento.pagamento.classificacao;

import java.math.BigDecimal;

public class ClassificacaoComissionado implements ClassificacaoDePagamento {
    private BigDecimal salarioFixo;

    public ClassificacaoComissionado(BigDecimal salarioFixo) {
        this.salarioFixo = salarioFixo;
    }

    public BigDecimal obterSalarioFixo() {
        return this.salarioFixo;
    }
}
