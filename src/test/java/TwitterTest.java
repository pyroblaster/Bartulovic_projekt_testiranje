import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TwitterTest {

    public WebDriver webDriver;

    public String twitterURL = "http://www.twitter.com";
    public String ELONMUSK_URL = "https://twitter.com/elonmusk";

    public String username = "***";
    public String password = "***";
    public String phoneNumber = "***";

    double random = Math.random() * 250000 + 1;

@BeforeMethod
public void setupTest() {

    System.setProperty("webdriver.chrome.driver", "C:\\Testiranje\\chromedriver.exe" );

    webDriver = new ChromeDriver();

    webDriver.navigate().to(twitterURL);
}

    @Test
    public void testLogin() throws InterruptedException {
        WebElement usernameTextBox = webDriver.findElement(By.xpath("/html//div[@id='doc']/div[@class='StaticLoggedOutHomePage']//form[@action='https://twitter.com/sessions']//input[@name='session[username_or_email]']"));
        usernameTextBox.sendKeys(username); //unosenje naziva racuna
        Thread.sleep(1750); //izbjegavanje zakljucavanja racuna
        WebElement passwordTextBox = webDriver.findElement(By.xpath("/html//div[@id='doc']/div[@class='StaticLoggedOutHomePage']//form[@action='https://twitter.com/sessions']//input[@name='session[password]']"));
        passwordTextBox.sendKeys(password); //unosenje lozinke racuna
        Thread.sleep(1750); //izbjegavanje zakljucavanja racuna
        WebElement logIn = webDriver.findElement(By.xpath("/html//div[@id='doc']//form[@action='https://twitter.com/sessions']/input[@value='Log in']"));
        logIn.click();  //logiranje
        Thread.sleep(1750); //izbjegavanje zakljucavanja racuna
        WebElement phoneNumberBox = webDriver.findElement(By.xpath("//*[@id=\"challenge_response\"]"));
        phoneNumberBox.sendKeys(phoneNumber); // slanje broja mobitela radi potvrde
        webDriver.findElement(By.xpath("//*[@id=\"email_challenge_submit\"]")).click();
        //kasnije moguce prebaciti u @BeforeMethod kako se ne bi morala svaki put zvati klasa testLogin()
    }

    @Test
    public void testTweet() throws InterruptedException {
            testLogin(); // logiranje

        WebElement tweetTextBox = webDriver.findElement((By.xpath("/html//div[@id='tweet-box-home-timeline']")));
        tweetTextBox.sendKeys("Alea iacta est!" + " " + random); //upisivanje teksta
        WebElement sendTweetBox = webDriver.findElement((By.xpath("/html//div[@id='timeline']//form[@action='//upload.twitter.com/i/tweet/create_with_media.iframe']//div[@class='TweetBoxToolbar-tweetButton tweet-button']/button[@type='button']/span[@class='button-text tweeting-text']")));
        sendTweetBox.click(); //slanje teksta
        Thread.sleep(7500);
    }

    @Test
    public void testPoll() throws  InterruptedException{
        testLogin();

        WebElement tweetTextBox = webDriver.findElement((By.xpath("/html//div[@id='tweet-box-home-timeline']")));
        tweetTextBox.click();
        WebElement tweetPollBox = webDriver.findElement(By.xpath("/html//div[@id='timeline']/div[@class='timeline-tweet-box']//form[@action='//upload.twitter.com/i/tweet/create_with_media.iframe']//div[@class='PollCreator']/button[@type='button']"));
        tweetPollBox.click(); // prelazak na anketiranje
        Thread.sleep(1750);
        WebElement questionPollBox = webDriver.findElement(By.xpath("/html//div[@id='tweet-box-home-timeline']"));
        questionPollBox.sendKeys("Como estas, mi amigo?" + " " + random); //postavljanje pitanja
        Thread.sleep(1750);
        WebElement choice1PollBox = webDriver.findElement(By.xpath("//*[@id=\"timeline\"]/div[2]/div/form/div[2]/div[4]/div[3]/div/div[1]/div[1]"));
        choice1PollBox.sendKeys("Muy bien."); //unosenje prvog odgovora
        Thread.sleep(1750);
        WebElement choice2PollBox = webDriver.findElement(By.xpath("//*[@id=\"timeline\"]/div[2]/div/form/div[2]/div[4]/div[3]/div/div[2]/div[1]"));
        choice2PollBox.sendKeys("Excelente."); //unosenje drugog odgovora
        Thread.sleep(1750);
        WebElement sendTweetBox = webDriver.findElement((By.xpath("/html//div[@id='timeline']//form[@action='//upload.twitter.com/i/tweet/create_with_media.iframe']//div[@class='TweetBoxToolbar-tweetButton tweet-button']/button[@type='button']/span[@class='button-text tweeting-text']")));
        sendTweetBox.click(); //slanje ankete
        Thread.sleep(7500);
        webDriver.quit();
    }

    @Test
    public void testLogout() throws  InterruptedException {
        testLogin(); // logiranje

        WebElement userSettings = webDriver.findElement(By.xpath("/html//a[@id='user-dropdown-toggle']"));
        userSettings.click(); //ulazak na postavke racuna
        Thread.sleep(1750);
        WebElement logoutButton = webDriver.findElement(By.xpath("//li[@id='signout-button']/button[@role='menuitem']"));
        logoutButton.click(); //odjava korisnika
        Thread.sleep(7500);
        webDriver.quit();
    }

    @Test
    public void testSearch() throws  InterruptedException {
        testLogin(); // logiranje

        WebElement searchBar = webDriver.findElement(By.xpath("/html//input[@id='search-query']"));
        searchBar.sendKeys("Elon Musk"); //unosenje pojma u trazilicu
        WebElement searchButton = webDriver.findElement(By.xpath("//form[@id='global-nav-search']//button[@type='submit']"));
        searchButton.click(); //pretrazivanje
        Thread.sleep(1500);
        webDriver.quit();
    }

    @Test void testTweetsAndReplies() throws  InterruptedException {
        testSearch(); // logiranje

        WebElement tweetsAndReplies = webDriver.findElement(By.xpath("//*[@id=\"page-container\"]/div[2]/div/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[2]"));
        tweetsAndReplies.click(); //prijelaz na twittove i odgovore
        Thread.sleep(1750);
        WebElement media = webDriver.findElement(By.xpath("//*[@id=\"page-container\"]/div[2]/div/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[3]"));
        media.click(); //prijelaz na multimedijalne twittove
        Thread.sleep(7500);
        webDriver.quit();
    }

    @Test void testMessage() throws InterruptedException {
        testLogin(); // logiranje

        WebElement message = webDriver.findElement(By.xpath("//*[@id=\"global-actions\"]/li[3]/a"));
        message.click(); //prijelaz na poruke
    }

    @Test void testNotifications() throws  InterruptedException{
        testLogin(); // logiranje

        WebElement notifications = webDriver.findElement(By.xpath("//*[@id=\"global-actions\"]/li[2]/a"));
        notifications.click(); //prijelaz na obavjesti
    }



}