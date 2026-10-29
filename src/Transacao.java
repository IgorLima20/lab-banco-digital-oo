import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Value;

@Value
public class Transacao {

	private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	TipoTransacao tipo;
	double valor;
	LocalDateTime data;

	public Transacao(TipoTransacao tipo, double valor) {
		this.tipo = tipo;
		this.valor = valor;
		this.data = LocalDateTime.now();
	}

	public String formatar() {
		return String.format("%s | %-22s | %10.2f", data.format(FORMATO_DATA), tipo.getDescricao(), valor);
	}

}
