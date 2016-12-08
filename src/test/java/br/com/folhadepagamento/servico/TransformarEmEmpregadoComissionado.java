package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.empregado.Empregado;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoQuinzenal;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoSemanal;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoComissionado;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoPorHora;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.instanceOf;

public class TransformarEmEmpregadoComissionado {
    @Test
    public void deve_tornar_um_empregado_como_por_hora() {
        int empregadoId = 3;
        AdicionarEmpregadoAssalariado adicionarEmpregado = new AdicionarEmpregadoAssalariado(
                empregadoId, "Vinicius", "Av 1", BigDecimal.valueOf(1999));
        adicionarEmpregado.executar();
        BigDecimal salarioFixo = BigDecimal.valueOf(2511.21);
        BigDecimal taxaDeComissao = BigDecimal.valueOf(5.51);
        TransformarEmpregadoEmComissionado alterarEmpregado =
                new TransformarEmpregadoEmComissionado(empregadoId, salarioFixo, taxaDeComissao);
        alterarEmpregado.executar();
        Empregado empregado = FolhaDePagamentoDatabase.buscarEmpregado(empregadoId);
        Assert.assertThat(empregado.obterClassificacaoDePagamento(), instanceOf(ClassificacaoComissionado.class));
        ClassificacaoComissionado classificacaoDePagamento = (ClassificacaoComissionado) empregado.obterClassificacaoDePagamento();
        Assert.assertEquals(salarioFixo, classificacaoDePagamento.obterSalarioFixo());
        Assert.assertThat(empregado.obterAgendamentoDePagamento(), instanceOf(AgendamentoQuinzenal.class));
    }
}
