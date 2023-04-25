package utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Recipe {
	public String method;
	public String recURL;
	public String recID;
	public String recName;
	public String prepTime;
	public String cookTime;
	public String ingredient;
	public String nutrientvalues;
	public String morbidCondition;
	public String foodCate;
	public WebDriver driver;
	public Recipe(WebDriver driver, int j) {
		super();
		this.driver = driver;
		setCookTime();
		//setFoodCate();
		setIngredient();
		setMethod();
		setMorbidCondition();
		setNutrientvalues();
		setPrepTime();
		setRecID(driver, j);
		setRecName(driver, j);
		setRecURL();
	}
	public void setMethod() {
		try {
		this.method = driver.findElement(By.xpath("//div[@id='recipe_small_steps']")).getText();
		System.out.println("method" +this.method);
		}catch(Exception e) {
			this.method = "";
		}
	}

	public void setRecURL() {
		try {
		this.recURL =driver.getCurrentUrl();
		}catch(Exception e) {
			this.recURL = "";
		}
	}

	public void setRecID(WebDriver driver, int j) {
		try {
			    this.recID = driver.findElement(By.xpath("//article["+j+"]/div[2]/span")).getText();
			}catch(Exception e) {
				this.recID = "12";
			}
	}

	public void setRecName(WebDriver driver, int j) {
		try {
		    this.recName = driver.findElement(By.xpath("//h1/span/span")).getText();

		}catch(Exception e) {
			this.recName = "hi";
		}
	}

	public void setPrepTime() {
		try {
		    this.prepTime = driver.findElement(By.xpath("//time[@itemprop='prepTime']")).getText();
		}catch(Exception e) {
			this.prepTime = "";
		}
	}

	public void setCookTime() {
		try {
		    this.cookTime = driver.findElement(By.xpath("//time[@itemprop='cookTime']")).getText();
		}catch(Exception e) {
			this.cookTime = "";
		}
	}

	public void setIngredient() {
		try {
		    this.ingredient = driver.findElements(By.xpath("//div[@id='rcpinglist']")).get(0).getText();
		    System.out.println("Ing:" +this.ingredient);
		}catch(Exception e) {
			this.ingredient = "";
		}
	}

	public void setNutrientvalues() {
		try {
		    this.nutrientvalues = driver.findElement(By.xpath("//span[@itemprop='nutrition']")).getText();
		    System.out.println("nutrientvalues" +this.nutrientvalues);
		}catch(Exception e) {
			this.nutrientvalues = "";
		}
	}

	public void setMorbidCondition() {
		try {
		    this.morbidCondition = driver.findElement(By.xpath("//span[@id='ctl00_cntleftpanel_lblSearchTerm']//span//h1")).getText();
		}catch(Exception e) {
			this.morbidCondition = "Hypertension";
		}
	}

//	public void setFoodCate() {
//		try {
//		    this.foodCate = driver.findElement(By.linkText("recipes-for-Course-Veg-Course-140")).getText();
//		}catch(Exception e) {
//			this.foodCate = "Veg";
//		}
//	}
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

}
