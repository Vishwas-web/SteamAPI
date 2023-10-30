package steamAPI;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FilteringElementsUsingSearch {
	@Test
	public void verifyFilteringEle() {
		WebDriver driver = new FirefoxDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//div[@id='app']//ul/li[1]/a")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("D");
		List<WebElement> low = driver.findElements(By.xpath("//ul[@class='oxd-main-menu']/li/a"));
		System.out.println("low.size "+low.size());
		
		List<WebElement> filterList = low.stream().filter(s->s.getText().toUpperCase().contains("D")).collect(Collectors.toList());
		System.out.println("filter.size " + filterList.size());
		Assert.assertEquals(low, filterList,"Both list are not watching");
	}
}
