import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import javax.inject.Inject;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import rest.HelloResource;
import service.HelloService;



@RunWith(Arquillian.class)
public class IntegrationTest {
    

    @Inject
    HelloService helloService;

    @Inject
    HelloResource helloResource;


    @Deployment
    @TargetsContainer("wildfly-remote-test2")
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addClasses(
                HelloService.class, 
                HelloResource.class, 
                PlaceApplication.class);
    }


    @Test
    public void testTest() {
        assertEquals("x", "x");
    }

    @Test
    public void testInjection() {
        assertNotNull(helloService);
        assertEquals("Hello World!", helloService.sayHello("World"));
    }

    @Test
    public void testResourcesCDI() {
        assertNotNull(helloResource);
        assertEquals("Hello World!", helloResource.sayHello("World"));
    }


    @Test
    @RunAsClient
    public void testResources(@ArquillianResource URL baseUrl) throws IOException {
        /* ==== URL ===
        host and port from baseUrl
        "/test"       from archive name (test.war)
        "/"           from PlaceApplication
        "hello"       from HelloResource       */
        URL url = new URL(baseUrl, "/test/hello/World");

        InputStream is = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        assertEquals("Hello World!", br.readLine());
    }
}
