package br.com.folhadepagamento.pagamento.classificacao;

import br.com.folhadepagamento.empregado.ChequeSalario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ClassificacaoDePagamento {
    BigDecimal calcularPagamento(ChequeSalario chequeSalario, LocalDate inicioDoPeriodo, LocalDate fimDoPeriodo);

    default List<LocalDate> obterDatasParaCalculoDoPagamento(LocalDate periodoInicial, LocalDate periodoFinal) {
        return Stream.iterate(periodoInicial, dia -> dia.plusDays(1))
                .limit(ChronoUnit.DAYS.between(periodoFinal, periodoInicial))
                .collect(Collectors.toList());
    }
}
