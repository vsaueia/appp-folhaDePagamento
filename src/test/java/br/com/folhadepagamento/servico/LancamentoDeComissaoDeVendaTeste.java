package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.empregado.Empregado;
import br.com.folhadepagamento.empregado.RelatorioDeVenda;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoComissionado;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;

public class LancamentoDeComissaoDeVendaTeste {
    @Test
    public void deve_poder_lancar_venda_para_empregado_comissionado() {
        int empregadoId = 76;
        AdicionarEmpregadoComissionado transacao =
                new AdicionarEmpregadoComissionado(empregadoId, "Vinicius", "Home", BigDecimal.valueOf(1555.25), BigDecimal.valueOf(3.2));
        transacao.executar();

        LocalDate dia = LocalDate.of(2016, 7, 31);
        BigDecimal valorDaVenda = BigDecimal.valueOf(10000);
        LancamentoDeVenda transacaoDeVenda = new LancamentoDeVenda(dia, valorDaVenda, empregadoId);
        transacaoDeVenda.executar();

        Empregado empregado = FolhaDePagamentoDatabase.buscarEmpregado(empregadoId);
        Assert.assertThat(empregado, is(not(nullValue())));
        ClassificacaoComissionado comissionado = (ClassificacaoComissionado) empregado.obterClassificacaoDePagamento();
        RelatorioDeVenda relatorioDeVenda = comissionado.obterRelatorioDeVenda(dia);
        Assert.assertThat(relatorioDeVenda.obterValorDaVenda(), is(valorDaVenda));
    }

    @Test(expected = RuntimeException.class)
    public void nao_deve_lancar_venda_para_empregado_nao_comissionado() {
        int empregadoId = 76;
        AdicionarEmpregadoAssalariado transacao =
                new AdicionarEmpregadoAssalariado(empregadoId, "Vinicius", "Home", BigDecimal.valueOf(1555.25));
        transacao.executar();

        LocalDate dia = LocalDate.of(2016, 7, 31);
        BigDecimal valorDaVenda = BigDecimal.valueOf(10000);
        LancamentoDeVenda transacaoDeVenda = new LancamentoDeVenda(dia, valorDaVenda, empregadoId);
        transacaoDeVenda.executar();
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_lancar_venda_para_empregado_nao_cadastrado() {
        LocalDate dia = LocalDate.of(2016, 7, 31);
        BigDecimal valorDaVenda = BigDecimal.valueOf(10000);
        LancamentoDeVenda transacaoDeVenda = new LancamentoDeVenda(dia, valorDaVenda, -1);
        transacaoDeVenda.executar();
    }
}
