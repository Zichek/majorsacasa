
package es.uji.ei1027.majorsacasa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cas")
public class CommitteeControler {
	
	@RequestMapping("/committee")
	public String register() {		
		return "cas/committee";
	}
	
}
