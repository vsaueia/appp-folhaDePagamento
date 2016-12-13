package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.empregado.Afiliacao;
import br.com.folhadepagamento.empregado.AfiliacaoSindical;
import br.com.folhadepagamento.empregado.Empregado;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.instanceOf;

public class ModificarParaMembroSindicalizadoTeste {
    @Test
    public void deve_modificar_um_empregado_para_ser_um_membro_do_sindicato() {
        int empregadoId = 9;
        AdicionarEmpregadoPorHora adicionarEmpregadoPorHora = new AdicionarEmpregadoPorHora(empregadoId, "Bill",
                "Home", BigDecimal.valueOf(15.25));
        adicionarEmpregadoPorHora.executar();
        int membroSindicatoId = 7754;
        BigDecimal taxaSindical = BigDecimal.valueOf(99.25);
        ModificarParaMembroSindicalizado modificarParaMembroSindicalizado = new ModificarParaMembroSindicalizado(
                empregadoId, membroSindicatoId, taxaSindical);
        modificarParaMembroSindicalizado.executar();
        Empregado empregado = FolhaDePagamentoDatabase.buscarEmpregado(empregadoId);
        Assert.assertNotNull(empregado);
        Afiliacao afiliacao = empregado.obterAfiliacao();
        Assert.assertThat(afiliacao, instanceOf(AfiliacaoSindical.class));
        AfiliacaoSindical afiliacaoSindical = (AfiliacaoSindical) afiliacao;
        Assert.assertEquals(taxaSindical, afiliacaoSindical.obterTaxa());
        Empregado membroSindical = FolhaDePagamentoDatabase.buscarEmpregadoPorAfiliacao(membroSindicatoId);
        Assert.assertEquals(empregado, membroSindical);

    }
}
