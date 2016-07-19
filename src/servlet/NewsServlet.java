package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import bean.News;
import bean.User;

import dao.NewsDao;
import dao.UserDao;

/**
 * Servlet implementation class NewsServlet
 */

public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String userid = request.getParameter("myid");
		String type = request.getParameter("type");
		

		// type =1 ��ȡ�������б�
		// type =2 ����������
		// type =3 ��ȡ����
		// type =4 ��ȡ�����б�
		NewsDao ndao = new NewsDao();
		
		if (type == "1") {
			List<News> newslist = ndao.getNewsList(Integer.parseInt(userid));
			JSONArray array = new JSONArray();
			for(News news:newslist){
				UserDao udao = new UserDao();
				User user = udao.getUserInfo(news.getAuthor());
				JSONObject object = new JSONObject();
				object.put("id", news.getId());
				object.put("authorid", news.getAuthor());
				object.put("author", user.getName());
				object.put("content", news.getContent());
				object.put("time", news.getPublishTime()); 
				array.add(object);
			}
			out.write(array.toString());
		}
		if (type == "2"){
			
		}

	}

}
