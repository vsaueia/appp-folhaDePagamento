package br.com.folhadepagamento.empregado;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AfiliacaoEmSindicato implements Afiliacao {

    private Map<LocalDate, DescontoEmFolha> descontos = new HashMap<>();

    public DescontoEmFolha obterDesconto(LocalDate dia) {
        return this.descontos.get(dia);
    }

    public void adicionarDesconto(DescontoEmFolha descontoEmFolha) {
        this.descontos.put(descontoEmFolha.obterDia(), descontoEmFolha);
    }
}
