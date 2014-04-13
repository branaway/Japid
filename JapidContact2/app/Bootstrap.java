import java.util.*;

import play.db.jpa.JPA;
import play.jobs.*;
import play.test.*;
import models.*;

@OnApplicationStart
public class Bootstrap extends Job {

    public void doJob() {
        if(JPA.count(Contact.class) == 0) {
            Fixtures.load("data.yml");
        }
    }
    
}

