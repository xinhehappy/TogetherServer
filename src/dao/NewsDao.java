package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import bean.News;

public class NewsDao extends BaseDao{
	public List<News> getNewsList(int userid){
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<News> newslist = new ArrayList<News>();
		String sql ="SELECT news.newsId,news.authorId,news.content,news.publishTime," +
				"news.forId,news.commentCount,news.forwardCount " +
				"FROM news INNER JOIN friend ON news.authorId=friend.fid WHERE friend.myId=? LIMIT 10";
//				"FROM news,friend WHERE friend.myId=? AND news.authorId=friend.fid LIMIT 10";
		try{
			ps = conn.prepareStatement(sql);
			ps.setInt(1,userid);
			rs = ps.executeQuery();
			while(rs.next()){
				News news = new News();
				news.setId(rs.getInt(1)) ;
				news.setAuthor(rs.getInt(2));
				news.setContent(rs.getString(3));
				news.setPublishTime(rs.getString(4));
				news.setForId(rs.getInt(5));
				news.setCommentCount(rs.getInt(6));
				news.setForwardCount(rs.getInt(7));
				newslist.add(news);
				System.out.println(news.toString());
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return newslist;
	}
	
	public News getNews(int newsid){
		Connection conn = getConn();
		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql ="";
		News news = new News();
		try{
			ps = conn.prepareStatement(sql);
			ps.setInt(1,newsid);
			rs = ps.executeQuery();
			news.setId(rs.getInt(1)) ;
			news.setAuthor(rs.getInt(2));
			news.setContent(rs.getString(3));
			news.setPublishTime(rs.getString(4));
			news.setForId(rs.getInt(5));
			news.setCommentCount(rs.getInt(6));
			news.setForwardCount(rs.getInt(7));
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			getClose(rs,ps,conn);
		}
		return news;
	}
	
	int publish(News news){
		return 0;
	}
	
	
	int comment(int newsid,int userid,String comment){
		return 0;
		
	}
	
	JSONArray getComment(int newsid){
		JSONArray array = new JSONArray();
		
		return array;
	}
	
}
