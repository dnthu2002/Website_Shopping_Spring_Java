package Shopping.Controller.User;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import Shopping.Service.User.AccountServiceImpl;
import Shopping.Service.User.CategoryServiceImpl;
import Shopping.Service.User.ContactServiceImpl;
import Shopping.Service.User.HomeServiceImpl;
import Shopping.Service.User.OrderProductServiceImpl;

@Controller
public class BaseController {
	@Autowired
	HomeServiceImpl homeServiceImpl;
	@Autowired
	OrderProductServiceImpl orderProductServiceImpl;
	@Autowired
	AccountServiceImpl accountServiceImpl;
	@Autowired 
	ContactServiceImpl contactServiceImpl;
	public ModelAndView _mvShare = new ModelAndView();
	
	@PostConstruct
	public ModelAndView Init () {
		_mvShare.addObject("menus", homeServiceImpl.getDataBanners());
		_mvShare.addObject("category",homeServiceImpl.getDataCategorys());
		_mvShare.addObject("brand", homeServiceImpl.getDataBrands());
		_mvShare.addObject("countsold", homeServiceImpl.getDataCountSolds());
		_mvShare.addObject("bestseller", homeServiceImpl.getDataBestSellers());
		_mvShare.addObject("rating", homeServiceImpl.getDataRatings());
		_mvShare.addObject("rating", homeServiceImpl.getDataRatings());
		_mvShare.addObject("showordernew", orderProductServiceImpl.getDataOrderNew());
		_mvShare.addObject("showallorder", orderProductServiceImpl.getDataAllOrder());
		_mvShare.addObject("datacustomer", accountServiceImpl.getDataCustomer());
		_mvShare.addObject("datacontact", contactServiceImpl.getDataAllContact());

		return _mvShare;
	}
}