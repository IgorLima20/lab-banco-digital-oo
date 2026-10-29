import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaCorrente extends Conta {

	private static final double LIMITE_PADRAO = 500;

	private double limite;

	public ContaCorrente(Cliente cliente) {
		this(cliente, LIMITE_PADRAO);
	}

	public ContaCorrente(Cliente cliente, double limite) {
		super(cliente);
		this.limite = limite;
	}

	@Override
	public double getSaldoDisponivel() {
		return saldo + limite;
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato Conta Corrente ===");
		super.imprimirInfosComuns();
		System.out.println(String.format("Limite: %.2f", this.limite));
		System.out.println(String.format("Saldo disponivel: %.2f", this.getSaldoDisponivel()));
		super.imprimirTransacoes();
	}

}
