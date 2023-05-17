package Shopping.Controller.User;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import Shopping.Entity.User;
import Shopping.Service.User.AccountServiceImpl;
import Shopping.Service.User.OrderProductServiceImpl;

@Controller
public class UserController extends BaseController {
	@Autowired
	AccountServiceImpl accountServiceImpl = new AccountServiceImpl();
	@Autowired
	OrderProductServiceImpl orderProductServiceImpl = new OrderProductServiceImpl();
	private static final String UPLOAD_DIRECTORY = "assets/admin/upload";

	@RequestMapping(value = { "/Login" }, method = RequestMethod.GET)
	public ModelAndView Register() {
		_mvShare.setViewName("user/login/login");
		_mvShare.addObject("user", new User());
		return _mvShare;
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public ModelAndView CreateAcc(@ModelAttribute("user") User user) {
		String ErrMsg = "";

		if (user.getPassword().equals(user.getConfimpass())) {
			int count = accountServiceImpl.AddAccount(user);
			if (count > 0) {
				_mvShare.addObject("status", "Đăng ký thành công");
			} else {
				_mvShare.addObject("status", "Đăng ký không thành công");
			}
		} else {
			ErrMsg = "Mật khẩu không khớp";
		}
		_mvShare.addObject("errmsg", ErrMsg);
		_mvShare.setViewName("user/login/login");
		return _mvShare;
	}

	@RequestMapping(value = "/dang-nhap", method = RequestMethod.POST)
	public ModelAndView Login(HttpSession session, @ModelAttribute("user") User user) {
		user = accountServiceImpl.CheckAccount(user);
		if (user != null) {
			_mvShare.setViewName("redirect:home");
			session.setAttribute("LoginInfo", user);
		} else {
			_mvShare.addObject("statuslogin", "Đăng nhập không thành công");
		}
		return _mvShare;
	}

	@RequestMapping(value = "/dang-xuat", method = RequestMethod.GET)
	public String Logout(HttpSession session, HttpServletRequest request) {
		session.removeAttribute("LoginInfo");
		return "redirect:home";
	}

	@RequestMapping(value = "/myaccount", method = RequestMethod.GET)
	public ModelAndView MyAccount(HttpServletRequest request, HttpSession session) {
		_mvShare.setViewName("user/login/myaccount");
		User add = new User();
		User user = (User) session.getAttribute("LoginInfo");
		int id = user.getId();
		add.setAddress(user.getAddress());
		add.setFullname(user.getFullname());
		add.setPhone(user.getPhone());
		add.setBrithday(user.getBrithday());
		add.setEmail(user.getEmail());
		add.setUsername(user.getUsername());
		add.setPassword(user.getPassword());
		_mvShare.addObject("user", add);
		_mvShare.addObject("showOrder", orderProductServiceImpl.getDataOrderByUserId(id));
		return _mvShare;
	}

	@RequestMapping(value = "myaccount", method = RequestMethod.POST)
	public String EditAccount(HttpServletRequest request, HttpSession session,
			@ModelAttribute("myaccount") User user) {
		User useradd = (User) session.getAttribute("LoginInfo");
		int id = useradd.getId();
		accountServiceImpl.UpdateAccount(user, id);
		return "redirect:myaccount";
	}
	
}
