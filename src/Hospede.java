import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hospede {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String endereco;
    private String contato;
    private List<Estadia> historicoEstadias;

    public Hospede(String nome, String cpf, LocalDate dataNascimento, String endereco, String contato) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.contato = contato;
        this.historicoEstadias = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void adicionarEstadia(Estadia estadia) {
        historicoEstadias.add(estadia);
    }

    public void listarHistoricoEstadias() {
        if (historicoEstadias.isEmpty()) {
            System.out.println("Nenhuma estadia encontrada para o hóspede.");
            return;
        }
        System.out.println("Histórico de estadias do hóspede " + nome + ":");
        for (Estadia estadia : historicoEstadias) {
            System.out.println(estadia);
        }
    }

	public void editarInformacoes(String novoNome, String novoEndereco, String novoContato) {
		// TODO Auto-generated method stub
		
	}
}
