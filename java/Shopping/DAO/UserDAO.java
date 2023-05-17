package Shopping.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import Shopping.Entity.MapperOrderProduct;
import Shopping.Entity.MapperUser;
import Shopping.Entity.OrderProduct;
import Shopping.Entity.User;
@Repository
public class UserDAO extends BaseDAO{
	public int AddAccount(User user) {
		String sql = "INSERT INTO `account`(`ID`, `Fullname`, `Gender`, `Brithday`, `Address`, `Email`, `Phone`, `Username`, `Password`, `maquyen`, `Image`) "
				+ "VALUES ('"+user.getId()+"','"+user.getFullname()+"','"+user.getGender()+"','"+user.getBrithday()+"','"+user.getAddress()+"','"+user.getEmail()+"','"+user.getPhone()+"','"+user.getUsername()+"','"+user.getPassword()+"',"+"'3','"+user.getImage()+"')";
		int insert = _jdbcTemplate.update(sql);
		return insert;
	}
	
	public User GetUserbyAccount(User user) {
		String sql = "SELECT * FROM `account` WHERE Username = '"+user.getUsername()+"'";
		User result = _jdbcTemplate.queryForObject(sql, new MapperUser());
		return result;
	}

	public int UpdateAccount(User user,int id) {
		String sql = "UPDATE `account` SET `Fullname`='"+user.getFullname()+"',`Gender`='"+user.getGender()+"',`Brithday`='"+user.getBrithday()+"',`Address`='"+user.getAddress()+"',`Email`='"+user.getEmail()+"',`Phone`='"+user.getPhone()+"',`Username`='"+user.getUsername()+"',`Password`='"+user.getPassword()+"',`Image`='"+user.getImage()+"' WHERE `ID` = '"+id+"'";
		int update = _jdbcTemplate.update(sql);
		return update;
	}

	public List<User> getDataCustomer() {
		List<User> list = new ArrayList<User>();
		String sql = "SELECT * FROM `account` WHERE maquyen = '3'";
		list = _jdbcTemplate.query(sql, new MapperUser());
		return list;
	}
}
