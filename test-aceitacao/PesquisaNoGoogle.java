

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PesquisaNoGoogle {

	public static void main(String[] args) {

		// abre firefox
		WebDriver driver = new FirefoxDriver();

		// acessa o site do google
		driver.get("http://www.google.com.br/");

		// digita no campo com nome "q" do google
		WebElement campoDeTexto = driver.findElement(By.name("q"));
		campoDeTexto.sendKeys("triadworks");

		// submete o form
		campoDeTexto.submit();
		
		driver.close();
	}
}
