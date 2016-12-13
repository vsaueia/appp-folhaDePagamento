package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.empregado.Afiliacao;
import br.com.folhadepagamento.empregado.Empregado;

public abstract class TransacaoDeModificarAfilicao extends TransacaoDeAlteracaoDeUsuario {


    public TransacaoDeModificarAfilicao(int empregadoId) {
        super(empregadoId);
    }

    @Override
    public void aplicarAlteracao(Empregado empregado) {
        registrarMembroDoSindicato(empregado);
        Afiliacao afiliacao = criarAfilicao();
        empregado.criarAfiliacao(afiliacao);
    }

    protected abstract void registrarMembroDoSindicato(Empregado empregado);

    protected abstract Afiliacao criarAfilicao();
}
