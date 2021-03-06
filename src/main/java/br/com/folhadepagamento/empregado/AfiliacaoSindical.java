package br.com.folhadepagamento.empregado;

import br.com.folhadepagamento.util.DateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AfiliacaoSindical implements Afiliacao {

    private Map<LocalDate, DescontoEmFolha> descontos = new HashMap<>();
    private int membroId;
    private BigDecimal taxa;

    public AfiliacaoSindical(int membroId, BigDecimal taxa) {
        this.membroId = membroId;
        this.taxa = taxa;
    }

    public DescontoEmFolha obterDesconto(LocalDate dia) {
        return this.descontos.get(dia);
    }

    public void adicionarDesconto(DescontoEmFolha descontoEmFolha) {
        this.descontos.put(descontoEmFolha.obterDia(), descontoEmFolha);
    }

    public BigDecimal obterTaxa() {
        return taxa;
    }

    public int obterMembroId() {
        return membroId;
    }

    @Override
    public BigDecimal calcularDescontos(ChequeSalario chequeSalario, LocalDate inicioDoPeriodo, LocalDate fimDoPeriodo) {
        long quantidadeDeSextasFeirasNoPeriodo = DateUtil.extrairQuantidadeDeSextasFeirasNoPeriodo(inicioDoPeriodo, fimDoPeriodo);
        return taxa.multiply(BigDecimal.valueOf(quantidadeDeSextasFeirasNoPeriodo));
    }
}
