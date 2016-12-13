package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.empregado.Afiliacao;
import br.com.folhadepagamento.empregado.AfiliacaoSindical;
import br.com.folhadepagamento.empregado.Empregado;

import java.math.BigDecimal;

public class ModificarParaMembroSindicalizado extends TransacaoDeModificarAfilicao {
    private int membroSindicatoId;
    private BigDecimal taxaSindical;

    public ModificarParaMembroSindicalizado(int empregadoId, int membroSindicatoId, BigDecimal taxaSindical) {
        super(empregadoId);
        this.membroSindicatoId = membroSindicatoId;
        this.taxaSindical = taxaSindical;
    }

    @Override
    protected void registrarMembroDoSindicato(Empregado empregado) {
        FolhaDePagamentoDatabase.adicionarAfiliacao(membroSindicatoId, empregado);
    }

    @Override
    protected Afiliacao criarAfilicao() {
        return new AfiliacaoSindical(membroSindicatoId, taxaSindical);
    }
}
