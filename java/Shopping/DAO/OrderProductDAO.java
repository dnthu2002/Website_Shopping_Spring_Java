package Shopping.DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import Shopping.Entity.Brand;
import Shopping.Entity.MapperBrand;
import Shopping.Entity.MapperOrderProduct;
import Shopping.Entity.OrderDetail;
import Shopping.Entity.OrderProduct;

@Repository
public class OrderProductDAO extends BaseDAO {
	public int AddOrderProduct(OrderProduct orderProduct) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    Date date = new Date();
	    String Orderdate = formatter.format(date);
		String sql = "INSERT INTO `orderproduct`(`OrderID`, `OrderDate`, `Fullname`, `Phonenumber`, `Address`, `Typeaddress`, `Shippingtype`, `Totalprice`, `AccountID`, `Status`) "
			+ "VALUES ('"+orderProduct.getOrderid()+"','"+Orderdate+"','"+orderProduct.getFullname()+"','"+orderProduct.getPhone()+"','"+orderProduct.getAddress()+"','"+orderProduct.getTypeaddress()+"','"+orderProduct.getShippingtype()+"','"+orderProduct.getTotalprice()+"','"+orderProduct.getAccountid()+"','1')";
		int insert = _jdbcTemplate.update(sql.toString());
		return insert;
	}
	public int GetIDOrderProduct() {
		String sql = "SELECT MAX(OrderID) FROM orderproduct";
		int id = _jdbcTemplate.queryForObject(sql.toString(), new Object[] {}, int.class);
		return id;
	}
	public int AddOrderProductDetail(OrderDetail orderDetail) {
		String sql = "INSERT INTO `orderdetail`(`OrderID`, `ProductID`, `QuantityOrdered`, `Price`) "
				+ "VALUES ('"+orderDetail.getOrderid()+"','"+orderDetail.getProid()+"','"+orderDetail.getQuantity()+"','"+orderDetail.getPrice()+"')";
		int insert = _jdbcTemplate.update(sql.toString());
		return insert;
	}
	public List<OrderProduct> getDataOrderByUserId(int id){
		List<OrderProduct> list = new ArrayList<OrderProduct>();
		String sql = "SELECT orderproduct.*,product.ProductName,product.Picture FROM orderdetail INNER JOIN orderproduct ON orderdetail.OrderID=orderproduct.OrderID INNER JOIN product ON orderdetail.ProductID=product.ProductID WHERE orderproduct.AccountID ="+id+"";
		list = _jdbcTemplate.query(sql, new MapperOrderProduct());
		return list;
	}
	public List<OrderProduct> getDataOrderNew() {
		List<OrderProduct> list = new ArrayList<OrderProduct>();
		String sql = "SELECT orderproduct.*,product.ProductName,product.Picture FROM orderdetail INNER JOIN orderproduct ON orderdetail.OrderID=orderproduct.OrderID INNER JOIN product ON orderdetail.ProductID=product.ProductID INNER JOIN account ON account.ID=orderproduct.AccountID GROUP BY orderproduct.OrderID DESC LIMIT 0,5";
		list = _jdbcTemplate.query(sql, new MapperOrderProduct());
		return list;
	}
	public List<OrderProduct> getDataAllOrder() {
		List<OrderProduct> list = new ArrayList<OrderProduct>();
		String sql = "SELECT orderproduct.*,product.ProductName,product.Picture FROM orderdetail INNER JOIN orderproduct ON orderdetail.OrderID=orderproduct.OrderID INNER JOIN product ON orderdetail.ProductID=product.ProductID INNER JOIN account ON account.ID=orderproduct.AccountID GROUP BY orderproduct.OrderID DESC";
		list = _jdbcTemplate.query(sql, new MapperOrderProduct());
		return list;
	} 
}
