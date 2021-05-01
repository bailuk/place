package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "student")
@NamedQueries({
    @NamedQuery(name="Student.findById", query="SELECT s FROM Student s WHERE s.id = :id"),
    @NamedQuery(name="Student.findAll", query="SELECT s FROM Student s"),
    @NamedQuery(name="Student.findByName", query="SELECT s FROM Student s WHERE s.name LIKE :name")
})
public class Student {

    @Id
    private int id;


    private String name;
    private int marker;


    @ManyToOne
    @JoinColumn(name="id_city")    
    private City city;

    
    public void init(StudentIn studentIn, City city) {
    	name = studentIn.name;
    	marker = studentIn.marker;
    	this.city = city; 
	}

    
	public int getId() {
        return id;
    }

	
    public String getName() {
        return name;
    }

    public int getMarker() {
    	return marker;
    }
    
    public int getCityId() {
        return city.getId();
    }

    public String getPlz() {
        return city.getPlz();
    }

    public String getCity() {
        return city.getCity();
    }

}
