package ws;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.junit.Test;

import com.sds.acube.luxor.ws.client.sch.Page;
import com.sds.acube.luxor.ws.client.sch.SchScheduleService;
import com.sds.acube.luxor.ws.client.sch.SchScheduleServiceImplService;
import com.sds.acube.luxor.ws.client.sch.SearchVO;

public class SchTest {
  
  @Test
  public void 일정목록조회() throws MalformedURLException {
    
    URL url = new URL("http://127.0.0.1:8080/luxor_collaboration/cxf-jaxws/MobileScheduleWebService?wsdl");
    
   QName qName = new QName("http://ws.sch.collaboration.luxor.acube.sds.com/", "SchScheduleServiceImplService");
   
   SchScheduleServiceImplService s = new SchScheduleServiceImplService(url, qName);
   
   SchScheduleService ss = s.getSchScheduleServiceImplPort();
   
   SearchVO vo = new SearchVO();
   
   
   Page page = ss.getPagingList(vo);
    
   page.getList();
    
    
   
  }

}
