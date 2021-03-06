package br.com.folhadepagamento.pagamento.classificacao;

import br.com.folhadepagamento.empregado.ChequeSalario;
import br.com.folhadepagamento.empregado.RelatorioDeVenda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassificacaoComissionado implements ClassificacaoDePagamento {
    private BigDecimal salarioFixo;
    private BigDecimal taxaDeComissao;
    private Map<LocalDate, RelatorioDeVenda> relatoriosDeVenda = new HashMap<>();

    public ClassificacaoComissionado(BigDecimal salarioFixo, BigDecimal taxaDeComissao) {
        this.salarioFixo = salarioFixo;
        this.taxaDeComissao = taxaDeComissao;
    }

    public BigDecimal obterSalarioFixo() {
        return this.salarioFixo;
    }

    public BigDecimal obterTaxaDeComisaso() {
        return this.taxaDeComissao;
    }

    public RelatorioDeVenda obterRelatorioDeVenda(LocalDate dia) {
        return relatoriosDeVenda.get(dia);
    }

    public void adicionarRelatorioDeVendas(RelatorioDeVenda relatorioDeVenda) {
        relatoriosDeVenda.put(relatorioDeVenda.obterDia(), relatorioDeVenda);
    }

    @Override
    public BigDecimal calcularPagamento(ChequeSalario chequeSalario, LocalDate inicioDoPeriodo, LocalDate fimDoPeriodo) {
        List<LocalDate> diasNoPeriodo = obterDatasParaCalculoDoPagamento(inicioDoPeriodo, fimDoPeriodo);
        BigDecimal vendasRealizadasNoPeriodo = BigDecimal.ZERO;
        for (LocalDate dia : diasNoPeriodo) {
            RelatorioDeVenda relatorioDeVenda = relatoriosDeVenda.get(dia);
            if (relatorioDeVenda != null) {
                vendasRealizadasNoPeriodo = vendasRealizadasNoPeriodo.add(relatorioDeVenda.obterValorDaVenda());
            }
        }
        BigDecimal comissaoPelasVendas = vendasRealizadasNoPeriodo.multiply(taxaDeComissao.divide(BigDecimal.valueOf(100)));
        return salarioFixo.add(comissaoPelasVendas);
    }



}
