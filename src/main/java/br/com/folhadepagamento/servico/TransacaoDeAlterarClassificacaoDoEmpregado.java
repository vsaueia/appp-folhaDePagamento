package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.empregado.Empregado;
import br.com.folhadepagamento.pagamento.agendamento.AgendamentoDePagamento;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoDePagamento;

public abstract class TransacaoDeAlterarClassificacaoDoEmpregado extends TransacaoDeAlteracaoDeUsuario {
    public TransacaoDeAlterarClassificacaoDoEmpregado(int empregadoId) {
        super(empregadoId);
    }

    @Override
    public void aplicarAlteracao(Empregado empregado) {
        empregado.informarClassificacaoDePagamento(novaClassificao());
        empregado.informarAgendamentoDePagamento(novoAgendamento());
    }

    protected abstract ClassificacaoDePagamento novaClassificao();

    protected abstract AgendamentoDePagamento novoAgendamento();
}
