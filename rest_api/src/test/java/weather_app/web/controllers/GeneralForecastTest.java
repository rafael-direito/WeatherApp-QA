package weather_app.web.controllers;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static weather_app.web.controllers.Constants.BASE_URL;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;

@RunWith(MockitoJUnitRunner.class)
public class GeneralForecastTest {
  private FirefoxOptions options = new FirefoxOptions();
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    options.addArguments("--headless");
    driver = new FirefoxDriver(options);
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      System.out.println("banana");
  }


  @Test
  public void testTQSHomework() throws Exception {
    driver.get(BASE_URL + "/generalForecast");
    driver.findElement(By.id("inlineFormInputGroupUsername2")).click();
    driver.findElement(By.id("inlineFormInputGroupUsername2")).clear();
    driver.findElement(By.id("inlineFormInputGroupUsername2")).sendKeys("Coimbra");
    driver.findElement(By.id("inlineFormCustomSelectPref")).click();
    new Select(driver.findElement(By.id("inlineFormCustomSelectPref"))).selectByVisibleText("Three");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Number of days'])[1]/following::button[1]")).click();
    assertEquals("Coimbra", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Search'])[1]/following::h1[1]")).getText());
    assert(Double.parseDouble(driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Tuesday'])[1]/following::span[2]")).getText()) > -50 );
    assert(Double.parseDouble(driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Tuesday'])[1]/following::span[3]")).getText()) < 100);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }

}
