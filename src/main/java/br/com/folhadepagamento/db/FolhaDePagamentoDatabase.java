package br.com.folhadepagamento.db;

import br.com.folhadepagamento.empregado.Empregado;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FolhaDePagamentoDatabase {
    private static Map<Integer, Empregado> empregados = new HashMap<>();
    private static Map<Integer, Empregado> membrosDeAfiliacao = new HashMap<>();

    public static Empregado buscarEmpregado(int empregadoId) {
        return empregados.get(empregadoId);
    }

    public static void adicionarEmpregado(int empregadoId, Empregado empregado) {
        empregados.put(empregadoId, empregado);
    }

    public static void removerEmpregado(int empregadoId) {
        empregados.remove(empregadoId);
    }

    public static void adicionarAfiliacao(int afiliacaoId, Empregado empregado) {
        membrosDeAfiliacao.put(afiliacaoId, empregado);
    }

    public static Empregado buscarEmpregadoPorAfiliacao(int afiliacaoId) {
        return membrosDeAfiliacao.get(afiliacaoId);
    }

    public static void removerMembro(int membroId) {
        membrosDeAfiliacao.remove(membroId);
    }

    public static List<Empregado> buscarTodosOsEmpregados() {
        return empregados.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
    }
}
