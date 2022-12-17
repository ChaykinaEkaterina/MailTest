package lab4;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

public class MailPage {

    public WebDriver driver;

    //Конструктор обращается к классу PageFactory, чтобы заработала аннотация @FindBy
    //Он инициализирует поля класса
    public MailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Описание элементов страницы

    //Поле логина
    @FindBy(xpath = "//span[contains(@class, 'ph-project__user-name svelte-1hiqrvn')]")
    private WebElement loginText;

    // Кнопка написать письмо
    @FindBy(xpath = "//span[text()='Написать письмо']")
    private WebElement writeBtn;

    //Поле ввода "Кому"
    @FindBy(xpath = "//input[contains(@class,'container--H9L5q size_s--3_M-_')]")
    List<WebElement> emailInputs;
    //private WebElement fieldTo = emailInputs.get(0);

    //Поле ввода "Тема"    
    //private WebElement fieldTopic = emailInputs.get(1);

    // Кнопка "Отправить"
    @FindBy(xpath = "//span[text()='Отправить']")
    private WebElement sendBtn;
    
    
    //Список отправителей
    @FindBy(xpath = "//span[contains(@class, 'll-crpt')]")
    List<WebElement> mailList;
    
    //Список тем сообщений
    @FindBy(xpath = "//span[contains(@class, 'll-sj__normal')]")
    List<WebElement> topicsList;
    
    //Список флажков
    @FindBy(xpath = "//span[contains(@class, 'll-fs__icon')]")
    List<WebElement> flagIcons;

    // Тело письма
    //@FindBy(xpath = "//body[id = 'tinymce']/br[0]")
    //@FindBy(xpath = "//br")
    @FindBy(xpath = "/html/body/div[1]/div/div[2]/div/div/div/div[2]/div[3]/div[5]/div/div/div[2]/div[1]")
    private WebElement bodyLetter;
    

    //Кнопка "Выход"
    @FindBy(xpath="//*[contains(@class, 'ico ico_16-close ico_size_s')]")
    private WebElement outBtn;
    
    //Кнопка поиска
    @FindBy(xpath="//div[contains(@class, 'search-panel-button js-shortcut')]")
    private WebElement searchBtnBefore;
    
    //Поле поиска
    @FindBy(xpath="//input[contains(@class, 'mail-operands_dynamic-input__input--HbfQR _1XeZVjWI3S6j3SYMYdnVbj')]")
    private WebElement searchInput;
    
    //Поле поиска
    @FindBy(xpath="//span[contains(@class, '_1T55H2cA44INJYueGj74KP')]")
    private WebElement searchIBtnAfter;
    
    //Поле поиска
    @FindBy(xpath="//div[contains(@title, 'Фильтр')]")
    private WebElement FilterBtn;
    
    
    //Очистить поиск
    @FindBy(xpath="//span[contains(@class, 'button2__wrapper button2__wrapper_centered')]")
    private WebElement clearSearchBth;
    
    // Фильтр
    @FindBy(xpath = "//span[contains(@class,'filters-control filters-control_short filters-control_pure')]")
    private WebElement flagBtn;
    
    //Отменить фильтрацию
    @FindBy(xpath="//span[text()='Сбросить всё']")
    private WebElement clearFilterBth;
    
    //Кнопка "Продолжить" (если письмо пустое)
    @FindBy(xpath = "//span[text()='Продолжить']")
    private WebElement nextBtn;
    
    //Чекбоксы для выделения писем 
    @FindBy(xpath = "//input[type='checkbox']")
    List<WebElement> checkboxes;
    
    //Чекбоксы для выделения писем 
    @FindBy(xpath = "//span[contains(@class,'ll-av__img')]")
    List<WebElement> icons;
    
    //Кнопка "Корзина"
    @FindBy(xpath = "//div[text()='Корзина']")
    private WebElement trashBtn;
    
    //Кнопка "Продолжить" (если письмо пустое)
    @FindBy(xpath = "//div[text()='Удалить']")
    private WebElement deleteMailBtn;



    // Методы работы с элементами

    // Прочитать поле логина
    public String getLogin() {
        return loginText.getText();
    }

    //Нажать кнопку "Написать письмо
    public void writeBtnClk(){
        writeBtn.click();
    }

    //Вdод текста в тело письма
    public  void inTextBody(String Text) {
        bodyLetter.click();
        bodyLetter.sendKeys(Text);
    }

    public void  clTextBody(){
        bodyLetter.clear();
    }


    // Нажать кнопку "Отправить"
    public void sendBtnClk() {
        sendBtn.click();
    }

    // Нажать кнопку продолжить (если письмо пустое)
    public  void nextBtnClk(){
        nextBtn.click();
    }

    // Нажатие кнопки "Выход"
    public void outMail() { outBtn.click(); }
    
    
    // Получить отправителя последнего полученного письма
    public  String getLatestSender(){
        return mailList.get(0).getText();
    }
    
    // Получить отправителя последнего полученного письма
    public  String getLatestTopic(){
        return topicsList.get(0).getText();
    }
    
    // Осуществить поиск
    public  void search(String request){
        searchBtnBefore.click();
        searchInput.sendKeys(request);
        searchIBtnAfter.click();
    }
    
    // Нажатие "Очистить поиск"
    public void clearSearch() { clearSearchBth.click(); }
    
    public void filter() {
    	FilterBtn.click();
    	flagBtn.click();
    }
    
    public void clearFilter() {
    	FilterBtn.click();
    	clearFilterBth.click();
    }
    
    public void deleteMail() {
    	deleteMailBtn.click();
    }
    
    public void goToTrashBin() {
    	trashBtn.click();
    }
    
    
}
