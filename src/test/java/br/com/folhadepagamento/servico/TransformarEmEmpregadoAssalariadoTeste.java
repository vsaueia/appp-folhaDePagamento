package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.empregado.Empregado;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoMensal;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoAssalariado;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.instanceOf;

public class TransformarEmEmpregadoAssalariadoTeste {
    @Test
    public void deve_transformar_um_empregado_em_assalariado() {
        int empregadoId = 4;
        AdicionarEmpregadoComissionado adicionarEmpregado = new AdicionarEmpregadoComissionado(
                empregadoId, "Vinicius", "Av 1", BigDecimal.valueOf(1999), BigDecimal.valueOf(1.2));
        adicionarEmpregado.executar();
        BigDecimal salarioFixo = BigDecimal.valueOf(2500.21);
        TransformarEmpregadoEmAssalariado alterarEmpregado =
                new TransformarEmpregadoEmAssalariado(empregadoId, salarioFixo);
        alterarEmpregado.executar();
        Empregado empregado = FolhaDePagamentoDatabase.buscarEmpregado(empregadoId);
        Assert.assertThat(empregado.obterClassificacaoDePagamento(), instanceOf(ClassificacaoAssalariado.class));
        ClassificacaoAssalariado classificacaoDePagamento = (ClassificacaoAssalariado) empregado.obterClassificacaoDePagamento();
        Assert.assertEquals(salarioFixo, classificacaoDePagamento.obterSalario());
        Assert.assertThat(empregado.obterAgendamentoDePagamento(), instanceOf(AgendamentoMensal.class));
    }
}
