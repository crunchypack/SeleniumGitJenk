package selenium.adv.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login {
	static WebDriver driver;
	String driverPath = "G:\\Utils\\drivers\\";
	String assertionString="demoforsHardAssertion";
  @Test
  public void f() {
	  driver.findElement(By.xpath("//input[@type='text']")).sendKeys("simon.wolf.lobo@gmail.com");
	  driver.findElement(By.xpath("//input[@type='password']")).sendKeys("simON123");
	  driver.findElement(By.className("btn-success")).click();
		
		try {
			new WebDriverWait(driver, 5000).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("btn-danger")));
			

		}catch (Exception e) {
			System.out.println("FAIL");			
		}
  }
  @Test(dependsOnMethods="f",enabled= true)
  public void adminTest() throws InterruptedException {
  	driver.findElement(By.id("details-title")).sendKeys("Mission: Impossible");
  	driver.findElement(By.id("details-desc")).sendKeys("After his team is double-crossed, a spy races to find the mole who betrayed them and prevent a top-secret list from falling into the wrong hands.");
  	driver.findElement(By.id("details-year")).sendKeys("1996");
  	driver.findElement(By.id("details-length")).sendKeys("110");
  	driver.findElement(By.id("details-director")).sendKeys("Brian De Palma");
  	driver.findElement(By.id("details-url")).sendKeys("https://www.imdb.com/title/tt0117060/");
  	Select genre = new Select(driver.findElement(By.className("custom-select")));
  	genre.selectByVisibleText("Action");
  	genre.selectByVisibleText("Thriller");
  	genre.selectByVisibleText("Adventure");
  	driver.findElement(By.cssSelector("input[id^=\'__BVID__\']")).sendKeys("Tom Cruise");
  	
			Thread.sleep(5000);
		
  }
  @Test(dependsOnMethods="adminTest",enabled = true)
  public void logout() {
  	driver.findElement(By.className("btn-danger")).click();
  }
  @Test
  public void test3() {
      //Soft assert Object
      SoftAssert softAssert=new SoftAssert();
      softAssert.assertEquals("demoforHardAssertions", assertionString);
      System.out.println("After Assert equals");
      softAssert.assertTrue(assertionString.equals("demoforHardAssertions"));
      System.out.println("After Assert true");
      }
  
  @BeforeClass
  @Parameters({"browser","URL"})
  public void beforeClass(@Optional("Firefox")String browsername, String url) {
	  switch (browsername) {
      case "Edge":
      	System.setProperty("webdriver.edge.driver", driverPath + "msedgedriver.exe");
      	driver = new EdgeDriver();
      	driver.get(url);
          driver.manage().window().maximize();
          driver.manage().timeouts().implicitlyWait(2000,TimeUnit.MILLISECONDS);
      	break;
      	
      case "Firefox":
      	System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
  		driver = new FirefoxDriver(); 
  		driver.get(url);
          driver.manage().window().maximize();
          driver.manage().timeouts().implicitlyWait(2000,TimeUnit.MILLISECONDS);
  		break;
      case "Chrome":
      	System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
  		driver = new ChromeDriver();
  		driver.get(url);
          driver.manage().window().maximize();
          driver.manage().timeouts().implicitlyWait(2000,TimeUnit.MILLISECONDS);
  		break;
      }
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
