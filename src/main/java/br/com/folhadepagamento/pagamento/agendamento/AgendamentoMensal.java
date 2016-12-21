package br.com.folhadepagamento.pagamento.agendamento;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public class AgendamentoMensal implements AgendamentoDePagamento {

    @Override
    public boolean ehDiaDoPagamento(LocalDate dia) {
        return ehUltimoDiaDoMes(dia);
    }

    @Override
    public LocalDate obterPeriodo(LocalDate inicioDoPeriodo) {
        return inicioDoPeriodo.with(TemporalAdjusters.firstDayOfMonth());
    }

    private boolean ehUltimoDiaDoMes(LocalDate dia) {
        LocalDate ultimoDiaDoMes = dia.with(lastDayOfMonth());
        return dia.compareTo(ultimoDiaDoMes) == 0;
    }
}
