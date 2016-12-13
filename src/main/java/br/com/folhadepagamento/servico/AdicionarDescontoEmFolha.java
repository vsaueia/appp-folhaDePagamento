package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.empregado.AfiliacaoSindical;
import br.com.folhadepagamento.empregado.DescontoEmFolha;
import br.com.folhadepagamento.empregado.Empregado;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AdicionarDescontoEmFolha implements Transacao {

    private int afiliacaoId;
    private LocalDate dia;
    private BigDecimal valor;

    public AdicionarDescontoEmFolha(int afiliacaoId, LocalDate dia, BigDecimal valor) {
        this.afiliacaoId = afiliacaoId;
        this.dia = dia;
        this.valor = valor;
    }

    @Override
    public void executar() {
        Empregado empregado = FolhaDePagamentoDatabase.buscarEmpregadoPorAfiliacao(afiliacaoId);
        if (empregado != null) {
            AfiliacaoSindical afiliacaoSindical;
            if (empregado.obterAfiliacao() instanceof AfiliacaoSindical) {
                afiliacaoSindical = (AfiliacaoSindical) empregado.obterAfiliacao();
                if (afiliacaoSindical != null) {
                    afiliacaoSindical.adicionarDesconto(new DescontoEmFolha(dia, valor));
                }
            } else {
                throw new RuntimeException("Não é possível descontar a taxa do sindicato " +
                        "de um empregado não vinculado a sindicato");
            }
        } else {
            throw new RuntimeException("Empregado não afiliado a alguma agremiação");
        }
    }
}
