import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Banco {

	private String nome;
	private List<Conta> contas = new ArrayList<>();

	public Banco(String nome) {
		this.nome = nome;
	}

	public void adicionarConta(Conta conta) {
		contas.add(conta);
	}

	public Optional<Conta> buscarConta(int numero) {
		return contas.stream()
				.filter(conta -> conta.getNumero() == numero)
				.findFirst();
	}

	public List<Cliente> listarClientes() {
		return contas.stream()
				.map(Conta::getCliente)
				.distinct()
				.toList();
	}

	public double getSaldoTotal() {
		return contas.stream()
				.mapToDouble(Conta::getSaldo)
				.sum();
	}

}
