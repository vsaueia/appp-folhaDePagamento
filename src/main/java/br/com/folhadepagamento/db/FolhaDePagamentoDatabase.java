package br.com.folhadepagamento.db;

import br.com.folhadepagamento.empregado.Empregado;

import java.util.HashMap;
import java.util.Map;

public class FolhaDePagamentoDatabase {
    private static Map<Integer, Empregado> baseDeDados = new HashMap<>();

    public static Empregado buscarEmpregado(int empregadoId) {
        return baseDeDados.get(empregadoId);
    }

    public static void adicionarEmpregado(int empregadoId, Empregado empregado) {
        baseDeDados.put(empregadoId, empregado);
    }

    public static void removerEmpregado(int empregadoId) {
        baseDeDados.remove(empregadoId);
    }
}
