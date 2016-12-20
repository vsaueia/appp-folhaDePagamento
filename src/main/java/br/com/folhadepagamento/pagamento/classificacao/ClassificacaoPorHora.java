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
    public BigDecimal calcularPagamento(ChequeSalario chequeSalario, LocalDate inicioDoPeriodo, LocalDate fimDoPeriodo) {

        BigDecimal horasTotaisTrabalhadas = BigDecimal.ZERO;
        BigDecimal horasExtrasTotais = BigDecimal.ZERO;
        List<LocalDate> diasNoPeriodo = obterDatasParaCalculoDoPagamento(inicioDoPeriodo, fimDoPeriodo);

        for (LocalDate dia : diasNoPeriodo) {
            CartaoDePonto cartaoDePonto = cartoesDePonto.get(dia);
            if (cartaoDePonto != null) {
                BigDecimal quantidadeDeHoras = cartaoDePonto.obterQuantidadeDeHoras();
                BigDecimal horasExtras = BigDecimal.ZERO;
                if (quantidadeDeHoras.compareTo(JORNADA_DIARIA_DE_TRABALHO) > 0) {
                    horasExtras = quantidadeDeHoras.subtract(JORNADA_DIARIA_DE_TRABALHO);
                    quantidadeDeHoras = JORNADA_DIARIA_DE_TRABALHO;
                }
                horasTotaisTrabalhadas = horasTotaisTrabalhadas.add(quantidadeDeHoras);
                horasExtrasTotais = horasExtrasTotais.add(horasExtras);
            }
        }
        BigDecimal salario = horasTotaisTrabalhadas.multiply(valorPorHora);
        BigDecimal salarioDeHorasExtras = horasExtrasTotais.multiply(valorPorHora.multiply(BigDecimal.valueOf(1.5)));
        return salario.add(salarioDeHorasExtras);
    }
}
