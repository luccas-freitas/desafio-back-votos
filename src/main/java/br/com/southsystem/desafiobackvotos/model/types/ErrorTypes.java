package br.com.southsystem.desafiobackvotos.model.types;

public enum ErrorTypes {
    ERRO_ENCERRAR_PAUTA("Erro ao encerrar pauta"),
    PAUTA_NAO_CADASTRADA("Pauta não cadastrada"),
    PAUTA_ENCERRADA("Pauta já foi encerrada"),
    UNABLE_TO_VOTE("CPF não habilitado para votar"),
    ASSOCIADO_JA_VOTOU("Associado já votou");

    private final String value;

    ErrorTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
