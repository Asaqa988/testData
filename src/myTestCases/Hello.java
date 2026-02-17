package myTestCases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Hello extends TestData {



	@BeforeTest

	public void myBeforeTest() throws SQLException {

		driver.get(MyWebSite);
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "abed");
		
		myname="mahmmoud"; 

	}

	@Test(priority = 1, enabled = false)

	public void myTestToAddData() throws SQLException {

		stmt = con.createStatement();

		String query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) VALUES (9991, 'Raghad Obidat Trading', 'Obidat', 'Raghad', '+962-7-9000-1234', 'Al Rabieh Street', NULL, 'Amman', NULL, '11118', 'Jordan', 1370, 75000.00);";

		stmt.executeUpdate(query);
	}

	@Test(priority = 2)
	public void ReadData() throws SQLException {
		stmt = con.createStatement();

		String Query = "select * from customers";
		rs = stmt.executeQuery(Query);

		while (rs.next()) {
			firstname = rs.getString("customerName");
			customerID = rs.getString("customerNumber");
			customerPhone = rs.getString("phone");
			lastname = rs.getString("contactLastName");

		

		}
		
		System.out.println(firstname);
System.out.println(myname);

	}

	@Test(priority = 3, enabled = true)
	public void SignupWithDataBase() throws InterruptedException {
		String TheEmail = firstname + lastname + randomEmailNumber + randomEmailNumber2 + "@gmail.com";

		System.out.println(TheEmail);

		WebElement LoginAndSignUpButton = driver.findElement(By.linkText("Login or register"));
		LoginAndSignUpButton.click();

		// to press on countinue button

		WebElement CountinueButtonBeforeSignupPage = driver.findElement(By.xpath("//button[@title='Continue']"));
		CountinueButtonBeforeSignupPage.click();

		// ---------------- you are inside the signup page -----------------

		// elements

		WebElement FirstNameInputField = driver.findElement(By.id("AccountFrm_firstname"));
		WebElement LastNameInputField = driver.findElement(By.id("AccountFrm_lastname"));
		WebElement EmailInput = driver.findElement(By.id("AccountFrm_email"));
		WebElement AddressInput = driver.findElement(By.id("AccountFrm_address_1"));
		WebElement CountryDropDown = driver.findElement(By.id("AccountFrm_country_id"));
		WebElement StateDropDown = driver.findElement(By.id("AccountFrm_zone_id"));
		WebElement CityInput = driver.findElement(By.id("AccountFrm_city"));
		WebElement PostalInput = driver.findElement(By.id("AccountFrm_postcode"));
		WebElement LoginNameInput = driver.findElement(By.id("AccountFrm_loginname"));
		WebElement PasswordInput = driver.findElement(By.id("AccountFrm_password"));
		WebElement ConfirmPasswordInput = driver.findElement(By.id("AccountFrm_confirm"));
		WebElement ConditionsAndTermsCheckbox = driver.findElement(By.id("AccountFrm_agree"));
		WebElement CountinueButton = driver.findElement(By.cssSelector(".btn.btn-orange.pull-right.lock-on-click"));

// Actions 

		FirstNameInputField.sendKeys(firstname);
		LastNameInputField.sendKeys(lastname);
		EmailInput.sendKeys(TheEmail);
		AddressInput.sendKeys("Amman");
		Select CountrySelect = new Select(CountryDropDown);
		CountrySelect.selectByValue("108");
		Thread.sleep(3000);
		int randomState = rand.nextInt(StateDropDown.findElements(By.tagName("option")).size());
		Select SelectforStateDropDown = new Select(StateDropDown);
		SelectforStateDropDown.selectByIndex(randomState);
		CityInput.sendKeys("RandomCity");
		PostalInput.sendKeys(customerPhone);

		ConditionsAndTermsCheckbox.click();

// Assertion 

//		Assert.assertEquals(driver.getCurrentUrl().contains("success"), true);
//		Assert.assertEquals(driver.getPageSource().contains("Congratulations"), true);
//		WebElement WelcomeMessageArea = driver.findElement(By.id("customernav"));
//		Assert.assertEquals(WelcomeMessageArea.getText().contains(randomFirstName), true);

	}
	
	@Test(priority = 4,enabled = false)
	public void myTestToupdateData() throws SQLException {

		stmt = con.createStatement();

		String query = "UPDATE customers SET customerName = 'dana and raghad' WHERE customerNumber = 9991";

		stmt.executeUpdate(query);
	}
	
	@Test(priority = 5,enabled = false)
	public void myTestTodeleteData() throws SQLException, InterruptedException {

		stmt = con.createStatement();

		String query = "delete from customers where customerNumber = 9991";

		stmt.executeUpdate(query);
		
		Thread.sleep(5000);
		
		System.out.println(firstname);
	}

}
