package controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import models.Contact;
import play.data.validation.Valid;
import play.db.jpa.JPA;
import cn.bran.play.JapidController;

public class Application extends JapidController {

    public static void index() {
        Date now = new Date();
        renderJapid(now);
    }
    
    public static void list() {
        List<Contact> contacts = JPA.find(Contact.class).fetch();
        contacts = contacts.stream().sorted((a, b) -> a.name.compareTo(b.name)).collect(Collectors.toList());
//        List<Contact> contacts = JPA.find(Contact.class, "order by name, firstname").fetch();
        // for some unknown reason I cannot use "list" as the default template name
//        renderJapid(contacts);
//        render(contacts);
        // the default template would be named list.html and the derived class name seems to be conflict to the List class
        // So I chain it to another action.
        // for some unknown reason I cannot use "list" as the default template name
        // so I chain it to another one
//        dontRedirect();
//        listAll(contacts);
        renderJapidWith("@listAll", contacts);
    }
    
    public static void yaaa() {
    	renderText("yahoo");
    }
    
    public static void yahoo2(String hi) {
    	System.out.println("what!");
    }
//    
//    /**
//     *  note I don't use public modifier so it won't get enhanced and I don't need call dontRedirect
//     * @param cs
//     */
//    static void listAll(List<Contact> cs) {
//    	renderJapid(cs);
//    }
//    
    public static void form(Long id) {
        if(id == null) {
//            render();
        	renderJapid((Object)null);
        }
        Contact contact = JPA.findById(Contact.class, id);
//        render(contact);
        renderJapid(contact);
    }
    
    public static void save(@Valid Contact contact) {
        if(validation.hasErrors()) {
            if(request.isAjax()) 
            	error("Invalid value");
//            render("@form", contact);
            renderJapidWith("@form", contact);
        }
        System.out.println(contact.toString());
        contact.save();
        // redirect
        list();
    }

}