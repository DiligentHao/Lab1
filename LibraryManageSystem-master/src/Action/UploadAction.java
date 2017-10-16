package Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HttpServletRequest request=ServletActionContext.getRequest();
	private File file;
	private String fileFileName;        
    
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void UploadPic() throws IOException {
		//String root = ServletActionContext.getRequest().getRealPath("/upload");
		
		String saveurl="D:\\eclipse-jee-oxygen-R-win32-x86_64\\Java code\\LibraryManageSystem-master\\WebContent\\Images\\bookpic\\";
		InputStream is = new FileInputStream (file);//(//);
        
        //�õ�ͼƬ�����λ��(����root���õ�ͼƬ�����·����tomcat�µĸù�����)
        File destFile = new File(saveurl,this.fileFileName);
        
        //��ͼƬд�뵽�������õ�·����
        OutputStream os = new FileOutputStream(destFile);
        byte[] buffer = new byte[400];
        int length  = 0 ;
        while((length = is.read(buffer))>0){
            os.write(buffer, 0, length);
        }
        is.close();
        os.close();
        System.out.println(fileFileName+"~~~~~~~~~~~~~~~"+" In the path :"+saveurl);
        String picuri=saveurl+this.fileFileName;
        HttpSession session = request.getSession(); 
		session.setAttribute("bookuri", picuri);
	}
}
