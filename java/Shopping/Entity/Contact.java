package Shopping.Entity;

public class Contact {
	private int idcontact;
	private String Fullname;
	private String email;
	private String title;
	private String content;
	private String contactdate;
	private String status;
	
	
	public Contact() {
		super();
	}
	public Contact(int idcontact, String fullname, String email, String title, String content, String contactdate,
			String status) {
		super();
		this.idcontact = idcontact;
		Fullname = fullname;
		this.email = email;
		this.title = title;
		this.content = content;
		this.contactdate = contactdate;
		this.status = status;
	}
	public int getIdcontact() {
		return idcontact;
	}
	public void setIdcontact(int idcontact) {
		this.idcontact = idcontact;
	}
	public String getFullname() {
		return Fullname;
	}
	public void setFullname(String fullname) {
		Fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContactdate() {
		return contactdate;
	}
	public void setContactdate(String contactdate) {
		this.contactdate = contactdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
