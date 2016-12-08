package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.pagamento.agendamento.AgendamentoDePagamento;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoMensal;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoAssalariado;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoDePagamento;

import java.math.BigDecimal;

public class AdicionarEmpregadoAssalariado extends TransacaoDeAdicionarEmpregadoAssalariado {
    private final BigDecimal salario;

    public AdicionarEmpregadoAssalariado(int empregadoId, String nome, String endereco, BigDecimal salario) {
        super(empregadoId, nome, endereco);
        this.salario = salario;
    }

    protected ClassificacaoDePagamento construirClassificacao() {
        return new ClassificacaoAssalariado(this.salario);
    }

    protected AgendamentoDePagamento construirAgendamento() {
        return new AgendamentoMensal();
    }
}
