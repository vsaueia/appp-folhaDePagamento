package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.empregado.ChequeSalario;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

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

    @Test
    public void pagar_empregado_por_hora_com_um_cartao_ponto() {
        int empregadoId = 3;
        BigDecimal salarioPorHora = BigDecimal.valueOf(15.25);
        AdicionarEmpregadoPorHora adicionarEmpregado = new AdicionarEmpregadoPorHora(empregadoId, "Bill",
                "Home", salarioPorHora);
        adicionarEmpregado.executar();
        LocalDate diaDoPagamento = LocalDate.of(2016, 12, 2);
        LancamentoDeCartaoPonto cartaoPonto = new LancamentoDeCartaoPonto(diaDoPagamento.minusDays(2),
                BigDecimal.valueOf(7), empregadoId);
        cartaoPonto.executar();
        TransacaoDePagamentoDeFolhas pagamento = new TransacaoDePagamentoDeFolhas(diaDoPagamento);
        pagamento.executar();
        validarChequeSalarioEmpregadoPorHora(pagamento, empregadoId, diaDoPagamento, BigDecimal.valueOf(106.75));
    }

    @Test
    public void pagar_empregado_por_hora_com_dois_cartoes_ponto() {
        int empregadoId = 3;
        BigDecimal salarioPorHora = BigDecimal.valueOf(5);
        AdicionarEmpregadoPorHora adicionarEmpregado = new AdicionarEmpregadoPorHora(empregadoId, "Bill",
                "Home", salarioPorHora);
        adicionarEmpregado.executar();
        LocalDate diaDoPagamento = LocalDate.of(2016, 12, 2);
        LancamentoDeCartaoPonto cartaoPonto = new LancamentoDeCartaoPonto(diaDoPagamento.minusDays(2),
                BigDecimal.valueOf(7), empregadoId);
        cartaoPonto.executar();
        cartaoPonto = new LancamentoDeCartaoPonto(diaDoPagamento.minusDays(1),
                BigDecimal.valueOf(8), empregadoId);
        cartaoPonto.executar();
        TransacaoDePagamentoDeFolhas pagamento = new TransacaoDePagamentoDeFolhas(diaDoPagamento);
        pagamento.executar();
        validarChequeSalarioEmpregadoPorHora(pagamento, empregadoId, diaDoPagamento, BigDecimal.valueOf(75));
    }

    @Test
    public void deve_pagar_empregado_por_hora_com_horas_extras() {
        int empregadoId = 3;
        BigDecimal salarioPorHora = BigDecimal.valueOf(10.25);
        AdicionarEmpregadoPorHora adicionarEmpregado = new AdicionarEmpregadoPorHora(empregadoId, "Bill",
                "Home", salarioPorHora);
        adicionarEmpregado.executar();
        LocalDate diaDoPagamento = LocalDate.of(2016, 12, 2);
        LancamentoDeCartaoPonto cartaoPonto = new LancamentoDeCartaoPonto(diaDoPagamento.minusDays(2),
                BigDecimal.valueOf(10), empregadoId);
        cartaoPonto.executar();
        TransacaoDePagamentoDeFolhas pagamento = new TransacaoDePagamentoDeFolhas(diaDoPagamento);
        pagamento.executar();
        BigDecimal salarioEsperado = salarioPorHora.multiply(BigDecimal.valueOf(8));
        BigDecimal horasExtras = salarioPorHora.multiply(BigDecimal.valueOf(1.5)).multiply(BigDecimal.valueOf(2));
        salarioEsperado = salarioEsperado.add(horasExtras);
        validarChequeSalarioEmpregadoPorHora(pagamento, empregadoId, diaDoPagamento, salarioEsperado);
    }

    @Test
    public void nao_deve_pagar_empregado_por_hora_na_data_errada() {
        int empregadoId = 2;
        BigDecimal salarioPorHora = BigDecimal.valueOf(15.25);
        AdicionarEmpregadoPorHora adicionarEmpregado = new AdicionarEmpregadoPorHora(empregadoId, "Bill",
                "Home", salarioPorHora);
        adicionarEmpregado.executar();
        LocalDate diaDoPagamento = LocalDate.of(2016, 12, 3);
        TransacaoDePagamentoDeFolhas pagamento = new TransacaoDePagamentoDeFolhas(diaDoPagamento);
        pagamento.executar();
        ChequeSalario chequeSalario = pagamento.obterChequeSalario(empregadoId);
        assertNull(chequeSalario);
    }

    @Test
    public void deve_pagar_empregado_por_hora_com_um_cartao_ponto_certo_e_outro_fora_do_periodo() {
        int empregadoId = 3;
        BigDecimal salarioPorHora = BigDecimal.valueOf(10);
        AdicionarEmpregadoPorHora adicionarEmpregado = new AdicionarEmpregadoPorHora(empregadoId, "Bill",
                "Home", salarioPorHora);
        adicionarEmpregado.executar();
        LocalDate diaDoPagamento = LocalDate.of(2016, 12, 2);
        BigDecimal horasTrabalhadas = BigDecimal.valueOf(5);
        LancamentoDeCartaoPonto cartaoPonto = new LancamentoDeCartaoPonto(diaDoPagamento.minusDays(2),
                horasTrabalhadas, empregadoId);
        cartaoPonto.executar();

        cartaoPonto = new LancamentoDeCartaoPonto(diaDoPagamento.minusDays(26),
                BigDecimal.valueOf(6), empregadoId);
        cartaoPonto.executar();

        TransacaoDePagamentoDeFolhas pagamento = new TransacaoDePagamentoDeFolhas(diaDoPagamento);
        pagamento.executar();
        BigDecimal salarioEsperado = salarioPorHora.multiply(horasTrabalhadas);
        validarChequeSalarioEmpregadoPorHora(pagamento, empregadoId, diaDoPagamento, salarioEsperado);
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
