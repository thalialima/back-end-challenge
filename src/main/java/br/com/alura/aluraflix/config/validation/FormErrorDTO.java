package br.com.alura.aluraflix.config.validation;

//essa classe representa um erro de validação
//de algum campo
public class FormErrorDTO {

	private String field;
	private String errorMessage;

	public FormErrorDTO(String field, String errorMessage) {
		this.field = field;
		this.errorMessage = errorMessage;
	}

	public String getField() {
		return field;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
