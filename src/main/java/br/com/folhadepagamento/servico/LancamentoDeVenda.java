package br.com.folhadepagamento.servico;

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

    }
}
