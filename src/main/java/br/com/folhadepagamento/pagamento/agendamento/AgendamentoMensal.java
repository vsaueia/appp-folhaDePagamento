package br.com.folhadepagamento.pagamento.agendamento;

import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public class AgendamentoMensal implements AgendamentoDePagamento {

    @Override
    public boolean ehDiaDoPagamento(LocalDate dia) {
        return ehUltimoDiaDoMes(dia);
    }

    @Override
    public LocalDate obterPeriodo(LocalDate inicioDoPeriodo) {
        // procurar melhor maneira de chegar ao ultimo dia do mes
        return inicioDoPeriodo.plusMonths(1).withDayOfMonth(1).minusDays(1);
    }

    private boolean ehUltimoDiaDoMes(LocalDate dia) {
        LocalDate ultimoDiaDoMes = dia.with(lastDayOfMonth());
        return dia.compareTo(ultimoDiaDoMes) == 0;
    }
}
