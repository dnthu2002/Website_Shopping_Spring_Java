package Shopping.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Shopping.Controller.User.BaseController;
import Shopping.Service.User.HomeServiceImpl;
@Controller
public class AdminController extends BaseController{
	@Autowired
	HomeServiceImpl homeServiceImpl;
	@RequestMapping(value = {"/admin/"})
	public ModelAndView Index() {
//		_mvShare.addObject("banner",homeServiceImpl.getDataBanners());
//		_mvShare.addObject("product", homeServiceImpl.getDataProducts());
//		_mvShare.addObject("productnew", homeServiceImpl.getProductbynew());
//		_mvShare.addObject("producthighlight", homeServiceImpl.getProductbyhighlight());
		_mvShare.addObject("productseller", homeServiceImpl.getProductbyseller());
		_mvShare.addObject("activeAdminHome", "active");
		_mvShare.setViewName("admin/index");
		return _mvShare; 
}
}
