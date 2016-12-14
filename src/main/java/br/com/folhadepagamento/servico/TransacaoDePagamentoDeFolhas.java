package br.com.folhadepagamento.servico;

import br.com.folhadepagamento.db.FolhaDePagamentoDatabase;
import br.com.folhadepagamento.empregado.ChequeSalario;
import br.com.folhadepagamento.empregado.Empregado;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransacaoDePagamentoDeFolhas implements Transacao {

    private LocalDate diaDoPagamento;
    private Map<Integer, ChequeSalario> chequesSalario = new HashMap<>();

    public TransacaoDePagamentoDeFolhas(LocalDate diaDoPagamento) {
        this.diaDoPagamento = diaDoPagamento;
    }

    @Override
    public void executar() {
        List<Empregado> empregados = FolhaDePagamentoDatabase.buscarTodosOsEmpregados();
        for (Empregado empregado : empregados) {
            if (empregado.ehDiaDePagamento(diaDoPagamento)) {
                ChequeSalario chequeSalario = new ChequeSalario(diaDoPagamento);
                chequesSalario.put(empregado.obterId(), chequeSalario);
                empregado.receberPagamento(chequeSalario);
            }
        }
    }

    public ChequeSalario obterChequeSalario(int empregadoId) {
        return chequesSalario.get(empregadoId);
    }
}
