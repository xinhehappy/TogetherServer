package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import bean.User;

public class UserDao extends BaseDao {
	User user = new User();

	public boolean login(User user) {
		boolean flag = false;
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "SELECT `userId`, `userName`, `userPwd` FROM user WHERE userName=? and userPwd =?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPwd());
			rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			// User user = new User();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return flag;
	}

	public boolean isExist(User user) {
		boolean flag = false;
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "SELECT * FROM user WHERE userName=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			rs = ps.executeQuery();
			while (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}

		return flag;
	}

	public boolean register(User user) {
		// User u = new User(name);
		// UserDao checkDao = new UserDao();
		boolean flag = false;
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO user (userName, userPwd) VALUES(?,?) ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPwd());
			ps.execute();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return flag;
	}
	
	// TODO 更新用户信息
	public int changeUserInfo(User user){
		int flag = 0;
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "UPDATE user SET userSign=?,userGender=?,userHome=?,userBirthday=?,userEmail=?  WHERE userid=?";
		try{
			ps =conn.prepareStatement(sql);
			ps.setString(1, user.getSign());
			ps.setString(2, user.getGender());
			ps.setString(3,user.getHome());
			ps.setString(4,user.getBirthday());
			ps.setString(5,user.getEmail());
			ps.setInt(6, user.getId());
			flag = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return flag;
	}

	public int changePwd(int userid,int pwd) {
		int flag = 0;
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "UPDATE user set userPwd=? WHERE userId=?";
		try{
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pwd);
			ps.setInt(2, userid);
			flag = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return flag;
	}
	
	public User getUserInfo(String name) {
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "SELECT `userId`, `userName`, `userAvatar`, `userSign` FROM user WHERE userName=?";
		User user = new User();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setAvatar(rs.getString(3));
				user.setSign(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return user;
	}

	public User getUserInfo(int id) {
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "SELECT `userId`, `userName`, `userAvatar`, `userSign` FROM user WHERE userId=?";
		User user = new User();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setAvatar(rs.getString(3));
				user.setSign(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return user;
	}

	public List<User> searchUser(String name) {
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<User> userlist = new ArrayList<User>();
		String sql = "SELECT `userId`, `userName`, `userSign`, `UserAvatar` FROM `user` WHERE `userName` LIKE ? LIMIT 0,50";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + name + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setSign(rs.getString(3));
				user.setAvatar(rs.getString(4));
				userlist.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return userlist;

	}
	
	public List<User> searchUser(String name,int myid) {
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<User> userlist = new ArrayList<User>();
		String sql = "SELECT `userId`, `userName`, `userSign`, `UserAvatar` FROM `user` WHERE `userName` LIKE ? AND userId<>? LIMIT 0,50";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + name + "%");
			ps.setInt(2,myid);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setSign(rs.getString(3));
				user.setAvatar(rs.getString(4));
				userlist.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return userlist;
	}
	
	public boolean uploadPos(int uid,String poslat,String poslon){
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean flag = false;
		String sql = "UPDATE location SET posLat=?,posLon=?,locTime=? WHERE userId=?";
		try{
			ps = conn.prepareStatement(sql);
			ps.setString(1, poslat);
			ps.setString(2, poslon);
			Date now = new Date();
			//System.out.println(now.toString());
		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String time = df.format(now);
		    //System.out.println(time);
		    ps.setString(3,time);
			ps.setInt(4, uid);
			ps.executeUpdate();
			flag = true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return flag;
	}
	
	public boolean initPos(int userid){
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean flag = false;
		String sql = "INSERT INTO location (userId) VALUES (?)";
		try{
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userid);
			flag = ps.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return flag;
	}
	
	public HashMap<String, Object> getPos(int uid){
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		HashMap<String, Object > map = new HashMap<String, Object>();
		String sql = "SELECT posLat,posLon,locTime FROM location WHERE userId=?";
		try{
			ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			rs = ps.executeQuery();
			if(rs.next()){
				map.put("poslat", rs.getString(1));
				map.put("poslon", rs.getString(2));
				map.put("locTime", rs.getTimestamp(3));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return map;
	}
}
