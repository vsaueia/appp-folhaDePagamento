package br.com.folhadepagamento.empregado;

import br.com.folhadepagamento.pagamento.AgendamentoMensal;
import br.com.folhadepagamento.pagamento.ClassificacaoAssalariado;
import br.com.folhadepagamento.pagamento.interfaces.AgendamentoDePagamento;
import br.com.folhadepagamento.pagamento.interfaces.ClassificacaoDePagamento;
import br.com.folhadepagamento.transacao.TransacaoDeAdicionarEmpregadoAssalariado;

import java.math.BigDecimal;

public class AdicionarEmpregadoAssalariado extends TransacaoDeAdicionarEmpregadoAssalariado {
    private final BigDecimal salario;

    public AdicionarEmpregadoAssalariado(int empregadoId, String nome, String home, BigDecimal salario) {
        super(empregadoId, nome, home);
        this.salario = salario;
    }

    protected ClassificacaoDePagamento construirClassificacao() {
        return new ClassificacaoAssalariado(this.salario);
    }

    protected AgendamentoDePagamento construirAgendamento() {
        return new AgendamentoMensal();
    }
}
