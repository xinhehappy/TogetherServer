package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import bean.Party;
import bean.User;

public class MessageDao extends BaseDao {

	public JSONArray getMessage(int userid,int type) {	//type=1 ��ȡδ�� type=2 ��ʷ��Ϣ��10����
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		JSONArray mlist = new JSONArray();
		String sql1 = "SELECT mId,mContent FROM message WHERE mTo=? AND mStatus=0";
		String sql2 = "SELECT mId,mContent FROM message WHERE mTo=? AND mStatus=1 LIMIT 10";	//Ĭ��ȡ10��
		try {
			if(type == 1){
				ps = conn.prepareStatement(sql1);
			}
			if(type == 2){
				ps = conn.prepareStatement(sql2);
			}
				ps.setInt(1, userid);
				rs = ps.executeQuery();
				while(rs.next()){
					JSONObject obj= new JSONObject();
					obj.put("mid",rs.getInt(1));
					obj.put("content",rs.getString(2));
					mlist.add(obj);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose(rs, ps, conn);
		}
		return mlist;

	}
	
	public int changeStatus(int mId){
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		int flag = 0;
		String sql = "UPDATE message SET mStatus=1 WHERE mId=?";
		try{
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mId);
			flag = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return flag;
	}
	
	
	//XXX������μ�XXX
	public int invite(int leaderid,int memberid,int partyid){
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		Integer flag = 0;
		String sql = "INSERT INTO message (mTo,mContent) VALUES (?,?)";
		try{
			ps = conn.prepareStatement(sql);
			ps.setInt(1,memberid);
			FriendDao fdao = new FriendDao();
			PartyDao pdao = new PartyDao();
			String name = fdao.getFriendName(memberid,leaderid);
			Party party = pdao.getPartyInfo(partyid);
			ps.setString(2, name+"����μ�<"+party.getName()+">,��ȥ������!");
			ps.execute();
			flag = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose(rs, ps, conn);
		}
		return flag;
	}
	
	//XXX��ע����	myid:��ע��id	fid������ע��id
	public int follow(int myid,int fid){
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		Integer flag = 0;
		String sql = "INSERT INTO message (mTo,mContent) VALUES (?,?)";
		try{
			ps = conn.prepareStatement(sql);
			ps.setInt(1,fid);
			UserDao dao = new UserDao();
			User user = dao.getUserInfo(myid);
			ps.setString(2, user.getName()+"��ע����Ŷ����ȥ�����ɡ�����������໥��ע�����������Է��μӾۻ�Ŷ~��");
			ps.execute();
			flag = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose(rs, ps, conn);
		}
		return flag;
	}
	//XXXȡ����XXX�ۻ�
	public boolean cancelParty(int partyid){
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean flag = false;
		String sql1 = "SELECT userId FROM p_member WHERE partyId=?";
		String sql2 = "INSERT INTO message (mTo,mContent) VALUES (?,?)";
		try{
			//ȡ������������
			List<Integer> list = new ArrayList<Integer>() ;
			ps = conn.prepareStatement(sql1);
			ps.setInt(1,partyid);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(rs.getInt(1));
			}
			//�Բ�����һһ֪ͨ��ȡ��
			PartyDao pdao= new PartyDao();
			Party party = pdao.getPartyInfo(partyid);
			ps = conn.prepareStatement(sql2);
			Iterator<Integer> iter = list.iterator();
			while(iter.hasNext()){
				ps.setInt(1, iter.next());
				ps.setString(2, "���ĸ����㣬�ۻ�<"+party.getName()+">�Ѿ�ȡ��Ŷ��");
				ps.execute();
			}
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose(rs, ps, conn);
		}
		return flag;
	}
	//XXX�˳��˾ۻ�XXX	Ĭ��ֻ֪ͨ�ۻᷢ���ߣ�
	public int quitParty(int partyid,int userid){
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO message (mTo,mContent) VALUES (?,?) ";
		Integer flag=0;
		try {
			ps = conn.prepareStatement(sql);
			PartyDao pdao = new PartyDao();
			Party party = pdao.getPartyInfo(partyid);
			int leaderid = party.getLeaderId();
			ps.setInt(1, leaderid);
			FriendDao fdao = new FriendDao();
			String name = fdao.getFriendName(leaderid, userid);
			ps.setString(2,name+"�˳���<"+party.getName()+">ŶŶ~�˶�Ҫ�ܹ�����");
			ps.execute();
			flag = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getClose(rs, ps, conn);
		}
		return flag;
	}
	
	//friendMessage

	
	
}
