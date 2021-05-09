import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import javax.swing.*

import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

JFrame f

WebUI.openBrowser('')

WebUI.navigateToUrl('https://selfregistration.cowin.gov.in/')

startOPT:
WebUI.setText(findTestObject('Object Repository/Page_Co-WIN Application/input_One Time Password_mat-input-0'), '8939758959')

WebUI.click(findTestObject('Object Repository/Page_Co-WIN Application/ion-button_Get OTP'))

f = new JFrame()

String name = JOptionPane.showInputDialog(f, 'Enter OTP')

WebUI.setText(findTestObject('Object Repository/Page_Co-WIN Application/input_OTP Verification_mat-input-1'), name)

WebUI.click(findTestObject('Object Repository/Page_Co-WIN Application/ion-button_Verify  Proceed'))

for (i = 1; i <= 2; i++) {
    personName = WebUI.getText(findTestObject('Page_Co-WIN Application/h3_Bhaskaran ganapathy  REF ID  90452938378_404676', 
            [('index') : i]))

    JOptionPane.showMessageDialog(f, personName)

    if (personName.contains("Bhaskaran Ganapathy")) {
        WebUI.click(findTestObject('Page_Co-WIN Application/span_Schedule', [('index') : i]))
		
		WebUI.waitForElementVisible(findTestObject('Object Repository/Page_Co-WIN Application/ion-button_Schedule Now'),5)
		
        WebUI.click(findTestObject('Object Repository/Page_Co-WIN Application/ion-button_Schedule Now'))

        WebUI.waitForElementVisible(findTestObject('Page_Co-WIN Application/input_Book Appointment for Vaccination_mat-input-2'), 
            8)

        WebUI.setText(findTestObject('Object Repository/Page_Co-WIN Application/input_Book Appointment for Vaccination_mat-input-2'), 
            '560066')
		
		boolean logout = false;
		while (logout = true){
			
			WebUI.click(findTestObject('Object Repository/Page_Co-WIN Application/ion-button_Search'))
			
			WebUI.click(findTestObject('Object Repository/Page_Co-WIN Application/label_Age 18'))
			
			WebDriver driver = DriverFactory.getWebDriver()
			List<WebElement> grid = driver.findElements(By.xpath("//div[@class='vaccine-box vaccine-box1 vaccine-padding']"))
			int rows_count = grid.size()
			JOptionPane.showMessageDialog(f, rows_count)
					
			for (j = 1; j <= rows_count; j++) {
				booking = WebUI.getText(findTestObject('Page_Co-WIN Application/Booking Link', [('index') : j]))
	
				if (!booking.contains("Booked") && !booking.contains("NA")) {
					WebUI.click(findTestObject('Page_Co-WIN Application/Booking Link', [('index') : j]))
					JOptionPane.showMessageDialog(f, "SUCCESSFUL YOUR SLOT IS BOOKED")
					break;
				}
			}
			if(WebUI.waitForElementVisible(findTestObject('Object Repository/Page_Co-WIN Application/input_One Time Password_mat-input-0'), 5)){
				logout = true;
				continue startOPT;
			}
		}
        
		break;
    }
}

