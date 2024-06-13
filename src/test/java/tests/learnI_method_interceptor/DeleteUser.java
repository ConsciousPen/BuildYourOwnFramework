package tests.learnI_method_interceptor;


import base.BaseTest;
import org.testng.annotations.Test;

public class DeleteUser extends BaseTest {

    private String user;

    public DeleteUser(String user) {
        this.user = user;
        System.out.println("Delete user :" + user);

    }

    @Test
    public void testDeleteUser() {
        System.out.printf("testDeleteUser");
    }

}
