package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.empregado.Empregado;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoDePagamento;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoSemanal;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoDePagamento;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoPorHora;

import java.math.BigDecimal;

public class TransformarEmpregadoEmPorHora extends TransacaoDeAlterarClassificacaoDoEmpregado {

    private BigDecimal salarioPorHora;

    public TransformarEmpregadoEmPorHora(int empregadoId, BigDecimal salarioPorHora) {
        super(empregadoId);
        this.salarioPorHora = salarioPorHora;
    }

    @Override
    protected ClassificacaoDePagamento novaClassificao() {
        return new ClassificacaoPorHora(this.salarioPorHora);
    }

    @Override
    protected AgendamentoDePagamento novoAgendamento() {
        return new AgendamentoSemanal();
    }
}
