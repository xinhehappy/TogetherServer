package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Friend;
import bean.User;

public class FriendDao extends BaseDao {
	
	public List<Friend> getFriendList(int myid) {
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Friend> friendlist = new ArrayList<Friend>();
		String sql = "SELECT `Id`,`fId`,`neckname` FROM `friend` WHERE `myId` = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, myid);
			rs = ps.executeQuery();
			while (rs.next()) {
				Friend friend = new Friend();
				friend.setId(rs.getInt(1));
				friend.setFid(rs.getInt(2));
				friend.setNeckname(rs.getString(3));
				friendlist.add(friend);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}

		return friendlist;
	}

	public boolean addFriend(Friend friend) {
		boolean flag = false;
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		// 先把设置好友备注为好友的username
		User user = new User();
		UserDao dao = new UserDao();
		user = dao.getUserInfo(friend.getFid());
		String sql = "INSERT INTO friend(myId,fId,neckname) VALUES(?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, friend.getMyid());
			ps.setInt(2, friend.getFid());
			ps.setString(3, user.getName());
			ps.execute();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			getClose(rs,ps,conn);	
		}
		return flag;
	}

	public boolean delFriend(Friend friend) {
		boolean flag = false;
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM friend WHERE myId=? AND fId=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, friend.getMyid());
			ps.setInt(2, friend.getFid());
			ps.execute();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			getClose(rs,ps,conn);	
		}
		return flag;
	}

	public boolean setRemark(Friend friend) {
		boolean flag = false;
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "UPDATE friend SET neckname=? WHERE myId=? AND fId=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, friend.getNeckname());
			ps.setInt(2, friend.getMyid());
			ps.setInt(3, friend.getFid());
			ps.execute();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);	
		}
		return flag;
	}

	public boolean isFriend(Friend friend) {
		boolean flag = false;
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;

		String sql = "SELECT * FROM friend WHERE myId=? AND fId=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, friend.getMyid());
			ps.setInt(2, friend.getFid());
			rs = ps.executeQuery();
			while (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			getClose(rs,ps,conn);	
		}
		return flag;
	}
	
	public boolean isFriend(int myid, int fid) {
		boolean flag = false;
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;

		String sql = "SELECT * FROM friend WHERE myId=? AND fId=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, myid);
			ps.setInt(2, fid);
			rs = ps.executeQuery();
			while (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			getClose(rs,ps,conn);	
		}
		return flag;
	}
	
	public User getFriendInfo(int fid) {
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "SELECT userName, userAvatar, userSign, userGender, userHome, userBirthday, userEmail FROM user WHERE userId=?";
		User user = new User();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fid);
			rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(fid);
				user.setName(rs.getString(1));
				user.setAvatar(rs.getString(2));
				user.setSign(rs.getString(3));
				user.setGender(rs.getString(4));
				user.setHome(rs.getString(5));
				user.setBirthday(rs.getString(6));
				user.setEmail(rs.getString("userEmail"));
			}
			getClose(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			getClose(rs,ps,conn);	
		}
		return user;
	}
	
	
	
	public String getFriendName(int myid,int fid) {
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "SELECT neckname FROM friend WHERE myId=? AND fId=?";
		String name= "";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, myid);
			ps.setInt(2, fid);
			rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString("neckname");
			}
			getClose(rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			getClose(rs,ps,conn);	
		}
		return name;
	}
}
