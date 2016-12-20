package br.com.folhadepagamento.pagamento.agendamento;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class AgendamentoQuinzenal implements AgendamentoDePagamento {

    @Override
    public boolean ehDiaDoPagamento(LocalDate dia) {
        return dia.get(ChronoField.ALIGNED_WEEK_OF_YEAR) % 2 == 0
                && dia.getDayOfWeek() == DayOfWeek.FRIDAY;
    }

    @Override
    public LocalDate obterPeriodo(LocalDate inicioDoPeriodo) {
        return inicioDoPeriodo.minusDays(14);
    }
}
