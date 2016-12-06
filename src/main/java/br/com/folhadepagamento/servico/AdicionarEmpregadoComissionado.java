package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.pagamento.agendamento.AgendamentoDePagamento;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoQuinzenal;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoComissionado;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoDePagamento;

import java.math.BigDecimal;

public class AdicionarEmpregadoComissionado extends TransacaoDeAdicionarEmpregadoAssalariado {
    private final BigDecimal salarioFixo;

    public AdicionarEmpregadoComissionado(int empregadoId, String nome, String home, BigDecimal salarioFixo, BigDecimal comissaoPorVenda) {
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
