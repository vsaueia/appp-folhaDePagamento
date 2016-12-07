package br.com.folhadepagamento.empregado;

import br.com.folhadepagamento.pagamento.agendamento.AgendamentoDePagamento;
import br.com.folhadepagamento.pagamento.classificacao.ClassificacaoDePagamento;
import br.com.folhadepagamento.pagamento.metodo.MetodoDePagamento;

public class Empregado {
    private int empregadoId;
    private String nome;
    private String endereco;
    private ClassificacaoDePagamento classificacaoDePagamento;
    private AgendamentoDePagamento agendamentoDePagamento;
    private MetodoDePagamento metodoDePagamento;
    private Afiliacao afiliacao;

    public Empregado(int empresadoId, String nome, String endereco) {
        this.empregadoId = empresadoId;
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public ClassificacaoDePagamento obterClassificacaoDePagamento() {
        return classificacaoDePagamento;
    }

    public void informarClassificacaoDePagamento(ClassificacaoDePagamento classificacaoDePagamento) {
        this.classificacaoDePagamento = classificacaoDePagamento;
    }

    public void informarAgendamentoDePagamento(AgendamentoDePagamento agendamentoDePagamento) {
        this.agendamentoDePagamento = agendamentoDePagamento;
    }

    public void informarMetodoDePagamento(MetodoDePagamento metodoDePagamento) {
        this.metodoDePagamento = metodoDePagamento;
    }

    public AgendamentoDePagamento obterAgendamentoDePagamento() {
        return agendamentoDePagamento;
    }

    public MetodoDePagamento obterMetodoDePagamento() {
        return metodoDePagamento;
    }

    public void criarAfiliacao(AfiliacaoEmSindicato afiliacaoSindicato) {
        this.afiliacao = afiliacaoSindicato;
    }

    public Afiliacao obterAfiliacao() {
        return this.afiliacao;
    }
}

