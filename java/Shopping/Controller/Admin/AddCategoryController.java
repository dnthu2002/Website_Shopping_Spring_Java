package Shopping.Controller.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Shopping.Controller.User.BaseController;
import Shopping.Entity.Category;
import Shopping.Entity.User;
import Shopping.Service.User.CategoryServiceImpl;

@Controller
public class AddCategoryController extends BaseController {

	@Autowired
	CategoryServiceImpl categoryServiceImpl;

	@RequestMapping(value = { "/admin/category" })
	public ModelAndView Index() {
//			_mvShare.addObject("banner",homeServiceImpl.getDataBanners());
//			_mvShare.addObject("product", homeServiceImpl.getDataProducts());
//			_mvShare.addObject("productnew", homeServiceImpl.getProductbynew());
//			_mvShare.addObject("producthighlight", homeServiceImpl.getProductbyhighlight());
//			_mvShare.addObject("productseller", homeServiceImpl.getProductbyseller());
		_mvShare.addObject("activeAdminCategory", "active");
		_mvShare.setViewName("admin/category/addcategory");
		return _mvShare;
	}

	@RequestMapping(value = "/admin/category", method = RequestMethod.POST)
	public ModelAndView AddCategory(@ModelAttribute("category") Category category) {
		int count = categoryServiceImpl.AddCategory(category);
		if (count > 0) {
			_mvShare.addObject("status", "Thêm thành công");
		} else {
			_mvShare.addObject("status", "Thêm không thành công");
		}
		_mvShare.setViewName("admin/category/addcategory");
		return _mvShare;
	}

//	@RequestMapping(value = "/admin/updatecat/{id}")
//	public ModelAndView UpdateCat(@PathVariable int id ,@ModelAttribute("updatecat") Category category) {
//		category=categoryServiceImpl.getCatByID(category,id);
//		_mvShare.addObject("catbyid",category);
//		_mvShare.setViewName("admin/category/editcategory");
////		categoryServiceImpl.UpdateCategory(id);
//		return _mvShare;
//	}
	  @RequestMapping(value="/admin/updatecat/{id}")    
	    public String edit(@PathVariable int id, Model m){    
	        Category category=categoryServiceImpl.getCatByID(id);    
	        m.addAttribute("command",category);  
	        return "/admin/category/editcategory";    
	    }
	  @RequestMapping(value="/admin/updatecat",method = RequestMethod.POST)    
	    public String editsave(@ModelAttribute("category") Category category){    
	        categoryServiceImpl.UpdateCategory(category);    
	        return "redirect:/admin/category";    
	    }    
	@RequestMapping(value = "/admin/category/delete/{id}", method = RequestMethod.GET)
	public String DeleteCat(@PathVariable int id) {
		categoryServiceImpl.DeleteCategory(id);
		return "redirect:/admin/category";
	}

}
