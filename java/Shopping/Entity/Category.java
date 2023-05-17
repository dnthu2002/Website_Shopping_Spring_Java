package Shopping.Entity;

public class Category {
	private int idcat;
	private String catname;
	private String catimg;
	private String catdesc;
	private int countProbyCat;

	
	public int getIdcat() {
		return idcat;
	}
	public void setIdcat(int idcat) {
		this.idcat = idcat;
	}
	public String getCatname() {
		return catname;
	}
	public void setCatname(String catname) {
		this.catname = catname;
	}
	public String getCatimg() {
		return catimg;
	}
	public void setCatimg(String catimg) {
		this.catimg = catimg;
	}
	public String getCatdesc() {
		return catdesc;
	}
	public void setCatdesc(String catdesc) {
		this.catdesc = catdesc;
	}
	
	public int getCountProbyCat() {
		return countProbyCat;
	}
	public void setCountProbyCat(int countProbyCat) {
		this.countProbyCat = countProbyCat;
	}
	public Category() {
		super();
	}
	

}
