import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonPointer;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonValue;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import entity.City;
import rest.CityResource;
import service.DbService;


@RunWith(Arquillian.class)
public class ResourcesTest {
 
    
    @Deployment
    @TargetsContainer("wildfly-remote-test2")
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "resources.war")
            .addClasses(CityResource.class, PlaceApplication.class, DbService.class, City.class)
            .addAsResource(new File("src/main/resources/META-INF/persistence.xml"),"META-INF/persistence.xml");
    }


    @Test
    @RunAsClient
    public void testCity(@ArquillianResource URL baseUrl) throws IOException {
        URL url = new URL(baseUrl, "/resources/city/?city=Ossingen");

        InputStream is = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        var jr = Json.createReader(br);

        var js = jr.read();
        var jpCity = Json.createPointer("/0/city");
        var jpPlz = Json.createPointer("/0/plz");

        assertEquals("\"Ossingen\"", jpCity.getValue(js).toString());
        assertEquals("\"8475\"", jpPlz.getValue(js).toString());



    }
}
