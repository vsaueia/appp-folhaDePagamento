package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.empregado.ChequeSalario;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PagamentoDeUmEmpregadoPorHoraTeste {
    @Test
    public void empregado_sem_cartao_de_ponto_nao_tem_pagamento() {
        int empregadoId = 2;
        BigDecimal salarioPorHora = BigDecimal.valueOf(15.25);
        AdicionarEmpregadoPorHora adicionarEmpregado = new AdicionarEmpregadoPorHora(empregadoId, "Bill",
                "Home", salarioPorHora);
        adicionarEmpregado.executar();
        LocalDate diaDoPagamento = LocalDate.of(2016, 12, 2);
        TransacaoDePagamentoDeFolhas pagamento = new TransacaoDePagamentoDeFolhas(diaDoPagamento);
        pagamento.executar();
        validarChequeSalarioEmpregadoPorHora(pagamento, empregadoId, diaDoPagamento, BigDecimal.ZERO);
    }

    private void validarChequeSalarioEmpregadoPorHora(TransacaoDePagamentoDeFolhas pagamento, int empregadoId,
                                                      LocalDate diaDoPagamento, BigDecimal salario) {
        ChequeSalario chequeSalario = pagamento.obterChequeSalario(empregadoId);
        assertNotNull(chequeSalario);
        assertEquals(diaDoPagamento, chequeSalario.obterDia());
        assertTrue(chequeSalario.obterSalarioBruto().compareTo(salario) == 0);
        assertEquals("Direto", chequeSalario.obterCampos().get("Disposicao"));
        assertEquals(BigDecimal.ZERO, chequeSalario.obterDescontos());
        assertTrue(chequeSalario.obterSalarioLiquido().compareTo(salario) == 0);
    }


}
