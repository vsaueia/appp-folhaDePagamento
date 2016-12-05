package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.pagamento.agendamento.AgendamentoDePagamento;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoSemanal;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoDePagamento;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoPorHora;

import java.math.BigDecimal;

public class AdicionarEmpregadoPorHora extends TransacaoDeAdicionarEmpregadoPorHora {
    private BigDecimal valorPorHora;

    public AdicionarEmpregadoPorHora(int empresadoId, String nome, String endereco, BigDecimal valorPorHora) {
        super(empresadoId, nome, endereco);
        this.valorPorHora = valorPorHora;
    }

    @Override
    protected ClassificacaoDePagamento construirClassificacao() {
        return new ClassificacaoPorHora(this.valorPorHora);
    }

    @Override
    protected AgendamentoDePagamento construirAgendamento() {
        return new AgendamentoSemanal();
    }
}
