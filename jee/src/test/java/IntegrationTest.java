import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

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
        return ShrinkWrap.create(WebArchive.class)
            .addClass(HelloService.class)
            .addClass(HelloResource.class);
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
    public void testResources() {
        assertNotNull(helloResource);
        assertEquals("Hello World!", helloResource.sayHello("World"));
    }
}
