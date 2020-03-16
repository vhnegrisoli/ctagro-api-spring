package br.com.ctagroapi.ctagroapi.modules.comum.exception;

import lombok.Data;

@Data
public class ValidacaoExceptionDetails {
    private int status;
    private String message;
}
