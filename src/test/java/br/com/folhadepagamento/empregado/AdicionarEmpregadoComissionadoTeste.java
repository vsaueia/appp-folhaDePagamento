package br.com.folhadepagamento.empregado;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.pagamento.*;
import br.com.folhadepagamento.pagamento.interfaces.AgendamentoDePagamento;
import br.com.folhadepagamento.pagamento.interfaces.MetodoDePagamento;
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
                new AdicionarEmpregadoComissionado(empregadoId, "Bob", "Home", BigDecimal.valueOf(2500));
        transacao.executar();

        Empregado empregado = FolhaDePagamentoDatabase.buscarEmpregado(empregadoId);
        ClassificacaoComissionado classificacaoDePagamento = (ClassificacaoComissionado) empregado.obterClassificacaoDePagamento();
        AgendamentoDePagamento agendamento = empregado.obterAgendamentoDePagamento();
        MetodoDePagamento metodoDePagamento = empregado.obterMetodoDePagamento();

        Assert.assertEquals("Bob", empregado.getNome());
        assertThat(classificacaoDePagamento.obterSalarioFixo(), is(BigDecimal.valueOf(2500)));
        assertTrue(agendamento instanceof AgendamentoQuinzenal);
        assertTrue(metodoDePagamento instanceof PagamentoDiretoAoEmpregado);
    }
}
