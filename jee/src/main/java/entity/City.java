package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "city")
@NamedQueries({
    @NamedQuery(name="City.findById", query="SELECT c FROM City c WHERE c.id = :id"),
    @NamedQuery(name="City.findAll", query="SELECT c FROM City c")
})
public class City {

    @Id
    private int id;


    @Column(length = 30)
    private String plz;

    @Column(length = 30)
    private String city;



    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }


    public String getCity() {
        return city;
    }


    public String getPlz() {
        return plz;
    }
}
