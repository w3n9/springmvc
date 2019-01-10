package spittr.handler;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/*
* 统一异常处理类
* */
@ControllerAdvice
public class AppWideExceptionHandler {
	@ExceptionHandler(DuplicateKeyException.class)
	public String duplicateHandler(){
		return "error/duplicate";
	}
}
