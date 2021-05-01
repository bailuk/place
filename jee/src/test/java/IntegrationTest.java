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

import service.HelloWorldService;

@RunWith(Arquillian.class)
public class IntegrationTest {
    

    @Inject
    HelloWorldService helloWorldService;


    @Deployment
    @TargetsContainer("wildfly-remote-test2")
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class).addClass(HelloWorldService.class);
    }


    @Test
    public void testTest() {
        assertEquals("x", "x");
    }

    @Test
    public void testInjection() {
        assertNotNull(helloWorldService);
        assertEquals("Hello World!", helloWorldService.sayHelloWorld());
    }
}
