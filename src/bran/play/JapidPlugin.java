package bran.play;

import play.PlayPlugin;
import play.exceptions.UnexpectedException;
import play.mvc.Http.Request;
import play.templates.Template;
import bran.japid.BranTemplateBase;

public class JapidPlugin extends PlayPlugin {

    @Override
    public void onApplicationStop() {
        try {
            Japid.shutdown();
        } catch (Exception e) {
            throw new UnexpectedException (e);
        }
    }

    
    
    @Override
	public void beforeInvocation() {
    	// cannot do this. Rather I should get the controller name from the thread local
    	// the urlMapper is shared by threads!
//		BranTemplateBase.urlMapper = new RouteAdapter(Request.current().controller);
	}



	@Override
	public void afterApplicationStart() {
    	Japid.startup();
	}


	@Override
	public void onApplicationStart() {
		// TODO Auto-generated method stub
		super.onApplicationStart();
	}


	@Override
	public void onTemplateCompilation(Template template) {
		// TODO Auto-generated method stub
		super.onTemplateCompilation(template);
	}


	@Override
    public void onEvent(String message, Object context) {

    }
}


