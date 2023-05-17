package Shopping.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import Shopping.Entity.Banner;
import Shopping.Entity.MapperBanner;


@Repository
public class BannerDAO extends BaseDAO{
	
	public List<Banner> getDataBanners(){
		List<Banner> list = new ArrayList<Banner>();
		String sql = "SELECT * FROM banner";
		list = _jdbcTemplate.query(sql, new MapperBanner());
		return list;
	} 
}