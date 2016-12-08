package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.empregado.Empregado;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoSemanal;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoPorHora;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.instanceOf;

public class TransformarEmEmpregadoPorHoraTeste {
    @Test
    public void deve_tornar_um_empregado_como_por_hora() {
        int empregadoId = 3;
        AdicionarEmpregadoComissionado adicionarEmpregado = new AdicionarEmpregadoComissionado(
                empregadoId, "Vinicius", "Av 1", BigDecimal.valueOf(1999), BigDecimal.valueOf(1.2));
        adicionarEmpregado.executar();
        BigDecimal salarioPorHora = BigDecimal.valueOf(25.21);
        TransformarEmpregadoEmPorHora alterarEmpregado =
                new TransformarEmpregadoEmPorHora(empregadoId, salarioPorHora);
        alterarEmpregado.executar();
        Empregado empregado = FolhaDePagamentoDatabase.buscarEmpregado(empregadoId);
        Assert.assertThat(empregado.obterClassificacaoDePagamento(), instanceOf(ClassificacaoPorHora.class));
        ClassificacaoPorHora classificacaoDePagamento = (ClassificacaoPorHora) empregado.obterClassificacaoDePagamento();
        Assert.assertEquals(salarioPorHora, classificacaoDePagamento.obterSalarioPorHora());
        Assert.assertThat(empregado.obterAgendamentoDePagamento(), instanceOf(AgendamentoSemanal.class));
    }
}
