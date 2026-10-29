# Criando um Banco Digital com Java e Orientação a Objetos

## 02/08/2021 - [Mentoria #1: Tire Suas Dúvidas Sobre Orientação a Objetos](https://www.youtube.com/watch?v=YS6ouOhkyNI)

Desafio: Considerando nosso conhecimento no domínio bancário, iremos abstrair uma solução Orientada a Objetos em Java. Para isso, vamos interpretar o seguinte cenário:
“Um banco oferece aos seus clientes dois tipos de contas (corrente e poupança), as quais possuem as funcionalidades de depósito, saque e transferência (entre contas da própria instituição).”

### Abstração
Habilidade de concentrar-se nos aspectos essenciais de um domínio, ignorando características menos importantes ou acidentais. Nesse contexto, objetos são abstrações de entidades existentes no domínio em questão.

### Encapsulamento
Encapsular significa esconder a implementação dos objetos, criando assim interfaces de uso mais concisas e fáceis de usar/entender. O encapsulamento favorece principalmente dois aspectos de um sistema: a manutenção e a evolução.

### Herança
Permite que você defina uma classe filha que reutiliza (herda), estende ou modifica o comportamento de uma classe pai. A classe cujos membros são herdados é chamada de classe base. A classe que herda os membros da classe base é chamada de classe derivada.

### Polimorfismo
Capacidade de um objeto poder ser referenciado de várias formas, ou seja, é capacidade de tratar objetos criados a partir das classes específicas como objetos de uma classe genérica. Cuidado, polimorfismo não quer dizer que o objeto fica se transformando, muito pelo contrário, um objeto nasce de um tipo e morre daquele tipo, o que pode mudar é a maneira como nos referimos a ele.

## Melhorias implementadas

### Lombok
O projeto passou a usar [Lombok](https://projectlombok.org/) via Maven (`pom.xml`), eliminando getters, setters, construtores, `toString` e `equals/hashCode` escritos à mão (`@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@RequiredArgsConstructor`, `@ToString`, `@EqualsAndHashCode`, `@Value`).

> Na IDE, é necessário ter o suporte ao Lombok habilitado (IntelliJ já traz o plugin; no Eclipse, execute o `lombok.jar` para instalá-lo).

Para executar: `mvn compile exec:java`

### Novas funcionalidades
- **Cliente**: novo atributo `cpf`, usado como identidade (`equals/hashCode`).
- **Validações**: saques/depósitos/transferências com valor menor ou igual a zero lançam `IllegalArgumentException`; saques acima do saldo disponível lançam `SaldoInsuficienteException`.
- **Histórico de transações**: cada operação gera uma `Transacao` (tipo, valor e data) exibida no extrato. Os tipos estão no enum `TipoTransacao`.
- **Conta Corrente**: atributo `limite` (cheque especial, padrão de R$ 500,00) somado ao saldo disponível.
- **Conta Poupança**: atributo `taxaRendimento` e método `aplicarRendimento()`.
- **Banco**: métodos `adicionarConta`, `buscarConta`, `listarClientes` e `getSaldoTotal`.
