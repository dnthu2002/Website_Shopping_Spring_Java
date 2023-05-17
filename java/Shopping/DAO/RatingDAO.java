package Shopping.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;


import Shopping.Entity.MapperRating;
import Shopping.Entity.Rating;

@Repository
public class RatingDAO extends BaseDAO {

	public List<Rating> getDataRatings() {
		List<Rating> list = new ArrayList<Rating>();
		String sql = "SELECT rating.ProductID,SUM(rating.Rating_Star) AS Rating,count( product.ProductID) AS Rating_count \r\n"
				+ "        FROM rating LEFT JOIN product ON rating.ProductID = product.ProductID GROUP BY rating.ProductID";
		list = _jdbcTemplate.query(sql, new MapperRating());
		return list;
	}

}