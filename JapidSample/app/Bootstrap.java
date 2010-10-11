import java.io.IOException;

import play.*;
import play.jobs.*;
import play.test.*;
import utils.MyMemClient;
 
import models.*;
 
//@OnApplicationStart
public class Bootstrap extends Job {
 
    public void doJob() {
    	try {
			MyMemClient.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
}