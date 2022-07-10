package misc;

import base.BaseTest;
import conf.TestProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import util.ScreenShotManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;

public class CustomerDetails extends BaseTest {
    private String name;
    private String lastname;
    private int age;

    public String getName() {
        return name;
    }

    public CustomerDetails setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public CustomerDetails setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public int getAge() {
        return age;
    }

    public CustomerDetails setAge(int age) {
        this.age = age;
        return this;
    }
}
