package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.empregado.Afiliacao;
import br.com.folhadepagamento.empregado.AfiliacaoSindical;
import br.com.folhadepagamento.empregado.Empregado;
import br.com.folhadepagamento.empregado.SemAfiliacao;

public class TransacaoDeDesafiliacao extends TransacaoDeModificarAfilicao {

    public TransacaoDeDesafiliacao(int empregadoId) {
        super(empregadoId);
    }

    @Override
    protected void registrarMembroDoSindicato(Empregado empregado) {
        Afiliacao afiliacao = empregado.obterAfiliacao();
        if (afiliacao instanceof AfiliacaoSindical) {
            AfiliacaoSindical afiliacaoSindical = (AfiliacaoSindical) empregado.obterAfiliacao();
            FolhaDePagamentoDatabase.removerMembro(afiliacaoSindical.obterMembroId());
        }
    }

    @Override
    protected Afiliacao criarAfilicao() {
        return new SemAfiliacao();
    }
}
