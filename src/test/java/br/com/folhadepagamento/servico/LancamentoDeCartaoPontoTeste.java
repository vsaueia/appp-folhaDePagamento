package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.empregado.CartaoDePonto;
import br.com.folhadepagamento.empregado.Empregado;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoDePagamento;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoPorHora;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.instanceOf;

public class LancamentoDeCartaoPontoTeste {
    @Test
     public void deve_lancar_cartao_ponto_de_empregado_por_hora() {
        int empregadoId = 6;
        AdicionarEmpregadoPorHora transacao =
                new AdicionarEmpregadoPorHora(empregadoId, "Vinicius", "Home", BigDecimal.valueOf(15.25));
        transacao.executar();

        LocalDate dataBase = LocalDate.of(2016, 7, 31);
        LancamentoDeCartaoPonto lancamentoCartaoPonto =
                new LancamentoDeCartaoPonto(dataBase, BigDecimal.valueOf(8), empregadoId);
        lancamentoCartaoPonto.executar();

        Empregado empregado = FolhaDePagamentoDatabase.buscarEmpregado(empregadoId);
        ClassificacaoDePagamento classificacaoDePagamento = empregado.obterClassificacaoDePagamento();
        Assert.assertThat(classificacaoDePagamento, instanceOf(ClassificacaoPorHora.class));
        ClassificacaoPorHora porHora = (ClassificacaoPorHora) classificacaoDePagamento;
        CartaoDePonto cartaoDePonto = porHora.obterCartaoPontoDoDia(dataBase);
        Assert.assertNotNull(cartaoDePonto);
        Assert.assertEquals(BigDecimal.valueOf(8) , cartaoDePonto.obterQuantidadeDeHoras());
    }

    @Test(expected = IllegalStateException.class)
    public void nao_deve_adicionar_cartao_de_ponto_a_um_empregado_comissionado_ou_assalariado() {
        int empregadoId = 6;
        AdicionarEmpregadoAssalariado transacao =
                new AdicionarEmpregadoAssalariado(empregadoId, "Vinicius", "Home", BigDecimal.valueOf(15.25));
        transacao.executar();

        LocalDate dataBase = LocalDate.of(2016, 7, 31);
        LancamentoDeCartaoPonto lancamentoCartaoPonto =
                new LancamentoDeCartaoPonto(dataBase, BigDecimal.valueOf(8), empregadoId);
        lancamentoCartaoPonto.executar();
    }

    @Test(expected = IllegalArgumentException.class)
    public void nao_deve_adicionar_cartao_de_ponto_se_o_empregado_nao_existir() {
        LocalDate dataBase = LocalDate.of(2016, 7, 31);
        int empregadoId = 1;
        LancamentoDeCartaoPonto lancamentoCartaoPonto =
                new LancamentoDeCartaoPonto(dataBase, BigDecimal.valueOf(8), empregadoId);
        lancamentoCartaoPonto.executar();
    }
}
