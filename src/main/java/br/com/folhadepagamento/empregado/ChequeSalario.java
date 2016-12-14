package br.com.folhadepagamento.empregado;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ChequeSalario {
    private LocalDate dia;
    private BigDecimal salarioBruto;
    private BigDecimal descontos;
    private BigDecimal salarioLiquido;
    private Map<String, String> campos = new HashMap<>();

    public ChequeSalario(LocalDate diaDoPagamento) {
        this.dia = diaDoPagamento;
    }

    public LocalDate obterDia() {
        return dia;
    }

    public BigDecimal obterSalarioBruto() {
        return salarioBruto;
    }

    public String obterDisposicao() {
        return "";
    }

    public BigDecimal obterDescontos() {
        return descontos;
    }

    public BigDecimal obterSalarioLiquido() {
        return salarioLiquido;
    }

    public void informarSalarioBruto(BigDecimal salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public void informarDescontos(BigDecimal descontos) {
        this.descontos = descontos;
    }

    public void informarSalarioLiquido(BigDecimal salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }

    public void adicionarCampo(String campo, String valor) {
        this.campos.put(campo, valor);
    }

    public Map<String, String> obterCampos() {
        return campos;
    }
}
