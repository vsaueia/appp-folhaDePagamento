package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.empregado.Empregado;
import br.com.folhadepagamento.empregado.SemAfiliacao;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class TransacaoDeDesafiliacaoTeste {
    @Test
    public void deve_desafiliar_um_membro_do_sindicato() {
        int empregadoId = 2;
        int membroDoSindicatoId = 7737;
        BigDecimal taxa = BigDecimal.valueOf(99.42);
        AdicionarEmpregadoPorHora adicionarEmpregadoPorHora = new AdicionarEmpregadoPorHora(empregadoId, "Rob",
                "Home", BigDecimal.valueOf(14.21));
        adicionarEmpregadoPorHora.executar();
        ModificarParaMembroSindicalizado modificarParaMembroSindicalizado = new ModificarParaMembroSindicalizado(
                empregadoId, membroDoSindicatoId, taxa);
        modificarParaMembroSindicalizado.executar();
        Empregado membro = FolhaDePagamentoDatabase.buscarEmpregadoPorAfiliacao(membroDoSindicatoId);
        assertNotNull(membro);
        TransacaoDeDesafiliacao transacaoDeDesafiliacao = new TransacaoDeDesafiliacao(empregadoId);
        transacaoDeDesafiliacao.executar();
        Empregado empregado = FolhaDePagamentoDatabase.buscarEmpregado(empregadoId);
        assertNotNull(empregado);
        assertThat(empregado.obterAfiliacao(), instanceOf(SemAfiliacao.class));
        membro = FolhaDePagamentoDatabase.buscarEmpregadoPorAfiliacao(membroDoSindicatoId);
        assertNull(membro);
    }
}
