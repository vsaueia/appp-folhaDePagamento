package br.com.folhadepagamento.empregado;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoSemanal;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoPorHora;
import br.com.folhadepagamento.pagamento.metodo.PagamentoDiretoAoEmpregado;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoDePagamento;
import br.com.folhadepagamento.pagamento.metodo.MetodoDePagamento;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class AdicionarEmpregadoPorHoraTeste {
    @Test
    public void deve_adicionar_um_empregado_que_recebe_por_hora() {
        int empregadoId = 1;

        AdicionarEmpregadoPorHora transacao =
                new AdicionarEmpregadoPorHora(empregadoId, "Bob", "Home", BigDecimal.valueOf(25));
        transacao.executar();

        Empregado empregado = FolhaDePagamentoDatabase.buscarEmpregado(empregadoId);
        ClassificacaoPorHora classificacaoDePagamento = (ClassificacaoPorHora) empregado.obterClassificacaoDePagamento();
        AgendamentoDePagamento agendamento = empregado.obterAgendamentoDePagamento();
        MetodoDePagamento metodoDePagamento = empregado.obterMetodoDePagamento();

        Assert.assertEquals("Bob", empregado.getNome());
        assertThat(classificacaoDePagamento.obterValorPorHora(), is(BigDecimal.valueOf(25)));
        assertTrue(agendamento instanceof AgendamentoSemanal);
        assertTrue(metodoDePagamento instanceof PagamentoDiretoAoEmpregado);
    }
}
