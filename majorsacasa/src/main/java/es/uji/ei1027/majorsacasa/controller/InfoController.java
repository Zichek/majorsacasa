package es.uji.ei1027.majorsacasa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class InfoController {
	
	@RequestMapping("/aboutus")
	public String aboutUs(Model model) {
		model.addAttribute("pactiva", "aboutus");
		return "info/aboutus";
	}
	
}
