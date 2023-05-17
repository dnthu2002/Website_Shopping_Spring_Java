package Shopping.DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class ProductsDTOMapper implements RowMapper<ProductsDTO>{
	public ProductsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		ProductsDTO product = new ProductsDTO();
		product.setProid(rs.getInt("ProductID"));
		product.setProname(rs.getString("ProductName"));
		product.setCatid(rs.getInt("CategoryID"));
		product.setBrandid(rs.getInt("BrandID"));
		product.setProdesc(rs.getString("ProductDesc"));
		product.setPrice(rs.getDouble("Price"));
		product.setQuantity(rs.getInt("Quantity"));
		product.setProstatusid(rs.getInt("ProductStatusID"));
		product.setProimg1(rs.getString("Picture"));
		product.setProimg2(rs.getString("Picture2"));
		product.setUpdatedate(rs.getString("UpdateDate"));
		product.setQuantityrating(rs.getInt("QuantityRating"));
		product.setCatname(rs.getString("CategoryName"));
		product.setBrandname(rs.getString("BrandName"));
		product.setProstatusname(rs.getString("ProductStatusName"));
		return product;
	}
}
