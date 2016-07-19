package bean;

import java.sql.Timestamp;


public class Party {
	private int id;
	private String name;
	private String address;
	private int status;	// 默认0：进行中  1：已取消	2：已结束
	private Timestamp createTime;
	private String startTime;
	private int leaderId;
	private String posLat;
	private String posLon;
	private String note;
	
	public Party(){
		super();
	}
	
	public Party(int id){
		super();
		this.id = id;
	}
	
	public Party(int id,String name,String address){
		super();
		this.id=id;
		this.name=name;
		this.address=address;
	}
	
	public Party(String name,String address,String starttime,int leaderid,String poslat,String poslon,String note){
		super();
		this.name=name;
		this.address = address;
		this.startTime = starttime;
		this.leaderId=leaderid;
		this.posLat=poslat;
		this.posLon=poslon;
		this.note=note;
	}
	
	public Party(String name,String address,String starttime,int leaderid,String poslat,String poslon){
		super();
		this.name=name;
		this.address = address;
		this.startTime = starttime;
		this.leaderId=leaderid;
		this.posLat=poslat;
		this.posLon=poslon;
	}
	
	public Party(int id,String name,int status,String address,Timestamp createtime,String starttime,int leaderid,String poslat,String poslon,String note){
		super();
		this.id=id;
		this.name=name;
		this.status=status;
		this.address = address;
		this.createTime = createtime;
		this.startTime = starttime;
		this.leaderId=leaderid;
		this.posLat=poslat;
		this.posLon=poslon;
		this.note=note;
	}
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public int getStatus(){
		return status;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public int getLeaderId(){
		return leaderId;
	}
	public void setLeaderId(int leaderId){
		this.leaderId = leaderId;
	}
	public String getPosLat(){
		return posLat;
	}
	public void setPosLat(String posLat){
		this.posLat = posLat;
	}
	public String getPosLon(){
		return posLon;
	}
	public void setPosLon(String posLon){
		this.posLon = posLon;
	}
	public String getStartTime(){
		return startTime;
	}
	public void setStartTime(String startTime){
		this.startTime = startTime;
	}
	public Timestamp getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Timestamp timestamp){
		this.createTime = timestamp;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public static class Member{
		private int userId;
		private String userName;
		private int isAttend;
		public Member() {
			super();
		}
		public int getUserId(){
			return userId;
		}
		public void setUserId(int userId){
			this.userId = userId;
		}
		public String getUserName(){
			return userName;
		}
		public void setUserName(String userName){
			this.userName = userName;
		}
		public int getIsAttend(){
			return isAttend;
		}
		public void setIsAttend(int isAttend){
			this.isAttend=isAttend;
		}
	}
}

