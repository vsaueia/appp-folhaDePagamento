package br.com.folhadepagamento.pagamento.agendamento;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class AgendamentoSemanal implements AgendamentoDePagamento {
    @Override
    public boolean ehDiaDoPagamento(LocalDate dia) {
        return dia.getDayOfWeek() == DayOfWeek.FRIDAY;
    }

    @Override
    public LocalDate obterPeriodo(LocalDate inicioDoPeriodo) {
        return inicioDoPeriodo.minusDays(7);
    }
}
