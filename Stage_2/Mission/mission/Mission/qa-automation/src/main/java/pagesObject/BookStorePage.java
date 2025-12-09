package pagesObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BookStorePage extends BasePage {

    public BookStorePage(WebDriver driver) { super(driver); }

    // ------------------------------
    //  Localizadores Login (sin By.id/ By.name)
    // ------------------------------
    private final By usernameInput = By.xpath("//input[@type='text']");
    private final By passwordInput = By.xpath("//input[@type='password']");
    private final By loginBtn = By.xpath("//button[normalize-space()='Login']");
    private final By profileUserNameValue = By.xpath("//label[normalize-space()='User Name :']/following::label[1]");

    // ==============================
    //  Acciones Login
    // ==============================
    public void typeUsername(String username) {
        type(usernameInput, username);
    }

    public void typePassword(String password) {
        type(passwordInput, password);
    }

    public void clickLogin() {
        click(loginBtn);
    }

    public void login(String username, String password) {
        typeUsername(username);
        typePassword(password);
        clickLogin();
    }

    public String getProfileUserName() {
        return getText(profileUserNameValue);
    }

    // ------------------------------
    //  Localizadores Books catálogo (sin By.id/ By.name)
    // ------------------------------
    private By searchInput = By.xpath("//input[@type='text' and @placeholder='Type to search']");
    private By bookTitleLinks = By.xpath("//div[contains(@class,'rt-tbody')]//a");

    // ==============================
    //  Acciones Books catálogo
    // ==============================
    public void searchBook(String term) {
        type(searchInput, term);
    }

    public List<String> getVisibleBookTitles() {
        List<WebElement> bookTitles = findAll(bookTitleLinks);
        List<String> titles = new ArrayList<>();

        for (WebElement bookTitle : bookTitles) {
            titles.add(bookTitle.getText().trim());
        }
        return titles;
    }

    // ------------------------------
    //  Localizadores Books catálogo (one book) (sin By.id/ By.name)
    // ------------------------------
    private final By firstBookTitleLink = By.xpath("(//div[contains(@class,'rt-tbody')]//a)[1]");
    private By detailTitleValue = By.xpath("//label[normalize-space()='Title :']/following::label[1]");
    private By detailAuthorValue = By.xpath("//label[normalize-space()='Author :']/following::label[1]");

    // ==============================
    //  Acciones Books catálogo (one book)
    // ==============================

    public String getFirstBookTitle() {
        return getText(firstBookTitleLink);
    }

    public String getDetailTitle() {
        return getText(detailTitleValue);
    }

    public String getDetailAuthor() {
        return getText(detailAuthorValue);
    }

    public void clickOnFirstBookTitle() {
        click(firstBookTitleLink);
    }

    // ------------------------------
    //  Localizadores Books catálogo (profile + logout) (sin By.id/ By.name)
    // ------------------------------
    private final By profileBooksTable = By.xpath("//div[contains(@class,'rt-table')]");
    private final By profileBookRows = By.xpath("//div[contains(@class,'rt-tbody')]//div[contains(@class,'rt-tr-group')]");
    private final By profileNoRowsLabel = By.xpath("//div[contains(@class,'rt-noData') and contains(normalize-space(),'No rows found')]");
    private final By logoutBtn = By.xpath("//button[normalize-space()='Log out']");

    // ==============================
    //  Acciones Books catálogo (profile + logout)
    // ==============================
    public boolean isProfileTableVisible() {
        return waitForVisibility(profileBooksTable).isDisplayed();
    }

    public void clickOnLogout() {
        click(logoutBtn);
    }

    public boolean isLoginBtnVisible() {
        return waitForVisibility(loginBtn).isDisplayed();
    }
}
