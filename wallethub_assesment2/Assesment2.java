package wallethub_assesment2;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;

import utility.ReusableMethods;

public class Assesment2 {
	static String url = "http://wallethub.com/profile/test_insurance_company/";
	static String reviewsField = "cssSelector__span.nav-number:nth-of-type(1)";
	static String rating_stars = "xpath__(//div[@class='rating-box-wrapper'])[3]";
	static String dropdown = "xpath__(//span[@class='dropdown-placeholder'])[2]";
	static String health_insurance_value = "xpath__//li[text()='Health Insurance']";
	static String textfield_write_review = "xpath__//div[@class='android']/textarea";
	static String btn_submit = "xpath__//*[text()='Submit']";
	static String star_element = null;
	static WebElement star_webelement = null;
	

	public static void main(String[] args) throws Exception {
		ReusableMethods.setup();
		ReusableMethods.enter_url(url);
		ReusableMethods.click_element(reviewsField);
		System.out.println("Click on the Reviews tab");
		mouse_over_andClick_stars();
		select_value_from_dropdown();
		write_review();
		
		
		

	}
	
	public static void mouse_over_andClick_stars() throws Exception{

		for(int i=11;i<15;i++){
			star_element="xpath__(//*[@class='rvs-star-svg'])["+i+"]";
			star_webelement = ReusableMethods.find_element(star_element);
			Thread.sleep(5000);
			ReusableMethods.mouse_hover_element(star_webelement);
			System.out.println("mouse hover on the star ");
			if(i==14){
				ReusableMethods.click_element(star_element);	
			}
		}
	}
	
	public static void select_value_from_dropdown() throws Exception{
		ReusableMethods.click_element(dropdown);
		ReusableMethods.click_element(health_insurance_value);
		
	}
	
	
	public static void write_review() throws Exception{
		   HashMap<String, String> mapp = null;
		   mapp = ReusableMethods.readdata_excel();
		   ReusableMethods.set_text(textfield_write_review, mapp.get("TextArea"));
		   Thread.sleep(5000);
		   ReusableMethods.click_element(btn_submit);
		 }
	
	
	
	
	

}
