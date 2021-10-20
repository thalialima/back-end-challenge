package br.com.alura.aluraflix.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//interceptador de tratamento de erros
//qualquer método que gere exception será redirecionada
//para este interceptor
@RestControllerAdvice
public class ValidationErrorHandler {

	@Autowired
	//essa interface ajuda a pegar mensagens de erro
	private MessageSource messageSource;

	//indica qual status será devolvido
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	//esse método dever ser chamado quando houver uma exceção
	//dentro de algum controller
	//Deve-se informar o tipo de exceção a ser tratada
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<FormErrorDTO> handle(MethodArgumentNotValidException exception) {
		
		List<FormErrorDTO> errorsDTO = new ArrayList<FormErrorDTO>();
		//getBindingResult() pega o resultado das validações
		//getFieldErrors() contém todos os erros de field(erros de formulário)
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			FormErrorDTO error = new FormErrorDTO(e.getField(), message);
			errorsDTO.add(error);
		});
		
		//retorna a lista de erros do dto
		return errorsDTO;
	}
}
