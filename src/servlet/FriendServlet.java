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
import bean.Friend;
import bean.User;
import dao.MessageDao;
import dao.UserDao;
import dao.FriendDao;

public class FriendServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public FriendServlet() {
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
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// JSONObject object = new JSONObject();
		// UserDao dao = new UserDao();
		String myid = request.getParameter("myid");
		String fid = request.getParameter("fid");
		String type = request.getParameter("type");
		String neckname = request.getParameter("neckname");
		String name = request.getParameter("name");

		// type == 1 ��ȡ�����б�
		// type == 2 ��ȡ������Ϣ
		// type == 3 ���ĺ��ѱ�ע
		// type == 4 ���Һ���
		// type == 5 ��Ӻ���
		// type == 6 ɾ������
		// type == 7 �ж��Ƿ��Ǻ���

		if (type.equals("1")) { // ��ȡ�����б�������
			FriendDao dao = new FriendDao();
			List<Friend> friendlist = dao.getFriendList(Integer.parseInt(myid));
			JSONArray array = new JSONArray();
			for (Friend bean : friendlist) {
				JSONObject object = new JSONObject();
				// id���ǳơ�ͷ��
				int id = bean.getFid();
				User user = new User();
				UserDao userdao = new UserDao();
				user = userdao.getUserInfo(id);
				object.put("fid", id);
				object.put("name", bean.getNeckname());
				object.put("avatar", user.getAvatar());
				array.add(object);
			}
			out.write(array.toString());

		} else if (type.equals("2")) { // ��ȡ������Ϣ ���
			FriendDao fdao = new FriendDao();
			User user = fdao.getFriendInfo(Integer.parseInt(fid));
			JSONObject object = JSONObject.fromObject(user);
			// object.put("",user.)
			out.write(object.toString());
		} else if (type.equals("3")) { // �޸ĺ��� ��ע ���
			FriendDao dao = new FriendDao();
			Friend friend = new Friend(Integer.parseInt(myid),
					Integer.parseInt(fid), neckname);
			// URLEncoder.encode(neckname,"UTF-8"));
			// new String(neckname.getBytes("GBK"),"UTF-8"));
			System.out.println(neckname);
			// Todo
			if (dao.setRemark(friend)) {
				out.write("1");
			} else {
				out.write("0");
			}
		} else if (type.equals("4")) { // �����û� ok
			UserDao dao = new UserDao();
			List<User> userlist = dao.searchUser(name, Integer.parseInt(myid));
			JSONArray array = new JSONArray();
			for (User bean : userlist) {
				JSONObject object = new JSONObject();
				// // id���ǳơ�ͷ��
				// FriendDao frienddao = new FriendDao();
				// Friend friend = new Friend(Integer.parseInt(myid),
				// bean.getId());
				// int isfriend = 0;
				// if (frienddao.isFriend(friend)) {
				// isfriend = 1;
				// }
				object.put("uid", bean.getId());
				object.put("name", bean.getName());
				object.put("avatar", bean.getAvatar());
				object.put("sign", bean.getSign());
				// object.put("isfriend", isfriend);
				array.add(object);
			}
			out.write(array.toString());
		} else if (type.equals("5")) { // ��Ӻ��� ok
			FriendDao dao = new FriendDao();
			Friend friend = new Friend(Integer.parseInt(myid),
					Integer.parseInt(fid));
			// out.write(String.valueOf(friend.getMyid()));
			// ����Ҫ���غ��ѵ�id��ˢ�º����б��ɵõ�
			if (dao.isFriend(friend)) {
				out.write("0");
			} else {
				dao.addFriend(friend);
				MessageDao mdao = new MessageDao();
				mdao.follow(Integer.parseInt(myid),
					Integer.parseInt(fid));
				out.write("1");
			}
		} else if (type.equals("6")) { // ɾ������ ok
			FriendDao dao = new FriendDao();
			Friend friend = new Friend(Integer.parseInt(myid),
					Integer.parseInt(fid));
			if (dao.delFriend(friend)) {
				out.write("1");
			} else {
				out.write("0");
			}

		} else if (type.equals("7")) { // �Ƿ��Ǻ���
			FriendDao dao = new FriendDao();
			Friend friend = new Friend(Integer.parseInt(myid),
					Integer.parseInt(fid));
			if (dao.isFriend(friend)) {
				out.write("1");
			} else {
				out.write("0");
			}

		} else if (type.equals("8")) {

		} else {
			out.write("request error");
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
