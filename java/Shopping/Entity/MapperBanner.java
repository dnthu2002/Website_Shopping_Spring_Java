package Shopping.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperBanner implements RowMapper<Banner> {

	public Banner mapRow(ResultSet rs, int rowNum) throws SQLException {
		Banner banner= new Banner();
		banner.setId(rs.getInt("bannerid"));
		banner.setName_pro(rs.getString("name_pro"));
		banner.setImg_pro(rs.getString("img_pro"));
		banner.setContent(rs.getString("content"));
		return banner;
	}
	
}