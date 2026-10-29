
public class Main {

	public static void main(String[] args) {
		Banco banco = new Banco("Banco Digital DIO");

		Cliente venilton = new Cliente();
		venilton.setNome("Venilton");
		venilton.setCpf("123.456.789-00");

		Cliente maria = new Cliente("Maria", "987.654.321-00");

		Conta cc = new ContaCorrente(venilton);
		ContaPoupanca poupanca = new ContaPoupanca(venilton);
		Conta ccMaria = new ContaCorrente(maria, 1000);

		banco.adicionarConta(cc);
		banco.adicionarConta(poupanca);
		banco.adicionarConta(ccMaria);

		cc.depositar(100);
		cc.transferir(100, poupanca);
		poupanca.aplicarRendimento();

		ccMaria.depositar(300);
		ccMaria.sacar(800); // utiliza parte do limite (cheque especial)

		try {
			cc.sacar(1000);
		} catch (SaldoInsuficienteException e) {
			System.out.println("Operação negada: " + e.getMessage());
		}
		System.out.println();

		cc.imprimirExtrato();
		System.out.println();
		poupanca.imprimirExtrato();
		System.out.println();
		ccMaria.imprimirExtrato();
		System.out.println();

		System.out.println(String.format("=== %s ===", banco.getNome()));
		banco.listarClientes().forEach(System.out::println);
		banco.buscarConta(3).ifPresent(conta -> System.out.println("Conta 3 pertence a: " + conta.getCliente().getNome()));
		System.out.println(String.format("Saldo total das contas: %.2f", banco.getSaldoTotal()));
	}

}
