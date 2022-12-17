package lab4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

// Класс содержит локацию элементов страницы и методы взаимодействия с ними
public class LogInPage {

    public WebDriver driver;

    //Конструктор обращается к классу PageFactory, чтобы заработала аннотация @FindBy
    //Он инициализирует элементы страницы
    public LogInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    
    //Фрейм формы входа
    @FindBy(className = "ag-popup__frame__layout__iframe")
    private WebElement loginFrame;

    //Поле ввода логина
    @FindBy(name = "username")
    private WebElement loginField;

    // Галочка "Запомнить"
    @FindBy(xpath = "//span[text()='запомнить']")
    private WebElement checkBox;

    // Кнопка "Ввести пароль"
    @FindBy(xpath = "//span[text()='Ввести пароль']")
    private WebElement buttonPsw;

    // Поле ввода пароля
    @FindBy(name = "password")
    private WebElement pswField;

    // Кнопка входа на главной странице 
    @FindBy(xpath = "//button[text()='Войти']")
    private  WebElement buttonIn;
    
    // Кнопка входа на форме входа
    @FindBy(xpath = "//span[text()='Войти']")
    private  WebElement buttonInFrom;

    // Кнопка Х на "Загрузка фотографии и создание подписи"
    @FindBy(xpath = "//div[contains(@class,'ph-project-promo-close-icon__container svelte-m7oyyo')]")
    private WebElement btnX;

    // Методы работы с элементами
    
    // Переключение на фрейм с формой входа
    public void switchFrame(){
        driver.switchTo().frame(loginFrame);
    }

    // Ввод логина
    public void inputLogin(String login){
        loginField.click();
        loginField.sendKeys(login);
    }

    //Снять галочку "Запомнить"
    public  void notRemember() {
        checkBox.click();
    }

    // Переход на ввод пароля
    public void goPswInput(){
        buttonPsw.click();
    }

    //Ввод пароля
    public void inputPsw(String psw) {
        pswField.click();
        pswField.sendKeys(psw);
    }

    //нажать кнопку вход
    public void pressLogInBtn(){
        buttonIn.click();
    }
    
    //войти на почту после ввода данных в форму
    public void goToMail(){
        buttonInFrom.click();
    }

    // Нажать кнопку Х
    public void downWinFoto() { btnX.click();}
}