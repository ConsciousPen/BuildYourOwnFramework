package tests.learnI_method_interceptor;

import base.BaseTest;
import org.testng.annotations.Test;

public class CreateUser extends BaseTest {
    private String user;
    private String password;

    public CreateUser(String user, String password) {
        this.user = user;
        this.password = password;
        System.out.println("CreateUser :" + user + ":" + password);
    }

    @Test
    public void testCreateUser() {
        System.out.printf("testCreateUser");
    }
}
