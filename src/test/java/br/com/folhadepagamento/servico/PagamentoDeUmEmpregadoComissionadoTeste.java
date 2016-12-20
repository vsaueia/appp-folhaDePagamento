package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.empregado.ChequeSalario;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PagamentoDeUmEmpregadoComissionadoTeste {
    @Test
    public void deve_realizar_o_pagamento_de_um_empregado_comissionado_por_quinzena() {
        int empregadoId = 12;
        BigDecimal salario = BigDecimal.valueOf(1000);
        BigDecimal taxaDeComissao = BigDecimal.valueOf(10);
        AdicionarEmpregadoComissionado adicionarEmpregado = new AdicionarEmpregadoComissionado(empregadoId, "Vinicius",
                "Home", salario, taxaDeComissao);
        adicionarEmpregado.executar();
        LocalDate diaDoPagamento = LocalDate.of(2011, 11, 30);
        TransacaoDePagamentoDeFolhas pagamento = new TransacaoDePagamentoDeFolhas(diaDoPagamento);
        pagamento.executar();
        ChequeSalario chequeSalario = pagamento.obterChequeSalario(empregadoId);
        assertNotNull(chequeSalario);
        assertEquals(diaDoPagamento, chequeSalario.obterDia());
        assertEquals(salario, chequeSalario.obterSalarioBruto());
        assertEquals("Direto", chequeSalario.obterCampos().get("Disposicao"));
        assertEquals(BigDecimal.ZERO, chequeSalario.obterDescontos());
        assertEquals(salario, chequeSalario.obterSalarioLiquido());
    }

}
