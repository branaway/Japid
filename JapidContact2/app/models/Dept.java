/**
 * 
 */
package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

/**
 * @author bran
 *
 */
@Entity
public class Dept extends Model {
	public String name;
}
