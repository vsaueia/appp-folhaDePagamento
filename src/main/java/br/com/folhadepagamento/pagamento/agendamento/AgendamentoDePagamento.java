package br.com.folhadepagamento.pagamento.agendamento;

import java.time.LocalDate;

public interface AgendamentoDePagamento {
    boolean ehDiaDoPagamento(LocalDate dia);
}
