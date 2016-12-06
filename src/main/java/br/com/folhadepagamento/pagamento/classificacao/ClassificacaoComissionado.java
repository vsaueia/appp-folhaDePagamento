package br.com.folhadepagamento.pagamento.classificacao;

import br.com.folhadepagamento.empregado.RelatorioDeVenda;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClassificacaoComissionado implements ClassificacaoDePagamento {
    private BigDecimal salarioFixo;

    public ClassificacaoComissionado(BigDecimal salarioFixo) {
        this.salarioFixo = salarioFixo;
    }

    public BigDecimal obterSalarioFixo() {
        return this.salarioFixo;
    }

    public RelatorioDeVenda obterRelatorioDeVenda(LocalDate dia) {
        return null;
    }
}
