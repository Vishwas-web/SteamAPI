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

public class SortedWebTableElements {
	
	@Test
	public void VerifySortedElement() {
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
		
		List<WebElement> low = driver.findElements(By.xpath("//div[@class='oxd-table-body']/div/div/div[4]"));
		List<String> empNameList = low.stream().map(s->s.getText()).collect(Collectors.toList());
		List<String> loString = empNameList.stream().map(x->getEmpName(x)).sorted().collect(Collectors.toList());
		Assert.assertEquals(empNameList, loString,"Both list are not equal");
		driver.quit();
	}

	private String getEmpName(String e) {
		String[] as = e.split(" ");
		String firstWord = as[0];
		String secondWord = as[1];
		char firstLetter = ' ';
		String otherLetter ="";
		int in = 0;
		for(int i=0;i<firstWord.length();i++) {
			if(firstWord.charAt(0)>=48 && firstWord.charAt(0)<=57) {
				break;
			}
			if(i==0) {
				if(firstWord.charAt(0)>=97 && firstWord.charAt(0)<=122) {
					firstLetter = (char) (firstWord.charAt(0) - 32);
					in = 1;
				}else {
					firstLetter = firstWord.charAt(0);
					in = 1;
				}
			}
			else {
				if(firstWord.charAt(i)>=65 && firstWord.charAt(i)<=90) {
					otherLetter = otherLetter + (char) (firstWord.charAt(i)+32);
					in = 1;
				}else if(firstWord.charAt(i)>=97 && firstWord.charAt(i)<=122){
					otherLetter = otherLetter + firstWord.charAt(i);
					in = 1;
				}
			}
		}
		String firstString;
		if(in == 1) {
			firstString = firstLetter + otherLetter;
		}else {
			firstString = firstWord;
		}
		 // for second word
		firstLetter=' ';
		otherLetter="";
		in = 0;
		 for(int i=0;i<secondWord.length();i++)
		 {
			 if (secondWord.charAt(0)>=48 && secondWord.charAt(0)<=57)
				 break;
			if (i == 0)
			{
				if (secondWord.charAt(0)>=97 && secondWord.charAt(0)<=122)
				{
					 firstLetter = (char) (secondWord.charAt(0) - 32);
					 in = 1;
				}
				 else
				 {
					 firstLetter = secondWord.charAt(0);
					 in = 1;
				 }

			}
			else
			{
				if (secondWord.charAt(i)>=65 && secondWord.charAt(i)<=90)
				{
					otherLetter = otherLetter + (char) (secondWord.charAt(i) + 32);
					in = 1;
				}	 
				 else if (secondWord.charAt(i)>=97 && secondWord.charAt(i)<=122)
				 { 
					 otherLetter = otherLetter + secondWord.charAt(i);
					 in = 1;	
				 }
			}
		 }
		 String secondString = firstLetter + otherLetter;
		 if (in == 1)
			 secondString = firstLetter + otherLetter;
		 else
			 secondString = secondWord ;
		 
		 return firstString+ " " + secondString;
		
	} 
	

	
}
