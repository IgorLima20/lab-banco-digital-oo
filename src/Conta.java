import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;

@Getter
public abstract class Conta implements IConta {

	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;

	@Getter(AccessLevel.NONE)
	protected final List<Transacao> transacoes = new ArrayList<>();

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public void sacar(double valor) {
		debitar(valor, TipoTransacao.SAQUE);
	}

	@Override
	public void depositar(double valor) {
		creditar(valor, TipoTransacao.DEPOSITO);
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		this.debitar(valor, TipoTransacao.TRANSFERENCIA_ENVIADA);
		if (contaDestino instanceof Conta destino) {
			destino.creditar(valor, TipoTransacao.TRANSFERENCIA_RECEBIDA);
		} else {
			contaDestino.depositar(valor);
		}
	}

	/**
	 * Valor que pode ser movimentado pela conta. Subclasses podem
	 * sobrescrever para considerar limites adicionais.
	 */
	public double getSaldoDisponivel() {
		return saldo;
	}

	public List<Transacao> getTransacoes() {
		return Collections.unmodifiableList(transacoes);
	}

	protected void debitar(double valor, TipoTransacao tipo) {
		validarValor(valor);
		if (valor > getSaldoDisponivel()) {
			throw new SaldoInsuficienteException(valor, getSaldoDisponivel());
		}
		saldo -= valor;
		transacoes.add(new Transacao(tipo, valor));
	}

	protected void creditar(double valor, TipoTransacao tipo) {
		validarValor(valor);
		saldo += valor;
		transacoes.add(new Transacao(tipo, valor));
	}

	private void validarValor(double valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("O valor da operação deve ser maior que zero.");
		}
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("CPF: %s", this.cliente.getCpf()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}

	protected void imprimirTransacoes() {
		System.out.println("--- Transações ---");
		if (transacoes.isEmpty()) {
			System.out.println("Nenhuma transação realizada.");
		}
		transacoes.forEach(transacao -> System.out.println(transacao.formatar()));
	}
}
