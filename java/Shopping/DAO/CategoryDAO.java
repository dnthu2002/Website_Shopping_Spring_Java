package Shopping.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import Shopping.Entity.Category;
import Shopping.Entity.MapperCategory;
import Shopping.Entity.User;


@Repository
public class CategoryDAO extends BaseDAO{
	
	public List<Category> getDataCategorys(){
		List<Category> list = new ArrayList<Category>();
		String sql = "SELECT c.CategoryID, c.CategoryName, c.Picture_cat, c.Description , count( p.CategoryID) AS product_count FROM category c LEFT JOIN product p ON c.CategoryID = p.CategoryID GROUP BY c.CategoryID, c.CategoryName";
		list = _jdbcTemplate.query(sql, new MapperCategory());
		return list;
	}


	public int AddCategory(Category category) {
		String sql = "INSERT INTO `category`(`CategoryID`, `CategoryName`, `Picture_cat`, `Description`)"
				+ "VALUES ('"+category.getIdcat()+"','"+category.getCatname()+"','"+category.getCatimg()+"','"+category.getCatdesc()+"')";
		int insert = _jdbcTemplate.update(sql);
		return insert;
	}


	public int DeleteCategory(int id) {
		String sql = "DELETE FROM `category` WHERE CategoryID ="+id+"";
		return _jdbcTemplate.update(sql);
	}


	public int UpdateCategory(Category category) {
		String sql = "UPDATE `category` SET `CategoryName`='"+category.getCatname()+"',`Picture_cat`='"+category.getCatimg()+"',`Description`='"+category.getCatdesc()+"' WHERE CategoryID ="+category.getIdcat()+"";
		return _jdbcTemplate.update(sql);
	}


	public Category getCatByID(Category category, int id) {
		String sql = "SELECT c.CategoryID, c.CategoryName, c.Picture_cat, c.Description , count( p.CategoryID) AS product_count FROM category c LEFT JOIN product p ON c.CategoryID = p.CategoryID WHERE c.CategoryID ="+id+"";
		Category result = _jdbcTemplate.queryForObject(sql, new MapperCategory());
		return result;
	}


	public Category getCatByID(int id) {
		String sql = "SELECT c.CategoryID, c.CategoryName, c.Picture_cat, c.Description , count( p.CategoryID) AS product_count FROM category c LEFT JOIN product p ON c.CategoryID = p.CategoryID WHERE c.CategoryID ="+id+"";
		return _jdbcTemplate.queryForObject(sql, new MapperCategory());
	}


}
