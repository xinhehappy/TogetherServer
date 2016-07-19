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
import bean.Party;
import dao.FriendDao;
import dao.MessageDao;
import dao.PartyDao;

public class PartyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public PartyServlet() {
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

		String myid = request.getParameter("myid");
		String partyid = request.getParameter("partyid");
		String fid = request.getParameter("fid");
		String address = request.getParameter("address");
		String name = request.getParameter("name");
		String starttime = request.getParameter("starttime");

		String poslat = request.getParameter("poslat");
		String poslon = request.getParameter("poslon");
		String note = request.getParameter("note");
		String type = request.getParameter("type");

		// type | function
		// 1 | �ۻ��б�
		// 2 | �ۻ���Ϣ
		// 3 | ����ۻ�
		// 4 | �˳��ۻ�
		// 5 | ����ۻ�
		// 6 | ɾ���ۻ�

		if (type.equals("1")) { // ��ȡ�ۻ��б�
			PartyDao dao = new PartyDao();
			List<Party> partylist = dao.getPartyList(Integer.parseInt(myid));
			JSONArray array = new JSONArray();
			for (Party bean : partylist) {
				JSONObject object = new JSONObject();
				object.put("id", bean.getId());
				object.put("name", bean.getName());
				object.put("status", bean.getStatus()); // status��δ��ʼ���ѽ�������ȡ��
				array.add(object);
			}
			out.write(array.toString());
		} else if (type.equals("2")) { // �ۻ���Ϣ
			PartyDao dao = new PartyDao();
			Party party = new Party();
			party = dao.getPartyInfo(Integer.parseInt(partyid));

			JSONObject object = JSONObject.fromObject(party);
			List<bean.Party.Member> memberlist = dao.getPartyMember(Integer
					.parseInt(partyid));
			// System.out.println(memberlist.toString());
			JSONArray array = JSONArray.fromObject(memberlist);
			System.out.println(array.toString());
			object.element("member", array); // ����ۻ����Ա������userId,userName,isAttend����
			out.write(object.toString());
		} else if (type.equals("3")) { // ����ۻ�
			PartyDao dao = new PartyDao();
			Party party = new Party();
			party.setName(name);
			party.setLeaderId(Integer.parseInt(myid));
			party.setAddress(address);
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String time = df.format(starttime);
//			Timestamp ts = Timestamp.valueOf(starttime);
			party.setStartTime(starttime);
			party.setPosLat(poslat);
			party.setPosLon(poslon);
			party.setNote(note);
			int pid=0;
			if ((pid=dao.createParty(party))!=0 ) {
				dao.joinParty(Integer.parseInt(myid),pid);
				System.out.println("success!");
				out.write(String.valueOf(pid));
			} else {
				out.write("0");
			}
		} else if (type.equals("4")) { // �˳��ۻ�
			PartyDao dao = new PartyDao();
			if (dao.quitParty(Integer.parseInt(myid), Integer.parseInt(partyid))) {
				out.write("1");
				MessageDao mdao = new MessageDao();
				mdao.quitParty(Integer.parseInt(partyid), Integer.parseInt(myid));
			} else {
				out.write("0");
			}
		} else if (type.equals("5")) { // ����ۻ�  TODO ������Ҫ�޸�android�˼�myid
			PartyDao pdao = new PartyDao();
			FriendDao fdao = new FriendDao();
			if(fdao.isFriend(Integer.parseInt(fid),Integer.parseInt(myid)) && pdao.joinParty(Integer.parseInt(fid), Integer.parseInt(partyid))) {
				// XXX(������)����μ�XXX(�ۻ�����)
				MessageDao mdao = new MessageDao();
				mdao.invite(Integer.parseInt(myid), Integer.parseInt(fid), Integer.parseInt(partyid));
				out.write("1");
			} else {
				out.write("0");
			}
		} else if (type.equals("6")) { // ȡ���ۻ�
			PartyDao pdao = new PartyDao();
			MessageDao mdao = new MessageDao();

			if (mdao.cancelParty(Integer.parseInt(partyid)) && pdao.cancelParty(Integer.parseInt(partyid))) {
				out.write("1");
				// XXX�������ߣ�ȡ����XXX�ۻ�
			} else {
				out.write("0");
			}
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
