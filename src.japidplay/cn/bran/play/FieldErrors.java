package cn.bran.play;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import play.data.validation.Error;
import play.data.validation.Validation;

public class FieldErrors extends ArrayList<Error> {
    
	public FieldErrors(Collection<? extends Error> c) {
		super(c);
	}

	public Error forKey(String key) {
        return Validation.error(key);
    }
    
    public List<Error> allForKey(String key) {
        return Validation.errors(key);
    }
    
}