package Shopping.Entity;

public class Brand {
	private int brandid;
	private String brandname;
	private String brandimg;
	private String branddesc;
	private int countProbyBra;

	public int getBrandid() {
		return brandid;
	}
	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public String getBrandimg() {
		return brandimg;
	}
	public void setBrandimg(String brandimg) {
		this.brandimg = brandimg;
	}
	public String getBranddesc() {
		return branddesc;
	}
	public void setBranddesc(String branddesc) {
		this.branddesc = branddesc;
	}
	
	public int getCountProbyBra() {
		return countProbyBra;
	}
	public void setCountProbyBra(int countProbyBra) {
		this.countProbyBra = countProbyBra;
	}
	public Brand() {
		super();
	}
	
	
}
