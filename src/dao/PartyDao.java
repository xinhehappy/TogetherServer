package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Party;
import bean.Party.Member;

public class PartyDao extends BaseDao {
	public List<Party> getPartyList(int myid) {
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Party> partylist = new ArrayList<Party>();
		String sql = "SELECT party.partyId,party.partyName,party.partyStatus FROM party,p_member WHERE p_member.userId = ? AND party.partyID=p_member.partyID";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, myid);
			rs = ps.executeQuery();
			while (rs.next()) {
				Party party = new Party();
				party.setId(rs.getInt(1));
				party.setName(rs.getString(2));
				party.setStatus(rs.getInt(3));
				partylist.add(party);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return partylist;
	}

	public Party getPartyInfo(int partyid) {
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "SELECT `partyId`,`partyName`, `partyAddress`, `partyStatus`, `createTime`,"
				+ "startTime,leaderId,posLat,posLon,note FROM party WHERE partyId=?";
		Party party = new Party();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, partyid);
			rs = ps.executeQuery();
			if (rs.next()) {
				party.setId(rs.getInt(1));
				party.setName(rs.getString(2));
				party.setAddress(rs.getString(3));
				party.setStatus(rs.getInt(4));
				party.setCreateTime(rs.getTimestamp(5));
				party.setStartTime(rs.getString(6));
				party.setLeaderId(rs.getInt(7));
				party.setPosLat(rs.getString(8));
				party.setPosLon(rs.getString(9));
				party.setNote(rs.getString(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return party;

	}
	
//	public class Member{
//		private int userId;
//		private String userName;
//		private int isAttend;
//		public Member() {
//			super();
//		}
//		public int getUserId(){
//			return userId;
//		}
//		public void setUserId(int userId){
//			this.userId = userId;
//		}
//		public String getUserName(){
//			return userName;
//		}
//		public void setUserName(String userName){
//			this.userName = userName;
//		}
//		public int getIsAttend(){
//			return userId;
//		}
//		public void setIsAttend(int isAttend){
//			this.isAttend = isAttend;
//		}
//	}
	
	public List<Member> getPartyMember(int partyid){
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "SELECT user.userId,user.userName,p_member.isAttend FROM user,p_member WHERE p_member.partyId=? AND p_member.userId=user.userId";
		List<Member> memberlist = new ArrayList<Member>();
		try{
			ps = conn.prepareStatement(sql);
			ps.setInt(1, partyid);
			rs = ps.executeQuery();

			while(rs.next()){
				Member member = new Party.Member();
				member.setUserId(rs.getInt(1));
				member.setUserName(rs.getString(2));
				member.setIsAttend(rs.getInt(3));
				memberlist.add(member);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return memberlist;
	}
	
	public int createParty(Party party){
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		int partyid = 0;
		String sql = "INSERT INTO party(partyName,partyAddress,startTime,leaderId,posLat,posLon,note) VALUES(?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, party.getName());
			ps.setString(2, party.getAddress());
			ps.setString(3, party.getStartTime());
			ps.setInt(4, party.getLeaderId());
			ps.setString(5,party.getPosLat());
			ps.setString(6,party.getPosLon());
			ps.setString(7,party.getNote());
			ps.execute();
			
		    ps = conn.prepareStatement("SELECT LAST_INSERT_ID() ");
		    rs = ps.executeQuery();
		    if(rs.next()){
		    	partyid = rs.getInt(1);
		    }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return partyid;
	}
	
	public boolean quitParty(int userid,int partyid){
		boolean flag = false;
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM p_member WHERE partyId=? AND userId=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, partyid);
			ps.setInt(2, userid);
			ps.execute();
			flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return flag;
	}
	
	public boolean joinParty(int userid,int partyid){
		boolean flag = false;
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO p_member (partyId,userId) VALUES(?,?)";
		try{
			ps = conn.prepareStatement(sql);
			ps.setInt(1, partyid);
			ps.setInt(2,userid);
			ps.execute();
			flag = true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return flag;
	}
	
	public boolean cancelParty(int partyid){
		boolean flag = false;
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM party WHERE partyId=?";
		try{
			ps = conn.prepareStatement(sql);
			ps.setInt(1, partyid);
			//ps.setInt(2,myid);
			ps.execute();
			flag = true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return flag;
	}
}