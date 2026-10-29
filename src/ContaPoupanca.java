import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaPoupanca extends Conta {

	private static final double TAXA_RENDIMENTO_PADRAO = 0.5;

	/** Taxa de rendimento mensal em percentual (ex.: 0.5 = 0,5% a.m.). */
	private double taxaRendimento = TAXA_RENDIMENTO_PADRAO;

	public ContaPoupanca(Cliente cliente) {
		super(cliente);
	}

	public void aplicarRendimento() {
		double rendimento = saldo * taxaRendimento / 100;
		if (rendimento > 0) {
			super.creditar(rendimento, TipoTransacao.RENDIMENTO);
		}
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato Conta Poupança ===");
		super.imprimirInfosComuns();
		System.out.println(String.format("Taxa de rendimento: %.2f%% a.m.", this.taxaRendimento));
		super.imprimirTransacoes();
	}
}
