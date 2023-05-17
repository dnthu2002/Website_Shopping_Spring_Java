package Shopping.Controller.User;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.soap.SoapFaultException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Shopping.DTO.CartDTO;
import Shopping.Service.User.CartServiceImpl;
import Shopping.Service.User.OrderProductServiceImpl;
import Shopping.Entity.OrderProduct;
import Shopping.Entity.User;

@Controller
public class CartController extends BaseController{
	
	@Autowired
	private CartServiceImpl cartService = new CartServiceImpl();
	
	@Autowired
	private OrderProductServiceImpl orderProductServiceImpl = new OrderProductServiceImpl();
	
	@RequestMapping(value = "/cart")
	public ModelAndView Index() {
		_mvShare.addObject("products", homeServiceImpl.getDataProducts());
		_mvShare.setViewName("user/cart/cart");
		return _mvShare;
	}
	
	@RequestMapping(value = "AddCart/{id}")
	public String AddCart(HttpServletRequest request ,HttpSession session, @PathVariable long id) {
		HashMap<Long, CartDTO> cart = (HashMap<Long, CartDTO>)session.getAttribute("Cart");
		if(cart == null) {
			cart = new HashMap<Long, CartDTO>();
		}
		cart = cartService.AddCart(id, cart);
		session.setAttribute("Cart", cart);
		session.setAttribute("TotalQuantyCart", cartService.TotalQuanty(cart));
		session.setAttribute("TotalPriceCart", cartService.TotalPrice(cart));
		return "redirect:"+request.getHeader("Referer");
	}
	
	@RequestMapping(value = "EditCart/{id}/{quantity}")
	public String EditCart(HttpServletRequest request ,HttpSession session, @PathVariable long id, @PathVariable int quantity) {
		HashMap<Long, CartDTO> cart = (HashMap<Long, CartDTO>)session.getAttribute("Cart");
		if(cart == null) {
			cart = new HashMap<Long, CartDTO>();
		}
		cart = cartService.EditCart(id, quantity, cart);
		session.setAttribute("Cart", cart);
		session.setAttribute("TotalQuantyCart", cartService.TotalQuanty(cart));
		session.setAttribute("TotalPriceCart", cartService.TotalPrice(cart));
		return "redirect:"+request.getHeader("Referer");
	}
	
	
	@RequestMapping(value = "DeleteCart/{id}")
	public String DeleteCart(HttpServletRequest request ,HttpSession session, @PathVariable long id) {
		HashMap<Long, CartDTO> cart = (HashMap<Long, CartDTO>)session.getAttribute("Cart");
		if(cart == null) {
			cart = new HashMap<Long, CartDTO>();
		}
		cart = cartService.DeleteCart(id, cart);
		session.setAttribute("Cart", cart);
		session.setAttribute("TotalQuantyCart", cartService.TotalQuanty(cart));
		session.setAttribute("TotalPriceCart", cartService.TotalPrice(cart));
		return "redirect:"+request.getHeader("Referer");
	}
	
	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	public ModelAndView CheckOut(HttpServletRequest request,HttpSession session) {
		String ErrMsg = "";
		_mvShare.setViewName("user/checkout/checkout");
		OrderProduct orderProduct = new OrderProduct();
		User user = (User) session.getAttribute("LoginInfo");
		if(user != null) {
			orderProduct.setAddress(user.getAddress());
			orderProduct.setFullname(user.getFullname());
			orderProduct.setPhone(user.getPhone());
			_mvShare.addObject("orderproduct", orderProduct);
		}else {
			ErrMsg = "Đăng nhập để thanh toán";
			_mvShare.addObject("errmsg", ErrMsg);
			_mvShare.setViewName("redirect:Login");
		}				
		return _mvShare;
	}
	
	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public String OrderProduct(HttpServletRequest request,HttpSession session, @ModelAttribute("orderproduct") OrderProduct orderProduct ) {
		orderProduct.setTotalprice((double) session.getAttribute("TotalPriceCart"));
		
		if(orderProductServiceImpl.AddOrderProduct(orderProduct) > 0) {
			HashMap<Long, CartDTO> cart = (HashMap<Long, CartDTO>) session.getAttribute("Cart");
			orderProductServiceImpl.AddOrderProductDetail(cart);
		}
		session.removeAttribute("Cart");
		return "redirect:cart";
	}
}
