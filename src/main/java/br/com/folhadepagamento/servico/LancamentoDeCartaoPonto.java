package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.empregado.CartaoDePonto;
import br.com.folhadepagamento.empregado.Empregado;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoPorHora;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LancamentoDeCartaoPonto implements Transacao {

    private final LocalDate dia;
    private final BigDecimal horasLancadas;
    private final int empregadoId;

    public LancamentoDeCartaoPonto(LocalDate dia, BigDecimal horasLancadas, int empregadoId) {
        this.dia = dia;
        this.horasLancadas = horasLancadas;
        this.empregadoId = empregadoId;
    }

    @Override
    public void executar() {
        Empregado empregado = FolhaDePagamentoDatabase.buscarEmpregado(empregadoId);
        validarSeEmpregadoExiste(empregado);
        if (empregado.obterClassificacaoDePagamento() instanceof ClassificacaoPorHora) {
            ClassificacaoPorHora classificacaoPorHora = (ClassificacaoPorHora) empregado.obterClassificacaoDePagamento();
            CartaoDePonto cartaoDePonto = new CartaoDePonto(dia, horasLancadas);
            classificacaoPorHora.adicionarCartaoPonto(new CartaoDePonto(dia, horasLancadas));
        } else {
            throw new IllegalStateException("Só é permitido lançar horas de um empregado por hora.");
        }

    }

    private void validarSeEmpregadoExiste(Empregado empregado) {
        if (empregado == null) {
            throw new IllegalArgumentException("Empregado não existe");
        }
    }
}
