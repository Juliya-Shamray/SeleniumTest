
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class FirstTest {

    WebDriver driver = new ChromeDriver();

    By phoneNumber = By.xpath(" //input[@data-qa-node='phone-number']");
    By amount =By.xpath("//input[@name='amount']");
    By cardFrom =By.xpath("//input[@data-qa-node='numberdebitSource']");
    By expDate = By.xpath("//input[@data-qa-node='expiredebitSource']");
    By cvv = By.xpath("//input[@data-qa-node='cvvdebitSource']");
    By payerName = By.xpath("//input[@data-qa-node='firstNamedebitSource']");
    By payerSurname = By.xpath("//input[@data-qa-node='lastNamedebitSource']");
    By submitBtn = By.xpath("//button[@data-qa-node='submit']");
    By RulesLink = By.xpath("//a[@href='https://privatbank.ua/terms']");
    By purse = By.xpath(" //div[@data-qa-node='debitSourceSource']");
    By phoneCode = By.xpath("//button[@data-qa-node='phone-code']");
    By choosePhoneCode = By.xpath("//div[@data-qa-node='phone-code-list']/div/div/div/div/div[2]/div/input");
    By phoneCodeOption = By.xpath("//button[@data-qa-node='phone-code-option']");
    By amountHotSpot = By.xpath("(//button[@data-qa-node='amount-hot-spot'])[1]");
    By amountHotSpot1 = By.xpath("(//button[@data-qa-node='amount-hot-spot'])[2]");
    By amountHotSpot2 = By.xpath("(//button[@data-qa-node='amount-hot-spot'])[3]");
    By amountHotSpot3 = By.xpath("(//button[@data-qa-node='amount-hot-spot'])[4]");


    By actualPhoneNumber = By.xpath("//div[@data-qa-node='details']");
    By actualCardFrom = By.xpath("//td[@data-qa-node='card']");
    By actualMobileOperator = By.xpath("//span[@data-qa-node='nameB']");
    By actualAmount = By.xpath("//div[@data-qa-node='amount']");
    By actualTitleOfRules =By.xpath("//a[@target='_self' and @href='/main/?lang=uk']");
    By actualPhoneCode = By.xpath("//button[@data-qa-node='phone-code']/div/span/div/div[2]");
    By actualAmountHotSpot = By.xpath("//div[@data-qa-node='total-amount']");
    By actualErrorAmount = By.xpath("//form[@autocomplete='off']/div[2]/div[2]");
    By actualErrorPhoneNumber = By.xpath("//form[@autocomplete='off']/div/div[2]");


    @Test
    void checkAddToBasketMinPaymentSum(){

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://next.privat24.ua/mobile");
        driver.findElement(phoneNumber).sendKeys("957647952");

        WebElement toClear = driver.findElement(amount);
        toClear.sendKeys(Keys.CONTROL + "a");
        toClear.sendKeys(Keys.DELETE);
        toClear.sendKeys("1");

        driver.findElement(cardFrom).sendKeys("4004159115449003");
        driver.findElement(expDate).sendKeys("1225");
        driver.findElement(cvv).sendKeys("888");


        driver.findElement(payerName).sendKeys("Yuliia");
        driver.findElement(payerSurname).sendKeys("Shamrai");
        driver.findElement(submitBtn).submit();

        Assertions.assertEquals("Поповнення телефону. На номер +380957647952",
                driver.findElement(actualPhoneNumber).getText());
        Assertions.assertEquals("4004 **** **** 9003", driver.findElement(actualCardFrom).getText());
        Assertions.assertEquals("Vodafone", driver.findElement(actualMobileOperator).getText());
        Assertions.assertEquals("1 UAH", driver.findElement(actualAmount).getText());

        driver.close();
    }

    @Test
    void checkAddToBasketMaxPaymentSum(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://next.privat24.ua/mobile");
        driver.findElement(phoneNumber).sendKeys("963517269");

        WebElement toClear = driver.findElement(amount);
        toClear.sendKeys(Keys.CONTROL + "a");
        toClear.sendKeys(Keys.DELETE);
        toClear.sendKeys("8000");

        driver.findElement(cardFrom).sendKeys("5309233034765085");
        driver.findElement(expDate).sendKeys("1225");
        driver.findElement(cvv).sendKeys("888");

        driver.findElement(payerName).sendKeys("Yuliia");
        driver.findElement(payerSurname).sendKeys("Shamrai");
        driver.findElement(submitBtn).submit();


        Assertions.assertEquals("Поповнення телефону. На номер +380963517269",
                driver.findElement(actualPhoneNumber).getText());
        Assertions.assertEquals("5309 **** **** 5085", driver.findElement(actualCardFrom).getText());
        Assertions.assertEquals("Kyivstar Ukraine", driver.findElement(actualMobileOperator).getText());
        Assertions.assertEquals("8 000 UAH", driver.findElement(actualAmount).getText());

        driver.close();
    }

    @Test
    void checkConditionsAndRules(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://next.privat24.ua/mobile");
        driver.findElement(RulesLink).click();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        driver.switchTo().frame("frame");

        Assertions.assertEquals("https://privatbank.ua/terms", driver.getCurrentUrl());
        Assertions.assertEquals("Умови та правила", driver.getTitle());
        Assertions.assertEquals("Умови та правила надання банківських послуг", driver.findElement(actualTitleOfRules).getText());

        driver.quit();
    }

    @Test
    void checkLinkMyPurse(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://next.privat24.ua/mobile");
        driver.findElement(purse).click();

        int size = driver.findElements(By.tagName("iframe")).size();
        for(int i=0; i<=size; i++){
            driver.switchTo().frame(i);
            int total=driver.findElements(By.xpath("//form/div/h2")).size();
            System.out.println(total);
            driver.switchTo().defaultContent();}

        driver.switchTo().frame(1);

        Assertions.assertEquals("Вхід у Приват24", driver.findElement(By.xpath("//form/div/h2")).getText());

        driver.quit();

    }

    @Test
    void checkChangeCodePhone(){

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://next.privat24.ua/mobile");
        driver.findElement(phoneCode).click();
        driver.findElement(choosePhoneCode).sendKeys("United Kingdom");
        driver.findElement(phoneCodeOption).click();

        Assertions.assertEquals("+44", driver.findElement(actualPhoneCode).getText());

        driver.close();
    }

    @Test
    void checkAmountHotSpot(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://next.privat24.ua/mobile");
        driver.findElement(amountHotSpot).click();

        Assertions.assertEquals("50 UAH", driver.findElement(actualAmountHotSpot).getText());

        driver.findElement(amountHotSpot1).click();

        Assertions.assertEquals("100 UAH", driver.findElement(actualAmountHotSpot).getText());

        driver.findElement(amountHotSpot2).click();

        Assertions.assertEquals("150 UAH", driver.findElement(actualAmountHotSpot).getText());

        driver.findElement(amountHotSpot3).click();

        Assertions.assertEquals("200 UAH", driver.findElement(actualAmountHotSpot).getText());

        driver.close();
    }

    @Test
    void checkErrorLessMinAmount(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://next.privat24.ua/mobile");
        driver.findElement(phoneNumber).sendKeys("963517269");

        WebElement toClear = driver.findElement(amount);
        toClear.sendKeys(Keys.CONTROL + "a");
        toClear.sendKeys(Keys.DELETE);
        toClear.sendKeys("0.1");

        driver.findElement(cardFrom).sendKeys("5309233034765085");
        driver.findElement(expDate).sendKeys("1225");
        driver.findElement(cvv).sendKeys("888");

        driver.findElement(submitBtn).submit();

        Assertions.assertEquals("Мінімальна сума поповнення 1 UAH",
                driver.findElement(actualErrorAmount).getText());

        driver.close();
    }
@Test
    void checkErrorLessMaxAmount(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://next.privat24.ua/mobile");
        driver.findElement(phoneNumber).sendKeys("963517269");

        WebElement toClear = driver.findElement(amount);
        toClear.sendKeys(Keys.CONTROL + "a");
        toClear.sendKeys(Keys.DELETE);
        toClear.sendKeys("8000.1");

        driver.findElement(cardFrom).sendKeys("5309233034765085");
        driver.findElement(expDate).sendKeys("1225");
        driver.findElement(cvv).sendKeys("888");

        driver.findElement(submitBtn).submit();

        Assertions.assertEquals("Максимальна сума поповнення 8000 UAH",
                driver.findElement(actualErrorAmount).getText());

        driver.close();
    }

    @Test
    void checkErrorPhoneNumber(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://next.privat24.ua/mobile");
        driver.findElement(phoneNumber).sendKeys("95764795");

        WebElement toClear = driver.findElement(amount);
        toClear.sendKeys(Keys.CONTROL + "a");
        toClear.sendKeys(Keys.DELETE);
        toClear.sendKeys("999");

        driver.findElement(cardFrom).sendKeys("5309233034765085");
        driver.findElement(expDate).sendKeys("1225");
        driver.findElement(cvv).sendKeys("888");

        driver.findElement(submitBtn).submit();

        Assertions.assertEquals("Введено некоректне значення",
                driver.findElement(actualErrorPhoneNumber).getText());

        driver.close();
    }
}
