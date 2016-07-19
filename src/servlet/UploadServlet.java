package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filePath; // ����ϴ��ļ���Ŀ¼
	private String tempFilePath;// �����ʱ�ļ���Ŀ¼

	/**
	 * Constructor of the object.
	 */
	public UploadServlet() {
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

		 response.setContentType("text/html;charset=utf-8");
		 request.setCharacterEncoding("utf-8");
		 //��ͻ��˷�����Ӧ����
		 PrintWriter outNet = response.getWriter();
		 try{
		 //����һ������Ӳ�̵�FileItem����
		 DiskFileItemFactory factory = new DiskFileItemFactory();
		//������Ӳ��д����ʱ���õĻ������Ĵ�С���˴�Ϊ4K  
	      factory.setSizeThreshold(4*1024);   
	      //������ʱĿ¼  
	      factory.setRepository(new File(tempFilePath));  
	  
	      //����һ���ļ��ϴ�������  
	      ServletFileUpload upload = new ServletFileUpload(factory);  
	      //���������ϴ����ļ������ߴ磬�˴�Ϊ4M  
	      upload.setSizeMax(4*1024*1024);   
	      
	      List<FileItem> items = upload.parseRequest(request);      
	  
	      Iterator<FileItem> iter = items.iterator();  
	      while (iter.hasNext()) {  
	        FileItem item = iter.next();  
	        if(item.isFormField()) {  
	          processFormField(item,outNet); //������ͨ�ı���  
	        }else{  
	          processUploadedFile(item,outNet); //�����ϴ��ļ�  
	        }  
	      }  
	      outNet.close();  
	    }catch(Exception e){  
	       throw new ServletException(e);  
	    }  
		 }
	  private void processFormField(FileItem item,PrintWriter outNet){  
		    String name = item.getFieldName();  
		    String value = item.getString();  
		    outNet.println(name+":"+value+"\r\n");  
		  }  
		    
		    
		  private void processUploadedFile(FileItem item,PrintWriter outNet)throws Exception{  
		    String filename=item.getName();  
		   // System.out.println("�ļ����ǣ�"+filename);
		    int index=filename.lastIndexOf("\\");  
		    filename=filename.substring(index+1,filename.length());  
		    long fileSize=item.getSize();  
		      
		    if(filename.equals("") && fileSize==0)return;  
		  
		    File uploadedFile = new File(filePath+"/"+filename);  
		    //File uploadedFile = new File(filePath+"/"+id);  
		    item.write(uploadedFile);  
		    outNet.println(filename+" is saved.");  
		    outNet.println("The size of " +filename+" is "+fileSize+"/r/n");  
		  }  

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init(ServletConfig config) throws ServletException {
		// Put your code here
		super.init(config);
		filePath=config.getInitParameter("filePath");
		tempFilePath=config.getInitParameter("tempFilePath");
		filePath=getServletContext().getRealPath(filePath);
		tempFilePath=getServletContext().getRealPath(tempFilePath);
	}

}
