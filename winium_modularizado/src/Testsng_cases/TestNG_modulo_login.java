package Testsng_cases;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.winium.WiniumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import configuration.Init_winiumDriver;
import configuration.DataProviderClass;
import modulos_actions.Login_actions;

public class TestNG_modulo_login {

	public static ExtentReports reports;
	public static ExtentTest logger;
	private WiniumDriver driver;
	long id;
	
	
	
	
	@BeforeClass
	public void SetupEnviroment() {
		
		/*Clase Init_winiumDriver  ejecuta winiumDriver(intermediaro entre TestNg y aplicacion a automatizar)
		tambien solicita URL de  la aplicación que se va a automatizar*/
		Init_winiumDriver setup = new Init_winiumDriver("C:\\Users\\Roy\\Documents\\proyectos visual EXE\\LogIn\\setup.exe");
		this.driver= setup.GetDriver();
		
		id = Thread.currentThread().getId();
		 
	}
	
	
	@BeforeTest
	public void ConfigReport() {
		reports = new ExtentReports("C:\\Extentreports\\ExtentReportF_" + id + ".html", true);
		reports.addSystemInfo("Host Name", "Lisyx").addSystemInfo("Environment", "https://autos.staging.hdi.com.mx")
				.addSystemInfo("User Name", "Lisys");
		reports.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
	}
	
	@Test(dataProvider = "data-provider", dataProviderClass = DataProviderClass.class)
	public void ExecuteTest(HashMap<String, Object> rowMap) {
		
		logger=reports.startTest("Cotizador Autos HDI Chrome " + id + "");
		
		//Login_actions contiene las acciones que se pueden ejecutar el el modulo LogIn
		Login_actions objLogIn = new Login_actions(this.driver,logger);
		
		objLogIn.ExecuteLogIn(rowMap);
		objLogIn.ExecuteLogOut();
	}
	
	
	@AfterTest
	public void CloseTest() {
		//driver.close();
		reports.endTest(logger);
	}
	
	
}
