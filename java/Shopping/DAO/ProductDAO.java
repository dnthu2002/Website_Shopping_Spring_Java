package Shopping.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import Shopping.DTO.ProductsDTO;
import Shopping.DTO.ProductsDTOMapper;
@Repository
public class ProductDAO extends BaseDAO{

	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT");
		sql.append(" product.*");
		sql.append(", category.CategoryName,brand.BrandName,productstatus.ProductStatusName");
		sql.append(" FROM");
		sql.append(" product");
		sql.append(" INNER JOIN category ON product.CategoryID=category.CategoryID ");
		sql.append(" INNER JOIN brand ON product.BrandID=brand.BrandID");
		sql.append(" INNER JOIN productstatus ON product.ProductStatusID = productstatus.ProductStatusID");
		return sql;
	}
	
	private String SqlProducts() {
		StringBuffer sql = SqlString();
		sql.append(" order by UpdateDate ASC");
		return sql.toString();
	}
	
	private String SqlProductsNew() {
		StringBuffer sql = SqlString();
		sql.append(" WHERE product.ProductStatusID = 1");
		sql.append(" order by UpdateDate ASC LIMIT 1,6");
		return sql.toString();
	}
	
	private String SqlProductsHightlight() {
		StringBuffer sql = SqlString();
		sql.append(" WHERE product.ProductStatusID = 2");
		sql.append(" order by UpdateDate ASC LIMIT 1,3");
		return sql.toString();
	}
	
	private String SqlProductsSeller() {
		StringBuffer sql = SqlString();
		sql.append(" WHERE product.ProductStatusID = 3");
		sql.append(" order by UpdateDate ASC LIMIT 1,3");
		return sql.toString();
	}
	
	private StringBuffer SqlProductsByID(int id) {
		StringBuffer sql = SqlString();
		sql.append(" WHERE 1 = 1");
		sql.append(" AND product.CategoryID = " + id + " ");
		return sql;
	}
	
	private String SqlProductsPaginate(int id, int start, int totalPage) {
		StringBuffer sql = SqlProductsByID(id);
		sql.append(" LIMIT " + start + ", "+ totalPage);
		return sql.toString();
	}
	
	public List<ProductsDTO> getDataProducts(){
		String sql = SqlProducts();
		List<ProductsDTO> list = _jdbcTemplate.query(sql, new ProductsDTOMapper());
//		String sql = "SELECT product.*,category.CategoryName,brand.BrandName,productstatus.ProductStatusName FROM product INNER JOIN category ON product.CategoryID=category.CategoryID INNER JOIN brand ON product.BrandID=brand.BrandID INNER JOIN productstatus ON product.ProductStatusID = productstatus.ProductStatusID order by UpdateDate ASC LIMIT 1,12";
//		list = _jdbcTemplate.query(sql, new MapperProduct());
		return list;
	} 
	
	public List<ProductsDTO> getProductbynew(){
		String sql = SqlProductsNew();
		List<ProductsDTO> list = _jdbcTemplate.query(sql, new ProductsDTOMapper());
		return list;
	} 
	public List<ProductsDTO> getProductbyhighlight(){
		String sql = SqlProductsHightlight();
		List<ProductsDTO> list = _jdbcTemplate.query(sql, new ProductsDTOMapper());
		return list;
	} 
	public List<ProductsDTO> getProductbyseller(){
		String sql = SqlProductsSeller();
		List<ProductsDTO> list = _jdbcTemplate.query(sql, new ProductsDTOMapper());
		return list;
	} 
	
	public List<ProductsDTO> GetAllProductsByID(int id) {
		String sql = SqlProductsByID(id).toString();
		List<ProductsDTO> listProducts = _jdbcTemplate.query(sql, new ProductsDTOMapper());
		return listProducts;
	}
	
	public List<ProductsDTO> GetDataProductsPaginate(int id, int start, int totalPage) {
		StringBuffer sqlGetDataByID = SqlProductsByID(id);
		List<ProductsDTO> listProductsByID = _jdbcTemplate.query(sqlGetDataByID.toString(), new ProductsDTOMapper());
		List<ProductsDTO> listProducts = new ArrayList<ProductsDTO>();
		if(listProductsByID.size() > 0) {
			String sql = SqlProductsPaginate(id, start, totalPage);
			listProducts = _jdbcTemplate.query(sql, new ProductsDTOMapper());
		}
		return listProducts;
	}

	private String SqlProductByID(long id) {
		StringBuffer sql = SqlString();
		sql.append(" WHERE 1 = 1 ");
		sql.append(" AND product.ProductID  = " + id + " ");
		sql.append(" LIMIT 1 ");
		return sql.toString();
	}
	
	public List<ProductsDTO> GetProductByID(long id) {
		String sql = SqlProductByID(id);
		List<ProductsDTO> listProduct = _jdbcTemplate.query(sql, new ProductsDTOMapper());
		return listProduct;
	}
	
	public ProductsDTO FindProductByID(long id) {
		String sql = SqlProductByID(id);
		ProductsDTO product = _jdbcTemplate.queryForObject(sql, new ProductsDTOMapper());
		return product;
	}
	//all product
	public List<ProductsDTO> GetAllProducts() {
		String sql = SqlString().toString();
		List<ProductsDTO> listProducts = _jdbcTemplate.query(sql, new ProductsDTOMapper());
		return listProducts;
	}
	
	private String SqlAllProductsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append(" LIMIT " + start + ", "+ totalPage);
		return sql.toString();
	}
	
	public List<ProductsDTO> GetAllProductsPaginate(int start, int totalPage) {		
		List<ProductsDTO> listProductsByID = GetAllProducts();
		List<ProductsDTO> listProducts = new ArrayList<ProductsDTO>();
		if(listProductsByID.size() > 0) {
			String sql = SqlAllProductsPaginate(start, totalPage);
			listProducts = _jdbcTemplate.query(sql, new ProductsDTOMapper());
		}
		return listProducts;
	}
// brand
	private StringBuffer SqlProductsBybrandID(int id) {
		StringBuffer sql = SqlString();
		sql.append(" WHERE 1 = 1");
		sql.append(" AND product.BrandID = " + id + " ");
		return sql;
	} 
	private String SqlProductsPaginateBrand(int id, int start, int totalPage) {
		StringBuffer sql = SqlProductsBybrandID(id);
		sql.append(" LIMIT " + start + ", "+ totalPage);
		return sql.toString();
	} 
	public List<ProductsDTO> GetAllProductsByBrandID(int id) {
		String sql = SqlProductsBybrandID(id).toString();
		List<ProductsDTO> listProducts = _jdbcTemplate.query(sql, new ProductsDTOMapper());
		return listProducts;
	}
	
	public List<ProductsDTO> GetDataProductsPaginatebyBrand(int id, int start, int totalPage) {
		StringBuffer sqlGetDataByBrandID = SqlProductsBybrandID(id);
		List<ProductsDTO> listProductsByID = _jdbcTemplate.query(sqlGetDataByBrandID.toString(), new ProductsDTOMapper());
		List<ProductsDTO> listProducts = new ArrayList<ProductsDTO>();
		if(listProductsByID.size() > 0) {
			String sql = SqlProductsPaginateBrand(id, start, totalPage);
			listProducts = _jdbcTemplate.query(sql, new ProductsDTOMapper());
		}
		return listProducts;
	}
}
