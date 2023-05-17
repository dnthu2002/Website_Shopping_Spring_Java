package Shopping.DTO;

public class CartDTO {
	private int quantity;
	private double totalPrice;
	private ProductsDTO productsDTO;
	
	public CartDTO() {
	}
	
	public CartDTO(int quantity, double totalPrice, ProductsDTO productsDTO) {
		super();
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.productsDTO = productsDTO;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public ProductsDTO getProductsDTO() {
		return productsDTO;
	}
	public void setProductsDTO(ProductsDTO productsDTO) {
		this.productsDTO = productsDTO;
	}
	
	
}
