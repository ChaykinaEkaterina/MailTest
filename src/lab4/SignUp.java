package lab4;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUp {
	static String startPage = ConfProperties.getProperty("startpage");
	static String userName = ConfProperties.getProperty("user");
	static String password = ConfProperties.getProperty("password");
	static String upload = ConfProperties.getProperty("uploadfile");
	static WebDriver driver;
	static WebDriverWait wait;
	

	@Test 
	public void signIn() {
		System.setProperty("webdriver.edge.driver", ConfProperties.getProperty("edgedriver"));
		driver  = new EdgeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		driver.get(ConfProperties.getProperty("startpage"));
		//String loginPage = driver.findElement(By.linkText("Sign in")).getAttribute("href");
		driver.findElement(By.cssSelector(".resplash-btn.resplash-btn_primary.resplash-btn_mailbox-big.svelte-1y37831")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		//driver.get(loginPage);
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("iframe-0-2-16")));
		//WebElement fr = driver.findElement(By.className("iframe-0-2-16"));
		//driver.switchTo().frame(fr);
		//driver.findElement(By.name("username")).sendKeys(ConfProperties.getProperty("user"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".base-0-2-79.primary-0-2-93")));
		driver.findElement(By.cssSelector(".base-0-2-79.primary-0-2-93")).click();
		//driver.findElement(By.id("password")).sendKeys(ConfProperties.getProperty("password"));
		
		//wait.until(ExpectedConditions.elementToBeClickable(By.className("VfPpkd-Jh9lGc")));
		//driver.findElement(By.cssSelector(".VfPpkd-LgbsSe.VfPpkd-LgbsSe-OWXEXe-k8QpJ.VfPpkd-LgbsSe-OWXEXe-dgl2Hf.nCP5yc.AjY5Oe.DuMIQc.LQeN7.qIypjc.TrZEUc.lw1w4b")).click();
		/*
		Assertions.assertEquals(startPage, driver.getCurrentUrl());
		Assertions.assertEquals(userName, driver.findElement(By.cssSelector(".avatar.avatar-small.circle")).getAttribute("alt").substring(1));
		
		driver.quit();
		*/
	}
	
	@Test 
	public void negativeSignIn() {
		System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
		driver  = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		driver.get(ConfProperties.getProperty("startpage"));
		String loginPage = driver.findElement(By.linkText("Sign in")).getAttribute("href");
		driver.findElement(By.linkText("Sign in")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get(loginPage);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".auth-form-body.mt-3")));
		driver.findElement(By.id("login_field")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.cssSelector(".btn.btn-primary.btn-block.js-sign-in-button")).click();
				
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("js-flash-alert")));
		System.out.println(driver.findElement(By.className("js-flash-alert")).getText());
		Assertions.assertEquals("Incorrect username or password.", driver.findElement(By.className("js-flash-alert")).getText());
		
		driver.quit();
	}
	
	
	
	@Test 
	public void negativeSearch() {
		System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
		driver  = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		driver.get(ConfProperties.getProperty("startpage"));
		UUID uuid = UUID.randomUUID();
		driver.findElement(By.cssSelector(".form-control.js-site-search-focus.header-search-input.jump-to-field.js-jump-to-field")).sendKeys(uuid.toString()+Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("blankslate")));
		
		Assertions.assertEquals("We couldn’t find any repositories matching '" + uuid+ "'", driver.findElement(By.xpath("/html/body/div[1]/div[4]/main/div/div[3]/div/div/h3")).getText());
		driver.quit();
	}
	
	@Test 
	public void search() {
		System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
		driver  = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		driver.get(ConfProperties.getProperty("startpage"));
		driver.findElement(By.cssSelector(".form-control.js-site-search-focus.header-search-input.jump-to-field.js-jump-to-field")).sendKeys("calculator"+Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("repo-list")));
		
		Assertions.assertTrue(driver.findElements(By.cssSelector(".repo-list-item.hx_hit-repo.d-flex.flex-justify-start.py-4.public.source")).size()>0);
		driver.quit();
	}
	
	@Test 
	public void searchInRepo() {
		System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
		driver  = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		driver.get(ConfProperties.getProperty("startpage"));
		driver.findElement(By.cssSelector(".form-control.js-site-search-focus.header-search-input.jump-to-field.js-jump-to-field")).sendKeys("ChaykinaEkaterina/FSM"+Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("repo-list")));
		
		Assertions.assertTrue(driver.findElements(By.cssSelector(".repo-list-item.hx_hit-repo.d-flex.flex-justify-start.py-4.public.source")).size()==1);
		driver.findElement(By.cssSelector(".f4.text-normal")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"repo-content-pjax-container\"]/div/div/div[3]/div[1]/div[1]/a")));
		driver.findElement(By.xpath("//*[@id=\"repo-content-pjax-container\"]/div/div/div[3]/div[1]/div[1]/a")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tree-finder-results")));
		driver.findElement(By.cssSelector(".form-control.tree-finder-input.js-tree-finder-field.ml-1.flex-1")).sendKeys("FSM"+Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tree-browser-result.no-underline.p-1.py-2.border-bottom.d-block")));
		driver.findElement(By.cssSelector(".form-control.tree-finder-input.js-tree-finder-field.ml-1.flex-1")).sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("blob-path")));
		Assertions.assertEquals("FSM.java", driver.findElement(By.className("final-path")).getText());
	}
	
	
	@Test 
	public void createRepo() {
		System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
		driver  = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		driver.get(ConfProperties.getProperty("startpage"));
		String loginPage = driver.findElement(By.linkText("Sign in")).getAttribute("href");
		driver.findElement(By.linkText("Sign in")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get(loginPage);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".auth-form-body.mt-3")));
		driver.findElement(By.id("login_field")).sendKeys(ConfProperties.getProperty("user"));
		driver.findElement(By.id("password")).sendKeys(ConfProperties.getProperty("password"));
		driver.findElement(By.cssSelector(".btn.btn-primary.btn-block.js-sign-in-button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new_repository")));
		driver.findElement(By.cssSelector(".form-control.input-sm.flex-auto")).sendKeys("test");
		driver.findElement(By.cssSelector(".btn.btn-sm.btn-primary.btn.mb-2")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".url.fn")));
		driver.findElement(By.cssSelector(".url.fn")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[5]/main/div[1]/div/div/div[2]/div/nav/a[2]")));
		driver.findElement(By.xpath("/html/body/div[1]/div[5]/main/div[1]/div/div/div[2]/div/nav/a[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-repositories-list")));
		Assertions.assertEquals("test", driver.findElements(By.xpath("//*[@id=\"user-repositories-list\"]/ul/li[1]/div[1]/div[1]/h3/a")).get(0).getText());
		
	}
	
	@Test 
	public void uploadFile() {
		System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
		driver  = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		driver.get(ConfProperties.getProperty("startpage"));
		String loginPage = driver.findElement(By.linkText("Sign in")).getAttribute("href");
		driver.findElement(By.linkText("Sign in")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get(loginPage);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".auth-form-body.mt-3")));
		driver.findElement(By.id("login_field")).sendKeys(ConfProperties.getProperty("user"));
		driver.findElement(By.id("password")).sendKeys(ConfProperties.getProperty("password"));
		driver.findElement(By.cssSelector(".btn.btn-primary.btn-block.js-sign-in-button")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".details-overlay.details-reset.js-feature-preview-indicator-container")));
		driver.findElement(By.cssSelector(".details-overlay.details-reset.js-feature-preview-indicator-container")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/header/div[7]/details/details-menu/a[2]")));
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div[7]/details/details-menu/a[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-repositories-list")));
		driver.findElement(By.xpath("// a[contains(text(), 'test')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("// a[contains(text(), 'uploading an existing file')]")));
		driver.findElement(By.xpath("// a[contains(text(), 'uploading an existing file')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type=file]")));
		File file = new File("./src/resources/upload.txt");
		driver.findElement(By.cssSelector("input[type=file]")).sendKeys(file.getAbsolutePath());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("js-manifest-file-list-root")));
		driver.findElement(By.cssSelector(".js-blob-submit.btn-primary.btn")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".css-truncate.css-truncate-target.d-block.width-fit")));
		ArrayList<WebElement> files = new ArrayList<WebElement>(driver.findElements(By.cssSelector(".Box-row.Box-row--focus-gray.py-2.d-flex.position-relative.js-navigation-item")));
		Assertions.assertEquals(1, files.size());
		Assertions.assertEquals("upload.txt", driver.findElements(By.cssSelector(".js-navigation-open.Link--primary")).get(0).getText());
		
		
	}
    
	
}
