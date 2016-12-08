package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.empregado.Empregado;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoQuinzenal;
import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoDePagamento;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoComissionado;
import br.com.folhadepagamento.pagamento.metodo.MetodoDePagamento;
import br.com.folhadepagamento.pagamento.metodo.PagamentoDiretoAoEmpregado;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class AdicionarEmpregadoComissionadoTeste {

    @Test
    public void deve_adicionar_empregado_comissionado() {
        int empregadoId = 2;

        AdicionarEmpregadoComissionado transacao =
                new AdicionarEmpregadoComissionado(empregadoId, "Bob", "Home", BigDecimal.valueOf(2500), BigDecimal.ONE);
        transacao.executar();

        Empregado empregado = FolhaDePagamentoDatabase.buscarEmpregado(empregadoId);
        ClassificacaoComissionado classificacaoDePagamento = (ClassificacaoComissionado) empregado.obterClassificacaoDePagamento();
        AgendamentoDePagamento agendamento = empregado.obterAgendamentoDePagamento();
        MetodoDePagamento metodoDePagamento = empregado.obterMetodoDePagamento();

        Assert.assertEquals("Bob", empregado.obterNome());
        assertThat(classificacaoDePagamento.obterSalarioFixo(), is(BigDecimal.valueOf(2500)));
        assertThat(classificacaoDePagamento.obterTaxaDeComisaso(), is(BigDecimal.ONE));
        assertTrue(agendamento instanceof AgendamentoQuinzenal);
        assertTrue(metodoDePagamento instanceof PagamentoDiretoAoEmpregado);
    }
}
