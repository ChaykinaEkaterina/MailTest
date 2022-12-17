package lab4;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MailTest {

    public static LogInPage loginPage;
    public static MailPage mailPage;
    public static WebDriver driver;

      @BeforeAll
      static void setup() {
           //определение пути до драйвера и его настройка
  		 System.setProperty("webdriver.edge.driver", ConfProperties.getProperty("edgedriver"));
  		 driver  = new EdgeDriver();
         loginPage = new LogInPage(driver);
         mailPage = new MailPage(driver);
         driver.manage().window().maximize();
         driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
         driver.get(ConfProperties.getProperty("startpage"));
      }

      
      @Test
      public void loginTest() {
    	  loginPage.pressLogInBtn();
    	  // Переключение на фрейм формы
  		  loginPage.switchFrame();
  		  // Ввод логина
    	  loginPage.inputLogin(ConfProperties.getProperty("user"));
          // Снять галочку "запомнить"
          loginPage.notRemember();
          //Нажатие кнопки для перехода на ввод пароля
          loginPage.goPswInput();
          // Ввод пароля
          loginPage.inputPsw(ConfProperties.getProperty("password"));
          // Нажатие кнопки входа
          loginPage.goToMail();
          //Проверка эл.почты пользователя
          driver.get(driver.getCurrentUrl());
          Assertions.assertEquals(ConfProperties.getProperty("user")+"@mail.ru",mailPage.getLogin());
          loginPage.downWinFoto();
      }
      
      @Test
      public void loginNegativeTest() {
    	  loginPage.pressLogInBtn();
    	  // Переключение на фрейм формы
  		  loginPage.switchFrame();
  		  // Ввод логина
    	  loginPage.inputLogin(ConfProperties.getProperty("user"));
          // Снять галочку "запомнить"
          loginPage.notRemember();
          //Нажатие кнопки для перехода на ввод пароля
          loginPage.goPswInput();
          // Ввод пароля
          loginPage.inputPsw("12345679");
          // Нажатие кнопки входа
          loginPage.goToMail();
          //Проверка эл.почты пользователя
          driver.get(driver.getCurrentUrl());
          
          Assertions.assertEquals("Неверный пароль, попробуйте ещё раз", driver.findElement(By.xpath("//div[@data-test-id='password-input-error']")).getText());
      }

      
      @Test
      public void SendMail(){
    	  String name = "Екатерина Чайкина";
    	  String topic = "Тест";
          // Нажать кнопку "Написать письмо"
          mailPage.writeBtnClk();
          //Ввод адреса получателя
          mailPage.emailInputs.get(0).sendKeys(ConfProperties.getProperty("user") + "@mail.ru");
          //Ввод темы письма
          mailPage.emailInputs.get(1).sendKeys("Тест");

          mailPage.clTextBody();
          mailPage.inTextBody("Тут что-то тестируется");
          driver.switchTo().defaultContent();

          //Нажать кнопку "Отправить"
          mailPage.sendBtnClk();

          //Нажатие на кнопку "Выход"
          mailPage.outMail();
          
          try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          //Сравнить отправителя письма со своим именем
          Assertions.assertEquals(name,mailPage.getLatestSender());
          Assertions.assertEquals(topic,mailPage.getLatestTopic());
          
      }
      
      
      @Test
      public void Search() throws InterruptedException{
    	  loginTest();
    	  String request = "orioks";
    	  
    	  mailPage.search(request);
    	  Thread.sleep(2000);
    	  
    	  mailPage.mailList.forEach(topic -> {
    		    assertTrue(topic.getText().contains(request));
    		});
    	  mailPage.clearSearch();
    	  
      }
      
      @Test
      public void Filter() throws InterruptedException{
    	  loginTest();
    	  mailPage.filter();
    	  Thread.sleep(2000);
    	  Assertions.assertEquals(mailPage.flagIcons.size(), mailPage.mailList.size());
    	  mailPage.clearFilter();
      }
      
      @Test
      public void deleteMail() throws InterruptedException{
    	  loginTest();
    	  Actions action = new Actions(driver);
    	  String mailToBeDeleted = mailPage.topicsList.get(0).getText();
    	  action.moveToElement(mailPage.icons.get(0)).click().perform();
    	  mailPage.deleteMail();
    	  mailPage.goToTrashBin();
    	  driver.get(driver.getCurrentUrl());
    	  List<WebElement> trashMails = driver.findElements(By.className("ll-sj__normal"));  
    	  boolean mailFound = false;
    	  for (WebElement mail : trashMails) {
    		  if (mail.getText().equals(mailToBeDeleted)) {
    			  mailFound = true;
    			  break;
    		  }
    	  }
    	  
    	  
    	  Assertions.assertTrue(mailFound);
    	  
      }
      
      /*

      @AfterAll
      public static void toClose() {
         driver.quit();
      }

	*/

}
