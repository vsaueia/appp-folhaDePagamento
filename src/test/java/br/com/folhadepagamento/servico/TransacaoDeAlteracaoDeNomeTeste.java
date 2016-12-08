package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.empregado.Empregado;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class TransacaoDeAlteracaoDeNomeTeste {
    @Test
    public void deve_alterar_o_nume_de_um_empregado() {
        int empregadoId = 2;
        AdicionarEmpregadoAssalariado adicionarEmpregadoAssalariado =
                new AdicionarEmpregadoAssalariado(empregadoId, "Vini", "Casa", BigDecimal.TEN);
        adicionarEmpregadoAssalariado.executar();
        TransacaoDeAlteracaoDeNome alteracaoDeNome = new TransacaoDeAlteracaoDeNome(empregadoId, "Vinicius");
        alteracaoDeNome.executar();
        Empregado empregado = FolhaDePagamentoDatabase.buscarEmpregado(empregadoId);
        Assert.assertEquals("Vinicius", empregado.obterNome());
    }
}
