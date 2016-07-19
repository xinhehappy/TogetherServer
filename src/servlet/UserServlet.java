package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;

import net.sf.json.JSONObject;

import dao.UserDao;

public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UserServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//这个是LoginServlet和RegisterServlet的整合
		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		UserDao dao = new UserDao();
		
		String type = request.getParameter("type");
		String pwd = request.getParameter("pwd");
		String myid = request.getParameter("myid");
		String poslat = request.getParameter("poslat");
		String poslon = request.getParameter("poslon");
		String birthday = request.getParameter("birthday");
		String gender = request.getParameter("gender");
		String home = request.getParameter("home");
		String email = request.getParameter("email");
		String sign = request.getParameter("sign");
		UserDao udao = new UserDao();
		// JSONObject object = new JSONObject();

		//out.write(i.toString());
		if(type.equals("1")){	//上传位置
			if(dao.uploadPos(Integer.parseInt(myid),poslat,poslon)){
				out.write("1");
			}else{
				out.write("0");
			}
		}
		
		if(type.equals("2")){	//获取位置
			HashMap<String,Object> map = new HashMap<String,Object>();
			map = dao.getPos(Integer.parseInt(myid));
				JSONObject obj = JSONObject.fromObject(map);
				out.write(obj.toString());
		}
		
		if(type.equals("3")){	//更改用户信息
			User user = new User(Integer.parseInt(myid),gender,sign,email,home,birthday);
			if(1 == udao.changeUserInfo(user)){
				out.write("1");
			}
			else out.write("0");
		}
		
		if(type.equals("4")){	//更改密码
			if(1 == udao.changePwd(Integer.parseInt(myid), Integer.parseInt(pwd))){
				out.write("1");
			}else out.write("0");
		}
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
