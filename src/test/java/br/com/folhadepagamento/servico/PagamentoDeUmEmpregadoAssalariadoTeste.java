package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.empregado.ChequeSalario;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class PagamentoDeUmEmpregadoAssalariadoTeste {
    @Test
    public void deve_realizar_o_pagamento_de_um_empregado_assalariado_no_ultimo_dia_do_mes() {
        int empregadoId = 1;
        BigDecimal salario = BigDecimal.valueOf(10000);
        AdicionarEmpregadoAssalariado adicionarEmpregadoAssalariado = new AdicionarEmpregadoAssalariado(empregadoId, "Vinicius",
                "Home", salario);
        adicionarEmpregadoAssalariado.executar();
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
