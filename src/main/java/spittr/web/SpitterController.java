package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spittr.entity.Spitter;
import spittr.mapper.SpitterMapper;
import spittr.util.FileUtil;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
@PropertySource("classpath:application.properties")
@RequestMapping("/spittr")
public class SpitterController {
	private SpitterMapper spitterMapper;

	@Value("${macos.uploadFolder}")
	private String uploadFolder;
	@Autowired
	public SpitterController(SpitterMapper spitterMapper) {
		this.spitterMapper = spitterMapper;
	}
	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public String registerForm(Model model){
		model.addAttribute(new Spitter());
		return "registerForm";
	}
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public String register(
			Part headIcon,
			@Valid Spitter spitter,
			Errors errors,
			RedirectAttributes model){
		System.out.println(spitter);
		if(errors.hasErrors()){
			return "registerForm";
		}
		spitterMapper.save(spitter);
		//FileUtil.saveToDisk(headIcon,uploadFolder);
		FileUtil.saveToQiNiu(headIcon);
		model.addAttribute("username",spitter.getUsername());
		model.addFlashAttribute(spitter);
		return "redirect:/spittr/info/{username}";
	}
	@RequestMapping(value="/info/{username}",method = RequestMethod.GET)
	public String info(
			@PathVariable String username,
			Model model
	){
		if(!model.containsAttribute("spitter")){
			model.addAttribute(spitterMapper.findByUsername(username));
		}
		return "info";
	}
}
