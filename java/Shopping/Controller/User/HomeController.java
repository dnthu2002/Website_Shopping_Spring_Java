package Shopping.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class HomeController extends BaseController{

	@RequestMapping(value = {"/", "/home"})
	public ModelAndView Index() {
		_mvShare.addObject("banner",homeServiceImpl.getDataBanners());
		_mvShare.addObject("product", homeServiceImpl.getDataProducts());
		_mvShare.addObject("productnew", homeServiceImpl.getProductbynew());
		_mvShare.addObject("producthighlight", homeServiceImpl.getProductbyhighlight());
		_mvShare.addObject("productseller", homeServiceImpl.getProductbyseller());
		_mvShare.addObject("activeHome", "active");
		_mvShare.setViewName("user/index");
		return _mvShare; 
	}
	@RequestMapping(value = "product")
	public ModelAndView Product() {
		ModelAndView mv = new ModelAndView("user/product"); 
		return mv; 
	}
}
