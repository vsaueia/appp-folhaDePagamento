package br.com.folhadepagamento.pagamento.metodo;

import br.com.folhadepagamento.empregado.ChequeSalario;

public class PagamentoDiretoAoEmpregado implements MetodoDePagamento {
    @Override
    public void pagar(ChequeSalario chequeSalario) {
        chequeSalario.adicionarCampo("Disposicao", "Direto");
    }
}
