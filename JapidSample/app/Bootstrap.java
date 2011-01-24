import java.io.IOException;

import play.jobs.Job;
 
//@OnApplicationStart
public class Bootstrap extends Job {
 
    public void doJob() {
    	try {
//			MyMemClient.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
}