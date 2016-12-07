package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.empregado.Empregado;
import br.com.folhadepagamento.empregado.RelatorioDeVenda;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoComissionado;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoDePagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LancamentoDeVenda implements Transacao {

    private final LocalDate dia;
    private final BigDecimal valorDaVenda;
    private final int empregadoId;

    public LancamentoDeVenda(LocalDate dia, BigDecimal valorDaVenda, int empregadoId) {
        this.dia = dia;
        this.valorDaVenda = valorDaVenda;
        this.empregadoId = empregadoId;
    }

    @Override
    public void executar() {
        Empregado empregado = FolhaDePagamentoDatabase.buscarEmpregado(empregadoId);
        validarSeEmpregadoExiste(empregado);
        try {
            ClassificacaoComissionado classificacaoDePagamento = (ClassificacaoComissionado) empregado.obterClassificacaoDePagamento();
            classificacaoDePagamento.adicionarRelatorioDeVendas(new RelatorioDeVenda(valorDaVenda, dia));
        } catch (ClassCastException e) {
            throw new RuntimeException("Comissão só pode ser paga para empregados comissionados.");
        }
    }

    private void validarSeEmpregadoExiste(Empregado empregado) {
        if (empregado == null) {
            throw new IllegalArgumentException("Usuário não cadastrado");
        }
    }
}
