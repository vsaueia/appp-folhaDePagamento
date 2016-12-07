package br.com.folhadepagamento.pagamento.classificacao;

import br.com.folhadepagamento.empregado.RelatorioDeVenda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ClassificacaoComissionado implements ClassificacaoDePagamento {
    private BigDecimal salarioFixo;
    private Map<LocalDate, RelatorioDeVenda> relatoriosDeVenda = new HashMap<>();
    public ClassificacaoComissionado(BigDecimal salarioFixo) {
        this.salarioFixo = salarioFixo;
    }

    public BigDecimal obterSalarioFixo() {
        return this.salarioFixo;
    }

    public RelatorioDeVenda obterRelatorioDeVenda(LocalDate dia) {
        return relatoriosDeVenda.get(dia);
    }

    public void adicionarRelatorioDeVendas(RelatorioDeVenda relatorioDeVenda) {
        relatoriosDeVenda.put(relatorioDeVenda.obterDia(), relatorioDeVenda);
    }
}
