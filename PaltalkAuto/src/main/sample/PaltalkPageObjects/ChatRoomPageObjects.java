package PaltalkPageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChatRoomPageObjects {

	@FindBy(xpath="//a[text()='Chat Rooms']")
	public static WebElement clkChatRoom;
	
	@FindBy(xpath="(//span[text()='Select'])[1]")
    public static WebElement clkLangSelectField;
	
	@FindBy(xpath="/html/body/div[11]/ul/li[4]/div")
	public static WebElement clkBerberLang;
	
	@FindBy(xpath="//div[text()='Radio & TV']")
	public static WebElement clkRadioField;
	
	@FindBy(xpath="//div[text()='XM Radio']")
	public static WebElement clkXMRadio;
	
	@FindBy(xpath="//div[text()='G']")
	public static WebElement clkRatingField;
	
	@FindBy(xpath="//a[text()='Apply']")
	public static WebElement clkApplyBtn;
	
}
