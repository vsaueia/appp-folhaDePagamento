package br.com.folhadepagamento.pagamento.metodo;

import br.com.folhadepagamento.empregado.ChequeSalario;

public interface MetodoDePagamento {
    void pagar(ChequeSalario chequeSalario);
}
