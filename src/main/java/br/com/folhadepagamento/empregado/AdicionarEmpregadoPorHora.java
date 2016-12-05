package br.com.folhadepagamento.empregado;

import br.com.folhadepagamento.pagamento.AgendamentoSemanal;
import br.com.folhadepagamento.pagamento.ClassificacaoAssalariado;
import br.com.folhadepagamento.pagamento.ClassificacaoPorHora;
import br.com.folhadepagamento.pagamento.interfaces.AgendamentoDePagamento;
import br.com.folhadepagamento.pagamento.interfaces.ClassificacaoDePagamento;
import br.com.folhadepagamento.transacao.TransacaoDeAdicionarEmpregadoPorHora;

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
