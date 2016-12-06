package com.skt.dlab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Rock Kang(cserock@gmail.com) on 2016. 12. 6..
 */
@Controller
public class WebController {

	static final Logger log = LoggerFactory.getLogger(WebController.class);

	@RequestMapping("/")
	public String index(Model model){
		log.debug("index");
		return "redirect:/swagger-ui.html";
	}
}
