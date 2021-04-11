package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import driverBase.Base;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Hooks extends Base {

    public Scenario scenario;

    @Before
    public void startUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {

        String dateNow = new SimpleDateFormat("ddMMyy").format(new GregorianCalendar().getTime());
        String timeNow = new SimpleDateFormat("HHmmss").format(new GregorianCalendar().getTime());
        String fileName = String.format("./Screenshots/%s/screenshot_%s.png", dateNow, timeNow + "_" + scenario.getName());

        if (scenario.isFailed()) {

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFile, new File(fileName));
                System.out.println("Failed scenario is " + scenario.getName());
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
        driver.quit();
    }
}
