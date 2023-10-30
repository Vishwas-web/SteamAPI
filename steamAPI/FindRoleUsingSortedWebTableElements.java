package steamAPI;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class FindRoleUsingSortedWebTableElements {
	@Test
	public void verifyRole() {
		WebDriver driver = new FirefoxDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//div[@id='app']//ul/li[1]/a")).click();
		
		driver.findElement(By.xpath("//div[@id='app']//div[4]/div/i")).click();
		driver.findElement(By.xpath("//div[@id='app']//div[4]//ul/li[1]/span")).click();
		
		List<WebElement> low = driver.findElements(By.xpath("/div[@class='oxd-table-body']/div/div/div[4]"));
		List<String> empNameList = low.stream().map(s->s.getText()).collect(Collectors.toList());
		List<String> listOfRoles = low.stream().filter(s->s.getText().contains("John Smith")).map(s->getRole(s)).collect(Collectors.toList());
		if(listOfRoles.size() > 0) {
			System.out.println("ListOfRoles.get(0) "+ listOfRoles.get(0));
		}
		driver.quit();
	} 
	private static String getRole(WebElement ele) {
		return ele.findElement(By.xpath("preceding-sibling::div[1]/div")).getText();
	}
}
