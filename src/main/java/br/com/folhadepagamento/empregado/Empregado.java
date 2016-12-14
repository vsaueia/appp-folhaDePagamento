package br.com.folhadepagamento.empregado;

import br.com.folhadepagamento.pagamento.agendamento.AgendamentoDePagamento;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoDePagamento;
import br.com.folhadepagamento.pagamento.metodo.MetodoDePagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Empregado {
    private int empregadoId;
    private String nome;
    private String endereco;
    private ClassificacaoDePagamento classificacaoDePagamento;
    private AgendamentoDePagamento agendamentoDePagamento;
    private MetodoDePagamento metodoDePagamento;
    private Afiliacao afiliacao;

    public Empregado(int empresadoId, String nome, String endereco) {
        this.empregadoId = empresadoId;
        this.nome = nome;
        this.endereco = endereco;
    }

    public String obterNome() {
        return nome;
    }

    public ClassificacaoDePagamento obterClassificacaoDePagamento() {
        return classificacaoDePagamento;
    }

    public void informarClassificacaoDePagamento(ClassificacaoDePagamento classificacaoDePagamento) {
        this.classificacaoDePagamento = classificacaoDePagamento;
    }

    public void informarAgendamentoDePagamento(AgendamentoDePagamento agendamentoDePagamento) {
        this.agendamentoDePagamento = agendamentoDePagamento;
    }

    public void informarMetodoDePagamento(MetodoDePagamento metodoDePagamento) {
        this.metodoDePagamento = metodoDePagamento;
    }

    public AgendamentoDePagamento obterAgendamentoDePagamento() {
        return agendamentoDePagamento;
    }

    public MetodoDePagamento obterMetodoDePagamento() {
        return metodoDePagamento;
    }

    public void criarAfiliacao(Afiliacao afiliacao) {
        this.afiliacao = afiliacao;
    }

    public Afiliacao obterAfiliacao() {
        return this.afiliacao;
    }

    public void alterarNome(String nome) {
        this.nome = nome;
    }

    public boolean ehDiaDePagamento(LocalDate diaDoPagamento) {
        return agendamentoDePagamento.ehDiaDoPagamento(diaDoPagamento);
    }

    public Integer obterId() {
        return empregadoId;
    }

    public void receberPagamento(ChequeSalario chequeSalario) {
        BigDecimal salarioBruto = classificacaoDePagamento.calcularPagamento(chequeSalario);
        BigDecimal descontos = afiliacao.calcularDescontos(chequeSalario);
        BigDecimal salarioLiquido = salarioBruto.subtract(descontos);
        chequeSalario.informarSalarioBruto(salarioBruto);
        chequeSalario.informarDescontos(descontos);
        chequeSalario.informarSalarioLiquido(salarioLiquido);
        metodoDePagamento.pagar(chequeSalario);
    }
}

