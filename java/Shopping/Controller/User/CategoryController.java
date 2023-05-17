package Shopping.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Shopping.DTO.PaginationDTO;
import Shopping.Entity.Category;
import Shopping.Service.User.CategoryServiceImpl;
import Shopping.Service.User.PaginationServiceImpl;
@Controller
public class CategoryController extends BaseController {
	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	@Autowired
	private PaginationServiceImpl paginateService;
	
	private int totalProductsPage = 9;
	
	@RequestMapping(value = "/category/{id}")
	public ModelAndView Product(@PathVariable String id) {
		_mvShare.setViewName("user/shop/category");
		int totalData = categoryService.GetAllProductsByID(Integer.parseInt(id)).size();
		PaginationDTO paginateInfo = paginateService.GetInfoPagination(totalData, totalProductsPage, 1);
		_mvShare.addObject("idCategory", id);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("productsPaginate", categoryService.GetDataProductsPaginate(Integer.parseInt(id), paginateInfo.getStart(), totalProductsPage));
		return _mvShare;
	}
	@RequestMapping(value = "/category/{id}/{currentPage}")
	public ModelAndView Product(@PathVariable String id, @PathVariable String currentPage) {
		_mvShare.setViewName("user/shop/category");
		int totalData = categoryService.GetAllProductsByID(Integer.parseInt(id)).size();
		PaginationDTO paginateInfo = paginateService.GetInfoPagination(totalData, totalProductsPage, Integer.parseInt(currentPage));
		_mvShare.addObject("idCategory", id);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("productsPaginate", categoryService.GetDataProductsPaginate(Integer.parseInt(id) ,paginateInfo.getStart(), totalProductsPage));
		return _mvShare;
	}

}