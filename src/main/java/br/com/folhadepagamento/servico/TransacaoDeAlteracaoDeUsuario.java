package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.empregado.Empregado;

public abstract class TransacaoDeAlteracaoDeUsuario implements Transacao {
    private int empregadoId;

    public TransacaoDeAlteracaoDeUsuario(int empregadoId) {
        this.empregadoId = empregadoId;
    }

    public abstract void aplicarAlteracao(Empregado empregado);

    @Override
    public void executar() {
        Empregado empregado = FolhaDePagamentoDatabase.buscarEmpregado(empregadoId);
        if (empregado != null) {
            aplicarAlteracao(empregado);
        } else {
            throw new RuntimeException("Usuário não cadastrado");
        }
    }
}
