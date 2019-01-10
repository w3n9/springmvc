package spittr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("*")
public class NotFoundController {
	@RequestMapping(method = RequestMethod.GET)
	public String notFound404(){
		return "404";
	}
}
