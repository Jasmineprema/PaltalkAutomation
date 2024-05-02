package PaltalkPageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaltalkMembersPageObjects {

	@FindBy(id="header-members")
	public static WebElement clkMembers;
	
	@FindBy(id="nick_search")
	public static WebElement clkSerachbox;
	
	@FindBy(xpath="//input[@value='All Genders']")
	public static WebElement clkGender;
	
}
