package br.com.folhadepagamento.transacao;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.empregado.Empregado;
import br.com.folhadepagamento.pagamento.PagamentoDiretoAoEmpregado;
import br.com.folhadepagamento.pagamento.interfaces.AgendamentoDePagamento;
import br.com.folhadepagamento.pagamento.interfaces.ClassificacaoDePagamento;
import br.com.folhadepagamento.pagamento.interfaces.MetodoDePagamento;

public abstract class TransacaoDeAdicionarEmpregadoComissionado implements Transacao {
    private int empregadoId;
    private String nome;
    private String endereco;

    public TransacaoDeAdicionarEmpregadoComissionado(int empresadoId, String nome, String endereco) {
        this.empregadoId = empresadoId;
        this.nome = nome;
        this.endereco = endereco;
    }

    protected abstract ClassificacaoDePagamento construirClassificacao();

    protected abstract AgendamentoDePagamento construirAgendamento();

    public void executar() {
        ClassificacaoDePagamento classificacaoDePagamento = construirClassificacao();
        AgendamentoDePagamento agendamentoDePagamento = construirAgendamento();
        MetodoDePagamento metodoDePagamento = new PagamentoDiretoAoEmpregado();

        Empregado empregado = new Empregado(empregadoId, nome, endereco);
        empregado.informarClassificacaoDePagamento(classificacaoDePagamento);
        empregado.informarAgendamentoDePagamento(agendamentoDePagamento);
        empregado.informarMetodoDePagamento(metodoDePagamento);

        FolhaDePagamentoDatabase.adicionarEmpregado(empregadoId, empregado);
    }
}
