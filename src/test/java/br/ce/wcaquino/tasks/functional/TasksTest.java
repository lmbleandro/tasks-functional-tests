package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogCombiner;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
//		WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://10.151.83.139:4444/wd/hub"), cap);
		driver.navigate().to("http://10.151.83.127:8001/tasks");	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;		
	}
	@Test
	public void DeveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {	
			
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
		
			//escrver dscrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			
			//escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2031");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
			
		} finally {
			
			//fechar 0 browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {	
			
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();		
			
			//escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2031");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
			
		} finally {
			
			//fechar 0 browser
			driver.quit();
		}
	}
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {	
			
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
		
			//escrver dscrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
			
		} finally {
			
			//fechar 0 browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {	
			
			//clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
		
			//escrver dscrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			
			//escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
			
		} finally {
			
			//fechar 0 browser
			driver.quit();
		}
	}
}
