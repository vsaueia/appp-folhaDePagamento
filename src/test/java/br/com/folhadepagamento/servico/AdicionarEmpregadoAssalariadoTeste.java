package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.empregado.Empregado;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoMensal;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoAssalariado;
import br.com.folhadepagamento.pagamento.metodo.PagamentoDiretoAoEmpregado;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoDePagamento;
import br.com.folhadepagamento.pagamento.metodo.MetodoDePagamento;
import br.com.folhadepagamento.servico.AdicionarEmpregadoAssalariado;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class AdicionarEmpregadoAssalariadoTeste {
    @Test
    public void deve_adicionar_um_empregado_assalariado() {
        int empregadoId = 1;

        AdicionarEmpregadoAssalariado transacao =
                new AdicionarEmpregadoAssalariado(empregadoId, "Bob", "Home", BigDecimal.valueOf(1000));
        transacao.executar();

        Empregado empregado = FolhaDePagamentoDatabase.buscarEmpregado(empregadoId);
        ClassificacaoAssalariado classificacaoDePagamento = (ClassificacaoAssalariado) empregado.obterClassificacaoDePagamento();
        AgendamentoDePagamento agendamentoMensal = empregado.obterAgendamentoDePagamento();
        MetodoDePagamento metodoDePagamento = empregado.obterMetodoDePagamento();

        Assert.assertEquals("Bob", empregado.getNome());
        assertThat(classificacaoDePagamento.obterSalario(), is(BigDecimal.valueOf(1000)));
        assertTrue(agendamentoMensal instanceof AgendamentoMensal);
        assertTrue(metodoDePagamento instanceof PagamentoDiretoAoEmpregado);
    }


}
