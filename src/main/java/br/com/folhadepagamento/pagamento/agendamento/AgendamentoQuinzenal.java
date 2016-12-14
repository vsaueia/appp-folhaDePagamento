package br.com.folhadepagamento.pagamento.agendamento;

import java.time.LocalDate;

public class AgendamentoQuinzenal implements AgendamentoDePagamento {

    @Override
    public boolean ehDiaDoPagamento(LocalDate dia) {
        return false;
    }
}
