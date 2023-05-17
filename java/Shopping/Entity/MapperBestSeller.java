package Shopping.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperBestSeller implements RowMapper<OrderDetail>{

	public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		OrderDetail bestseller = new OrderDetail();
		bestseller.setProid(rs.getInt("ProductID"));
		bestseller.setPicture(rs.getString("Picture"));
		bestseller.setProname(rs.getString("ProductName"));
		bestseller.setPrice(rs.getDouble("Price"));
		bestseller.setQuantity(rs.getInt("Quantity"));
		return bestseller;
	}

}
