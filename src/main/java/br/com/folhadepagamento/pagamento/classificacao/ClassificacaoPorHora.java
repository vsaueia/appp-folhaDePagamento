package br.com.folhadepagamento.pagamento.classificacao;

import br.com.folhadepagamento.empregado.CartaoDePonto;
import br.com.folhadepagamento.empregado.ChequeSalario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassificacaoPorHora implements ClassificacaoDePagamento {
    public static final BigDecimal JORNADA_DIARIA_DE_TRABALHO = BigDecimal.valueOf(8);
    private BigDecimal valorPorHora;
    private Map<LocalDate, CartaoDePonto> cartoesDePonto = new HashMap<>();

    public ClassificacaoPorHora(BigDecimal valorPorHora) {
        this.valorPorHora = valorPorHora;
    }

    public BigDecimal obterSalarioPorHora() {
        return this.valorPorHora;
    }

    public CartaoDePonto obterCartaoPontoDoDia(LocalDate dataBase) {
        return cartoesDePonto.get(dataBase);
    }

    public void adicionarCartaoPonto(CartaoDePonto cartaoDePonto) {
        this.cartoesDePonto.put(cartaoDePonto.obterDia(), cartaoDePonto);
    }

    @Override
    public BigDecimal calcularPagamento(ChequeSalario chequeSalario) {
        LocalDate periodoInicial = chequeSalario.obterDia().minusDays(7);

        List<LocalDate> dias = Stream.iterate(periodoInicial, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(periodoInicial, chequeSalario.obterDia()))
                .collect(Collectors.toList());

        BigDecimal horasTotaisTrabalhadas = BigDecimal.ZERO;
        BigDecimal horasExtrasTotais = BigDecimal.ZERO;
        for (LocalDate dia : dias) {
            CartaoDePonto cartaoDePonto = cartoesDePonto.get(dia);
            if (cartaoDePonto != null) {
                BigDecimal quantidadeDeHoras = cartaoDePonto.obterQuantidadeDeHoras();
                BigDecimal horasExtras = BigDecimal.ZERO;
                if (quantidadeDeHoras.compareTo(JORNADA_DIARIA_DE_TRABALHO) > 0) {
                    horasExtras = quantidadeDeHoras.subtract(JORNADA_DIARIA_DE_TRABALHO);
                    quantidadeDeHoras = JORNADA_DIARIA_DE_TRABALHO;
                }
                horasTotaisTrabalhadas.add(quantidadeDeHoras);
                horasExtrasTotais.add(horasExtras);
            }
        }
        BigDecimal salario = horasExtrasTotais.multiply(valorPorHora);
        BigDecimal salarioDeHorasExtras = horasExtrasTotais.multiply(valorPorHora.multiply(BigDecimal.valueOf(1.3)));
        return salario.add(salarioDeHorasExtras);
    }
}
