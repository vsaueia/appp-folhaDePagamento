package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.pagamento.agendamento.AgendamentoDePagamento;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoMensal;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoSemanal;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoAssalariado;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoDePagamento;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoPorHora;

import java.math.BigDecimal;

public class TransformarEmpregadoEmAssalariado extends TransacaoDeAlterarClassificacaoDoEmpregado {

    private BigDecimal salarioFixo;

    public TransformarEmpregadoEmAssalariado(int empregadoId, BigDecimal salarioFixo) {
        super(empregadoId);
        this.salarioFixo = salarioFixo;
    }

    @Override
    protected ClassificacaoDePagamento novaClassificao() {
        return new ClassificacaoAssalariado(this.salarioFixo);
    }

    @Override
    protected AgendamentoDePagamento novoAgendamento() {
        return new AgendamentoMensal();
    }
}
