package Shopping.DAO;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Shopping.DTO.CartDTO;
import Shopping.DTO.ProductsDTO;
@Repository
public class CartDAO extends BaseDAO{
	@Autowired
	ProductDAO productsDao = new ProductDAO();
	
	public HashMap<Long, CartDTO> AddCart(long id, HashMap<Long, CartDTO> cart) {
		CartDTO itemCart = new CartDTO();
		ProductsDTO product = productsDao.FindProductByID(id);
		if(product != null) {
			itemCart.setProductsDTO(product);
			itemCart.setQuantity(1);
			itemCart.setTotalPrice(product.getPrice());
		}
		cart.put(id, itemCart);
		return cart;
	}
	public HashMap<Long, CartDTO> EditCart(long id, int quanty, HashMap<Long, CartDTO> cart) {
		if(cart == null) {
			return cart;
		}
		CartDTO itemCart = new CartDTO();
		if(cart.containsKey(id)) {
			itemCart = cart.get(id);
			itemCart.setQuantity(quanty);
			double totalPrice = quanty * itemCart.getProductsDTO().getPrice();
			itemCart.setTotalPrice(totalPrice);
		}
		cart.put(id, itemCart);
		return cart;
	}
	
	public HashMap<Long, CartDTO> DeleteCart(long id, HashMap<Long, CartDTO> cart) {
		if(cart == null) {
			return cart;
		}
		if(cart.containsKey(id)) {
			cart.remove(id);
		}
		return cart;
	}
	
	public int TotalQuanty(HashMap<Long, CartDTO> cart) {
		int totalQuanty = 0;
		for(Map.Entry<Long, CartDTO> itemCart : cart.entrySet()) {
			totalQuanty += itemCart.getValue().getQuantity();
		}
		return totalQuanty;
	}
	
	public double TotalPrice(HashMap<Long, CartDTO> cart) {
		double totalPrice = 0;
		for(Map.Entry<Long, CartDTO> itemCart : cart.entrySet()) {
			totalPrice += itemCart.getValue().getTotalPrice();
		}
		return totalPrice;
	}
	
}

