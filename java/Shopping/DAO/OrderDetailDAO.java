package Shopping.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import Shopping.Entity.OrderDetail;
import Shopping.Entity.MapperBestSeller;
import Shopping.Entity.MapperCount_Sold;

@Repository
public class OrderDetailDAO extends BaseDAO {

	public List<OrderDetail> getDataCountSolds() {
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		String sql = "SELECT orderdetail.ProductID,SUM(orderdetail.QuantityOrdered) AS Quantity FROM orderdetail LEFT JOIN product ON orderdetail.ProductID = product.ProductID GROUP BY orderdetail.ProductID ORDER BY orderdetail.ProductID DESC";
		list = _jdbcTemplate.query(sql, new MapperCount_Sold());
		return list;
	}
	public List<OrderDetail> getDataBestSellers() {
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		String sql = "SELECT orderdetail.ProductID,product.Picture,product.ProductName,product.Price,SUM(orderdetail.QuantityOrdered) AS Quantity FROM orderdetail LEFT JOIN product ON orderdetail.ProductID = product.ProductID GROUP BY orderdetail.ProductID ORDER BY Quantity DESC LIMIT 0,5";
		list = _jdbcTemplate.query(sql, new MapperBestSeller());
		return list;
	}

}
