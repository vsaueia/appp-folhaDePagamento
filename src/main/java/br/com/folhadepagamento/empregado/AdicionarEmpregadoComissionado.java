package br.com.folhadepagamento.empregado;

import br.com.folhadepagamento.pagamento.AgendamentoQuinzenal;
import br.com.folhadepagamento.pagamento.ClassificacaoComissionado;
import br.com.folhadepagamento.pagamento.interfaces.AgendamentoDePagamento;
import br.com.folhadepagamento.pagamento.interfaces.ClassificacaoDePagamento;
import br.com.folhadepagamento.transacao.TransacaoDeAdicionarEmpregadoAssalariado;

import java.math.BigDecimal;

public class AdicionarEmpregadoComissionado extends TransacaoDeAdicionarEmpregadoAssalariado {
    private final BigDecimal salarioFixo;

    public AdicionarEmpregadoComissionado(int empregadoId, String nome, String home, BigDecimal salarioFixo) {
        super(empregadoId, nome, home);
        this.salarioFixo = salarioFixo;
    }

    protected ClassificacaoDePagamento construirClassificacao() {
        return new ClassificacaoComissionado(this.salarioFixo);
    }

    protected AgendamentoDePagamento construirAgendamento() {
        return new AgendamentoQuinzenal();
    }
}
