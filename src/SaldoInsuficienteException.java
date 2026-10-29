
public class SaldoInsuficienteException extends RuntimeException {

	public SaldoInsuficienteException(double valorSolicitado, double saldoDisponivel) {
		super(String.format("Saldo insuficiente. Solicitado: %.2f | Disponível: %.2f", valorSolicitado, saldoDisponivel));
	}

}
