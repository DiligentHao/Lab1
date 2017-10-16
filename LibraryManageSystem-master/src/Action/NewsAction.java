package Action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import entity.News;
import domain.NewsMan;
import domain.impl.NewsManImpl;

public class NewsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	News news=new News();
	
	HttpServletRequest request=ServletActionContext.getRequest();
	
	NewsMan newsman=new NewsManImpl();
	
	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}
	
	public String addNews() throws IOException{
		Date today=new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy��-MM��-dd��",Locale.getDefault());
		String releaseTime = dateFormat.format(today);
		
		String filename = "D:\\eclipse-jee-oxygen-R-win32-x86_64\\Java code\\LibraryManageSystem-master\\WebContent\\news"
			+ releaseTime + ".txt";
		java.io.File f = new java.io.File(filename);
		if (!f.exists())//����ļ����棬����
		{
			f.createNewFile();
		}

		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(
					filename));
			pw.println(news.contentpath);//д����
			pw.close();
		} catch (IOException e) {
		}
		news.setContentpath(filename);
		news.setTime(releaseTime);
		newsman.AddNews(news);
		return SUCCESS;
	}
	
	public String showNews(){
		List <News> allnews=newsman.QueryAllNews();
		request.setAttribute("allnews", allnews);
		return SUCCESS;
	}
	
	public String showthisNews() throws IOException{
		News thisnews=newsman.ReadNews(news.getTime());
		String path=thisnews.getContentpath();
		try{
			File f = new java.io.File(path);
			FileReader fr = new java.io.FileReader(f);
			char[] buffer = new char[100];
			int length;
			path="";
			while ((length = fr.read(buffer)) != -1) {
				for(int i=0;i<length;i++)
				path+=buffer[i];
			}
			fr.close();
		}catch(Exception e){
			path="Դ�����Ѿ���ɾ��";
		}
		
		thisnews.setContentpath(path);
		request.setAttribute("thisnews", thisnews);
		return SUCCESS;
	}

}
