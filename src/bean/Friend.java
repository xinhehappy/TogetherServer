package bean;

public class Friend {
	private int id;
	private int myid;
	private int fid;
	private String neckname;
	private String avater;
	private String sign;
	private String addtime;
	private int status;

	public Friend() {
		super();
	}

	public Friend(int myid, int fid) {
		super();
		this.myid = myid;
		this.fid = fid;
	}

	public Friend(int fid, String neckname, String avater) {
		super();
		this.fid = fid;
		this.neckname = neckname;
		this.avater = avater;
	}

	public Friend(int myid, int fid, String neckname) {
		// TODO Auto-generated constructor stub
		super();
		this.myid = myid;
		this.fid = fid;
		this.neckname = neckname;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNeckname() {
		return neckname;
	}

	public void setNeckname(String neckname) {
		this.neckname = neckname;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public int getMyid() {
		return myid;
	}

	public void setMyid(int myid) {
		this.myid = myid;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getAvater() {
		return avater;
	}

	public void setAvater(String avater) {
		this.avater = avater;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
