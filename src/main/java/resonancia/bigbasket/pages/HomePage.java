package resonancia.bigbasket.pages;

import resonancia.bigbasket.maindata.TestBase;

public class HomePage extends TestBase {
	
	
	
	
	public String validateTitleOfHomepage() {
		return driver.getTitle();
	}
}
