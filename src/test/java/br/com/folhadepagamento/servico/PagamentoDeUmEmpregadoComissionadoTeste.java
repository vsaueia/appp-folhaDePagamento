package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.empregado.ChequeSalario;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class PagamentoDeUmEmpregadoComissionadoTeste {
    @Test
    public void nao_deve_realizar_o_pagamento_de_um_empregado_comissionado_em_data_errada() {
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
        assertNull(chequeSalario);
    }

    @Test
    public void deve_realizar_o_pagamento_de_um_empregado_comissionado_por_quinzena() {
        int empregadoId = 12;
        BigDecimal salario = BigDecimal.valueOf(300);
        BigDecimal taxaDeComissao = BigDecimal.valueOf(10);
        AdicionarEmpregadoComissionado adicionarEmpregado = new AdicionarEmpregadoComissionado(empregadoId, "Vinicius",
                "Home", salario, taxaDeComissao);
        adicionarEmpregado.executar();
        LocalDate diaDoPagamento = LocalDate.of(2016, 12, 9);
        TransacaoDePagamentoDeFolhas pagamento = new TransacaoDePagamentoDeFolhas(diaDoPagamento);
        pagamento.executar();
        ChequeSalario chequeSalario = pagamento.obterChequeSalario(empregadoId);
        assertNotNull(chequeSalario);
        assertEquals(diaDoPagamento, chequeSalario.obterDia());
        assertTrue(chequeSalario.obterSalarioBruto().compareTo(salario) == 0);
        assertEquals("Direto", chequeSalario.obterCampos().get("Disposicao"));
        assertEquals(BigDecimal.ZERO, chequeSalario.obterDescontos());
        assertTrue(chequeSalario.obterSalarioLiquido().compareTo(salario) == 0);
    }

    @Test
    public void deve_pagar_empregado_comissionado_com_comissao_por_vendas() {
        int empregadoId = 1;
        BigDecimal salario = BigDecimal.valueOf(300);
        BigDecimal taxaDeComissao = BigDecimal.valueOf(10);
        AdicionarEmpregadoComissionado adicionarEmpregado = new AdicionarEmpregadoComissionado(empregadoId, "Vinicius",
                "Home", salario, taxaDeComissao);
        adicionarEmpregado.executar();

        LocalDate diaDoPagamento = LocalDate.of(2016, 12, 9);
        BigDecimal valorDaVenda = BigDecimal.valueOf(1000);
        LancamentoDeVenda lancamentoDeVenda = new LancamentoDeVenda(diaDoPagamento.minusDays(5),
                valorDaVenda, empregadoId);
        lancamentoDeVenda.executar();
        TransacaoDePagamentoDeFolhas pagamento = new TransacaoDePagamentoDeFolhas(diaDoPagamento);
        pagamento.executar();
        ChequeSalario chequeSalario = pagamento.obterChequeSalario(empregadoId);
        assertNotNull(chequeSalario);
        assertEquals(diaDoPagamento, chequeSalario.obterDia());
        BigDecimal salarioEsperado = salario.add(valorDaVenda.divide(taxaDeComissao, 2, BigDecimal.ROUND_HALF_UP));
        assertTrue(chequeSalario.obterSalarioBruto().compareTo(salarioEsperado) == 0);
        assertEquals("Direto", chequeSalario.obterCampos().get("Disposicao"));
        assertEquals(BigDecimal.ZERO, chequeSalario.obterDescontos());
        assertTrue(chequeSalario.obterSalarioLiquido().compareTo(salarioEsperado) == 0);
    }
}
