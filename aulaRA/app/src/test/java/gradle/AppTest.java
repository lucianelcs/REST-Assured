/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package gradle;

import org.junit.Test;
import static org.junit.Assert.*;
import static  org.junit.Assert.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;



public class AppTest {
    @Test public void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }
}