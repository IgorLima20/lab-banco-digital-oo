import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoTransacao {

	DEPOSITO("Depósito"),
	SAQUE("Saque"),
	TRANSFERENCIA_ENVIADA("Transferência enviada"),
	TRANSFERENCIA_RECEBIDA("Transferência recebida"),
	RENDIMENTO("Rendimento");

	private final String descricao;

}
