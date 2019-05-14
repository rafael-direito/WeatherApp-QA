package weather_app.web.controllers;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import weather_app.constants.Converters;

public class SpecificForecastTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String today;
  private Converters converters = new Converters();
  
  
  @Before
  public void setUp() throws Exception 
  {
      
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    today = dateFormat.format(date).toString();
        
    driver = new ChromeDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSpecificForecast() throws Exception 
  {
    driver.get("http://localhost:8080/generalForecast");
    driver.findElement(By.id("inlineFormInputGroupUsername2")).click();
    driver.findElement(By.id("inlineFormInputGroupUsername2")).clear();
    driver.findElement(By.id("inlineFormInputGroupUsername2")).sendKeys("Aveiro");
    driver.findElement(By.id("inlineFormCustomSelectPref")).click();
    new Select(driver.findElement(By.id("inlineFormCustomSelectPref"))).selectByVisibleText("One");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Number of days'])[1]/following::button[1]")).click();
    String tMaxGeneral = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Tuesday'])[1]/following::span[2]")).getText();
    String tMinGeneral = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Tuesday'])[1]/following::span[3]")).getText();
    driver.findElement(By.name("Partly cloudy")).click();
    assertEquals("Aveiro", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='WeatherApp'])[2]/following::h1[1]")).getText());
    
    
    assertEquals(converters.dateToString(today), driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Aveiro'])[1]/following::h2[1]")).getText());
    assertEquals(tMinGeneral, driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Partly cloudy'])[1]/following::span[1]")).getText());
    assertEquals(tMaxGeneral, driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='ºC'])[1]/following::span[1]")).getText());
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
