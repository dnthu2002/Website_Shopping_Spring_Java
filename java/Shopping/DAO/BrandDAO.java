package Shopping.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import Shopping.Entity.Brand;
import Shopping.Entity.MapperBrand;
@Repository
public class BrandDAO  extends BaseDAO{
	public List<Brand> getDataBrands(){
		List<Brand> list = new ArrayList<Brand>();
		String sql = "SELECT b.BrandID, b.BrandName , b.Logo , b.BrandDesc , count( p.BrandID) AS brand_count FROM brand b LEFT JOIN product p ON b.BrandID = p.BrandID GROUP BY b.BrandID, b.BrandName";
		list = _jdbcTemplate.query(sql, new MapperBrand());
		return list;
	} 
}
