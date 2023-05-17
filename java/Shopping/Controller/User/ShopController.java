package Shopping.Controller.User;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.servlet.ModelAndView;

	import Shopping.DTO.PaginationDTO;
	import Shopping.Service.User.PaginationServiceImpl;
import Shopping.Service.User.ShopServiceImpl;
	@Controller
	public class ShopController  extends BaseController {
		
		@Autowired
		private ShopServiceImpl shopServiceImpl;
		
		@Autowired
		private PaginationServiceImpl paginateService;
		
		private int totalProductsPage = 9;
		
		@RequestMapping(value = "/shop")
		public ModelAndView Product() {
			_mvShare.setViewName("user/shop/shop");
			int totalData = shopServiceImpl.GetAllProducts().size();
			PaginationDTO paginateInfo = paginateService.GetInfoPagination(totalData, totalProductsPage, 1);
			_mvShare.addObject("AllpaginateInfo", paginateInfo);
			_mvShare.addObject("AllproductsPaginate", shopServiceImpl.GetAllProductsPaginate(paginateInfo.getStart(), totalProductsPage));
			_mvShare.addObject("activeShop", "active");
			return _mvShare;
		}
		@RequestMapping(value = "/shop/{currentPage}")
		public ModelAndView Product(@PathVariable String currentPage) {
			_mvShare.setViewName("user/shop/shop");
			int totalData = shopServiceImpl.GetAllProducts().size();
			PaginationDTO paginateInfo = paginateService.GetInfoPagination(totalData, totalProductsPage, Integer.parseInt(currentPage));
			_mvShare.addObject("AllpaginateInfo", paginateInfo);
			_mvShare.addObject("AllproductsPaginate", shopServiceImpl.GetAllProductsPaginate(paginateInfo.getStart(), totalProductsPage));
			return _mvShare;
		}
	
}
