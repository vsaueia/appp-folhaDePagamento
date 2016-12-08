package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.pagamento.agendamento.AgendamentoDePagamento;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoQuinzenal;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoComissionado;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoDePagamento;

import java.math.BigDecimal;

public class TransformarEmpregadoEmComissionado extends TransacaoDeAlterarClassificacaoDoEmpregado {

    private BigDecimal salarioFixo;
    private BigDecimal taxaDeComissao;

    public TransformarEmpregadoEmComissionado(int empregadoId, BigDecimal salarioFixo, BigDecimal taxaDeComissao) {
        super(empregadoId);
        this.salarioFixo = salarioFixo;
        this.taxaDeComissao = taxaDeComissao;
    }

    @Override
    protected ClassificacaoDePagamento novaClassificao() {
        return new ClassificacaoComissionado(this.salarioFixo, this.taxaDeComissao);
    }

    @Override
    protected AgendamentoDePagamento novoAgendamento() {
        return new AgendamentoQuinzenal();
    }
}
