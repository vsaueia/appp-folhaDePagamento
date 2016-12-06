package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;

public class TransacaoDeDeletarEmpregado implements Transacao {

    private int empregadoId;

    public TransacaoDeDeletarEmpregado(int empregadoId) {
        this.empregadoId = empregadoId;
    }


    @Override
    public void executar() {
        FolhaDePagamentoDatabase.removerEmpregado(empregadoId);
    }
}
