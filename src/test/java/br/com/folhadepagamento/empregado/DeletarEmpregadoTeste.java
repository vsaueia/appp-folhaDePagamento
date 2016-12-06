package br.com.folhadepagamento.empregado;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.servico.AdicionarEmpregadoAssalariado;
import br.com.folhadepagamento.servico.AdicionarEmpregadoComissionado;
import br.com.folhadepagamento.servico.TransacaoDeDeletarEmpregado;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class DeletarEmpregadoTeste {
    @Test
    public void deve_deletar_um_empregado() {
        int empregadoId = 4;
        AdicionarEmpregadoComissionado transacao =
                new AdicionarEmpregadoComissionado(empregadoId, "Bill", "Home", BigDecimal.valueOf(2500), BigDecimal.valueOf(3.2));
        transacao.executar();

        Empregado empregado = FolhaDePagamentoDatabase.buscarEmpregado(empregadoId);
        Assert.assertNotNull(empregado);
        TransacaoDeDeletarEmpregado transacaoDeletar = new TransacaoDeDeletarEmpregado(empregadoId);
        transacaoDeletar.executar();

        empregado = FolhaDePagamentoDatabase.buscarEmpregado(empregadoId);
        Assert.assertNull(empregado);
    }
}
