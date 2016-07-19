package bean;

public class User {
	private int id;
	private String name;
	private String pwd;
	private String avatar;
	private String gender;
	private String sign;
	private String email;
	private String home;
	private String birthday;


	public User() {
		super();
	}

	public User(int id){
		super();
		this.setId(id);
	}
	public User(String name) {
		super();
		this.setName(name);
	}

	public User(String name, String pwd) {
		super();
		this.setName(name);
		this.setPwd(pwd);
	}

	public User(int id,String gender, String sign,String email,String home,String birthday) {
		super();
		this.setId(id);
		this.setGender(gender);
		this.setSign(sign);
		this.setEmail(email);
		this.setHome(home);
		this.setBirthday(birthday);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
