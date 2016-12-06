package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.pagamento.agendamento.AgendamentoDePagamento;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoQuinzenal;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoComissionado;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoDePagamento;

import java.math.BigDecimal;

public class AdicionarEmpregadoComissionado extends TransacaoDeAdicionarEmpregadoAssalariado {
    private final BigDecimal salarioFixo;
    private final BigDecimal taxaDeComissao;

    public AdicionarEmpregadoComissionado(int empregadoId, String nome, String home, BigDecimal salarioFixo, BigDecimal taxaDeComissao) {
        super(empregadoId, nome, home);
        this.salarioFixo = salarioFixo;
        this.taxaDeComissao = taxaDeComissao;
    }

    protected ClassificacaoDePagamento construirClassificacao() {
        return new ClassificacaoComissionado(this.salarioFixo);
    }

    protected AgendamentoDePagamento construirAgendamento() {
        return new AgendamentoQuinzenal();
    }
}
