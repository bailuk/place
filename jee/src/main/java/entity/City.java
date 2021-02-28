package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "city")
@NamedQueries({
    @NamedQuery(name="City.findById", query="SELECT c FROM City c WHERE c.id = :id"),
    @NamedQuery(name="City.findAll", query="SELECT c FROM City c"),
    @NamedQuery(name="City.findByPlz", query="SELECT c FROM City c WHERE c.plz = :plz"),
    @NamedQuery(name="City.findByCity", query="SELECT c FROM City c WHERE c.city = :city"),
    @NamedQuery(name="City.findByName", query="SELECT c FROM City c WHERE c.city LIKE :name OR c.plz LIKE :name")
})
public class City {

    @Id
    private int id;

    private String plz;
    private String city;

  
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
