package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.empregado.ChequeSalario;
import org.junit.Assert;
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

    @Test
    public void nao_deve_realizar_o_pagamento_de_empregado_assalariado_em_data_errada() {
        int empregadoId = 1;
        BigDecimal salario = BigDecimal.valueOf(10000);
        AdicionarEmpregadoAssalariado adicionarEmpregadoAssalariado = new AdicionarEmpregadoAssalariado(empregadoId, "Vinicius",
                "Home", salario);
        adicionarEmpregadoAssalariado.executar();
        LocalDate diaDoPagamento = LocalDate.of(2011, 11, 29);
        TransacaoDePagamentoDeFolhas pagamento = new TransacaoDePagamentoDeFolhas(diaDoPagamento);
        pagamento.executar();
        ChequeSalario chequeSalario = pagamento.obterChequeSalario(empregadoId);
        Assert.assertNull(chequeSalario);
    }

    @Test
    public void deve_descontar_taxa_sindical_de_empregado_assalariado_vinculado_ao_sindicato() {
        int empregadoId = 3;
        BigDecimal salario = BigDecimal.valueOf(1000000);
        AdicionarEmpregadoAssalariado adicionarEmpregado = new AdicionarEmpregadoAssalariado(empregadoId, "Lula",
                "Casa", salario);
        adicionarEmpregado.executar();
        int membroId = 171;
        BigDecimal taxaSindical = BigDecimal.TEN;
        ModificarParaMembroSindicalizado transacaoDeSindicalizarEmpregado =
                new ModificarParaMembroSindicalizado(empregadoId, membroId, taxaSindical);
        transacaoDeSindicalizarEmpregado.executar();
        LocalDate diaDoPagamento = LocalDate.of(2016, 11, 30);
        TransacaoDePagamentoDeFolhas pagamento = new TransacaoDePagamentoDeFolhas(diaDoPagamento);
        pagamento.executar();
        BigDecimal quantidadeDeSextas = BigDecimal.valueOf(4);
        ChequeSalario chequeSalario = pagamento.obterChequeSalario(empregadoId);
        assertTrue(chequeSalario.obterSalarioBruto().compareTo(salario) == 0);
        assertTrue(chequeSalario.obterDescontos().compareTo(taxaSindical.multiply(quantidadeDeSextas)) == 0);
        assertTrue(chequeSalario.obterSalarioLiquido()
                .compareTo(salario.subtract(taxaSindical.multiply(quantidadeDeSextas))) == 0);
    }
}
