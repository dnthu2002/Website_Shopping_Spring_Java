package Shopping.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController extends BaseController {
	@RequestMapping(value = "/contact")
	public ModelAndView Contact() {
		_mvShare.addObject("activeContact", "active");
		_mvShare.setViewName("user/contact/contact");
		return _mvShare; 
	}
}
