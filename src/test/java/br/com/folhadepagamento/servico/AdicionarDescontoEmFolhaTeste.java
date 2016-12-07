package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.empregado.AfiliacaoEmSindicato;
import br.com.folhadepagamento.empregado.DescontoEmFolha;
import br.com.folhadepagamento.empregado.Empregado;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AdicionarDescontoEmFolhaTeste {
    @Test
    public void deve_descontar_taxa_sindical_de_empregado_sindicalizado() {
        int empregadoId = 2;
        AdicionarEmpregadoPorHora transacao = new AdicionarEmpregadoPorHora(
                empregadoId, "Bill", "Home", BigDecimal.valueOf(15.52));
        transacao.executar();

        Empregado empregado = FolhaDePagamentoDatabase.buscarEmpregado(empregadoId);
        AfiliacaoEmSindicato afiliacaoSindicato = new AfiliacaoEmSindicato();
        empregado.criarAfiliacao(afiliacaoSindicato);
        int identificacaoDaAfiliacao = 86;
        FolhaDePagamentoDatabase.adicionarAfiliacao(identificacaoDaAfiliacao, empregado);
        LocalDate dia = LocalDate.now();
        BigDecimal valorDoDesconto = BigDecimal.valueOf(2.33);
        AdicionarDescontoEmFolha transacaoDeDesconto = new AdicionarDescontoEmFolha(
                identificacaoDaAfiliacao, dia, valorDoDesconto);
        transacaoDeDesconto.executar();
        DescontoEmFolha descontoEmFolha = afiliacaoSindicato.obterDesconto(dia);
        Assert.assertNotNull(descontoEmFolha);
        Assert.assertEquals(valorDoDesconto, descontoEmFolha.obterValor());
    }
}
