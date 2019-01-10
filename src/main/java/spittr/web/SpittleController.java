package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spittr.entity.Spittle;
import spittr.exception.SpittleNotFoundException;
import spittr.mapper.SpittleMapper;


@Controller
@RequestMapping("/spittles")
public class SpittleController {

	private SpittleMapper spittleMapper;

	@Autowired
	public SpittleController(SpittleMapper spittleMapper) {
		this.spittleMapper = spittleMapper;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String spittles(
			@RequestParam(defaultValue = "100") int before,
			@RequestParam(defaultValue = "10") int count,
			Model model
	){
		model.addAttribute(spittleMapper.findSpittles(before,count));
		return "spittles";
	}
	@RequestMapping(method = RequestMethod.GET,value = "/{spittleId}")
	public String spittle(
			@PathVariable int spittleId,
			Model model
	){
		Spittle spittle=spittleMapper.findOne(spittleId);
		System.out.println(spittle==null);
		if(spittle==null){
			throw new SpittleNotFoundException();
		}else{
			model.addAttribute(spittle);
		}
		return "spittle";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String saveSpittle(Spittle spittle,Model model){
		spittleMapper.save(spittle);
		return "redirect:/spittles";
	}
}
