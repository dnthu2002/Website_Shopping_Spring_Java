package Shopping.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import Shopping.Entity.Brand;
import Shopping.Entity.Contact;
import Shopping.Entity.MapperBrand;
import Shopping.Entity.MapperContact;
@Repository
public class ContactDAO extends BaseDAO{
	public List<Contact> getDataAllContact() {
		List<Contact> list = new ArrayList<Contact>();
		String sql = "SELECT * FROM `contact` WHERE 1";
		list = _jdbcTemplate.query(sql, new MapperContact());
		return list;
	}

}
