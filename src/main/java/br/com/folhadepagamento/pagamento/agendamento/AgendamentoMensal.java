package br.com.folhadepagamento.pagamento.agendamento;

import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public class AgendamentoMensal implements AgendamentoDePagamento {

    @Override
    public boolean ehDiaDoPagamento(LocalDate dia) {
        return ehUltimoDiaDoMes(dia);
    }

    private boolean ehUltimoDiaDoMes(LocalDate dia) {
        LocalDate ultimoDiaDoMes = dia.with(lastDayOfMonth());
        return dia.compareTo(ultimoDiaDoMes) == 0;
    }
}
