package Shopping.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Shopping.DTO.PaginationDTO;
import Shopping.Service.User.BrandServiceImpl;
import Shopping.Service.User.CategoryServiceImpl;
import Shopping.Service.User.PaginationServiceImpl;
@Controller
public class BrandController extends BaseController {
	
	@Autowired
	private BrandServiceImpl brandServiceImpl;
	
	@Autowired
	private PaginationServiceImpl paginateService;
	
	private int totalProductsPage = 9;
	
	@RequestMapping(value = "/brand/{id}")
	public ModelAndView Product(@PathVariable String id) {
		_mvShare.setViewName("user/shop/brand");
		int totalData = brandServiceImpl.GetAllProductsByBrandID(Integer.parseInt(id)).size();
		PaginationDTO paginateInfo = paginateService.GetInfoPagination(totalData, totalProductsPage, 1);
		_mvShare.addObject("idBrand", id);
		_mvShare.addObject("paginateInfobrand", paginateInfo);
		_mvShare.addObject("productsPaginatebrand", brandServiceImpl.GetDataProductsPaginatebyBrand(Integer.parseInt(id), paginateInfo.getStart(), totalProductsPage));
		return _mvShare;
	}
	@RequestMapping(value = "/brand/{id}/{currentPage}")
	public ModelAndView Product(@PathVariable String id, @PathVariable String currentPage) {
		_mvShare.setViewName("user/shop/brand");
		int totalData = brandServiceImpl.GetAllProductsByBrandID(Integer.parseInt(id)).size();
		PaginationDTO paginateInfo = paginateService.GetInfoPagination(totalData, totalProductsPage, Integer.parseInt(currentPage));
		_mvShare.addObject("idBrand", id);
		_mvShare.addObject("paginateInfobrand", paginateInfo);
		_mvShare.addObject("productsPaginatebrand", brandServiceImpl.GetDataProductsPaginatebyBrand(Integer.parseInt(id) ,paginateInfo.getStart(), totalProductsPage));
		return _mvShare;
	}
}