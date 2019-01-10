package spittr.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND,reason = "Spittle not found")
public class SpittleNotFoundException extends RuntimeException {
}
