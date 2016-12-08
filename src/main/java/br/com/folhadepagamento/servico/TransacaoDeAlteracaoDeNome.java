package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.empregado.Empregado;

public class TransacaoDeAlteracaoDeNome extends TransacaoDeAlteracaoDeUsuario {
    private int empregadoId;
    private String nome;

    public TransacaoDeAlteracaoDeNome(int empregadoId, String nome) {
        super(empregadoId);
        this.nome = nome;
    }

    @Override
    public void aplicarAlteracao(Empregado empregado) {
        empregado.alterarNome(this.nome);
    }
}
