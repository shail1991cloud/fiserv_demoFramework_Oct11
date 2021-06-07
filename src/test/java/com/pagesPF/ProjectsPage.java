package com.pagesPF;

import com.cucumber.listener.Reporter;
import com.helperUtilities.Constant;
import com.helperUtilities.DatesHelper;
import com.helperUtilities.EnvSetUp;
import com.managersUtilities.CommonFunction;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class ProjectsPage {

    WebDriver driver;
    public Functions_LeanPageObject functions_leanPageObject;

    public ProjectsPage(WebDriver driver) {
        this.driver = driver;
        functions_leanPageObject = PageFactory.initElements(driver, Functions_LeanPageObject.class);

    }

    @FindBy(how = How.XPATH, using = "//*[@id=\"create-project-btn\"]")
    public WebElement createProjectButton;

    @FindBy(how = How.XPATH, using = "//*[text()=' Create project ']")
    public WebElement createProjectButtonWhenNoProject;

    @FindBy(how = How.XPATH, using = "//*[@id=\"input-text-field0\"]")
    WebElement projectNameTextField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"input-text-field0\"]")
    WebElement projectSettingUpModelPanel;

    @FindBy(how = How.XPATH, using = "//*[@id=\"input-textarea0\"]")
    WebElement projectDescriptionTextField;

    @FindBy(how = How.XPATH, using = "//*[@id=\"input-text-field1\"]")
    WebElement projectTag;

    @FindBy(how = How.XPATH, using = "//*[@id=\"input-dropdown0\"]")
    WebElement dropDown_Engines;

    @FindBy(how = How.XPATH, using = "//*[@id=\"create-btn\"]")
    public WebElement buttonCreateOnProjectSettingPopUp;

    @FindBy(how = How.XPATH, using = "//*[@id=\"cancel-btn\"]")
    public WebElement buttonCancelOnProjectSettingPopUp;

    @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]//p[3]/button")
    WebElement deleteButtonOnProjectListingPage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div[1]/div/div[2]/div/div/scale-modal/scale-button[1]")
    WebElement cancelButtonOnDeletePopUp;


    @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]//p[3]/button")
    List<WebElement> deleteButtonsOnProjectListingPage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]//div[2]/div/div/div[1]/div[1]/div[1]")
    WebElement project_Name;

    @FindBy(how = How.XPATH, using = "//*[text()=' Delete ']")
    WebElement deleteButtonOnDeleteProjectPopUp;

    @FindBy(how = How.XPATH, using = "//*[@id=\"project-dashboard-header\"]/div[1]")
    WebElement optionToSortByStatus;

    @FindBy(how = How.XPATH, using = "//*[@placeholder=\"Search\"]")
    WebElement searchBoxOnProjectListingPage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"projectName\"]")
    WebElement existingProjectOnProjectListingPage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"projectName\"]")
    WebElement projectStatusOnProjectListingPage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"projectTag\"]")
    WebElement projectTagOnProjectListingPage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"engineId\"]/p/span")
    WebElement projectEngineOnProjectListingPage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]//div[5]//p[1]")
    WebElement projectRunOnProjectListingPage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]//div[5]//p[2]")
    WebElement projectCopyOnProjectListingPage;


    @FindBy(how = How.XPATH, using = "//*[@id=\"projectName\"]")
    List<WebElement> createdProjectsOnProjectListingPage;

    public final String customisedProjectOnProjectListingPage = "//*[text()=' %s ']";
    public final String customisedCreateProjectTab = "//*[text()=' %s ']";

    String projectJsPath = "return document.querySelector(\"#header\").shadowRoot.querySelector(\"div > scale-app-header > header > nav.header__nav > div > div.header__nav-menu-wrapper > div.header__nav-menu-main > ul > scale-nav-main:nth-child(1) > li > a > span.main-navigation__item-link-text\")";


    public void enterDetailsInProjectSetting() {
        createProjectButton.click();
    }


    public void validateProjectSettingPopUp() {
        CommonFunction.waitForElementToAppear(driver, projectSettingUpModelPanel);
        Assert.assertTrue(projectSettingUpModelPanel.isDisplayed());
    }


    public void validateAllFieldsOnProjectSettingPopUp() {
        CommonFunction.waitForElementToAppear(driver, projectNameTextField);
        Assert.assertTrue(projectNameTextField.isDisplayed());
        CommonFunction.waitForElementToAppear(driver, projectTag);
        Assert.assertTrue(projectTag.isDisplayed());
        CommonFunction.waitForElementToAppear(driver, dropDown_Engines);
        CommonFunction.scrollToElement(driver, dropDown_Engines);
        Assert.assertTrue(dropDown_Engines.isDisplayed());
        Assert.assertTrue(buttonCreateOnProjectSettingPopUp.isDisplayed());
        Assert.assertTrue(buttonCancelOnProjectSettingPopUp.isDisplayed());

    }

    public void pickExistingProjectOrCreateNew(String name, String description, String tag) throws InterruptedException {
        if (createdProjectsOnProjectListingPage.size() > 0) {
            String existingProjectName = createdProjectsOnProjectListingPage.get(1).getText();
            CommonFunction.waitForElementToAppear(driver, searchBoxOnProjectListingPage);
            searchBoxOnProjectListingPage.sendKeys(existingProjectName);
            Assert.assertTrue(existingProjectOnProjectListingPage.isDisplayed());


        } else {
            CommonFunction.waitForElementToAppear(driver, createProjectButtonWhenNoProject);
            CommonFunction.scrollToElement(driver, createProjectButtonWhenNoProject);
            createProjectButtonWhenNoProject.click();
            enterDetailsIntoCreateProjectPopUp(name, description, tag);
            buttonCreateOnProjectSettingPopUp.click();
            validateProjectName(name, tag);
        }
    }

    public void enterDetailsIntoCreateProjectPopUp(String name, String description, String tag) throws InterruptedException {
        CommonFunction.waitForElementToAppear(driver, projectNameTextField);
        String uniqueProjectName = CommonFunction.generateRandomString(5) + name + DatesHelper.getTodayDateWithSeconds();
        EnvSetUp.setDataValue(Constant.ProjectName, uniqueProjectName);
        projectNameTextField.sendKeys(uniqueProjectName);
        projectDescriptionTextField.sendKeys(description);
        CommonFunction.waitForElementToAppear(driver, projectTag);
        projectTag.sendKeys(tag);
        projectTag.sendKeys(Keys.ENTER);
        CommonFunction.waitForElementToAppear(driver, dropDown_Engines);
        CommonFunction.scrollToElement(driver, dropDown_Engines);
        CommonFunction.selectFromDropDownWithValue(dropDown_Engines, "1");

    }

    public void validateProjectRecord(String projectName) throws InterruptedException {
        Assert.assertTrue(existingProjectOnProjectListingPage.isDisplayed());
        Assert.assertTrue(projectStatusOnProjectListingPage.isDisplayed());
        Assert.assertTrue(projectTagOnProjectListingPage.isDisplayed());
        Assert.assertTrue(projectEngineOnProjectListingPage.isDisplayed());
        Assert.assertTrue(projectCopyOnProjectListingPage.isDisplayed());
        Assert.assertTrue(projectRunOnProjectListingPage.isDisplayed());
        Assert.assertTrue(deleteButtonOnProjectListingPage.isDisplayed());
    }


    public void validateProjectName(String projectName, String tag) throws InterruptedException {

        CommonFunction.clickOnShadowElement(driver, projectJsPath);
        CommonFunction.waitForElementToAppear(driver, searchBoxOnProjectListingPage);
        searchBoxOnProjectListingPage.sendKeys(EnvSetUp.getDataKeyValue(Constant.ProjectName));
        CommonFunction.waitForSomeTime();
        Assert.assertTrue(CommonFunction.getCustomisedWebElement(driver, customisedProjectOnProjectListingPage, EnvSetUp.getDataKeyValue(Constant.ProjectName)).isDisplayed());
        String tagNameOnProjectListingPage = projectTagOnProjectListingPage.getText();
        Assert.assertEquals(tag, tagNameOnProjectListingPage);

    }

    public void navigateToProjectListing() throws InterruptedException {

        CommonFunction.clickOnShadowElement(driver, projectJsPath);
        CommonFunction.waitForElementToAppear(driver, searchBoxOnProjectListingPage);
        searchBoxOnProjectListingPage.sendKeys(EnvSetUp.getDataKeyValue(Constant.ProjectName));
        CommonFunction.waitForMinimalTime();
        CommonFunction.getCustomisedWebElement(driver, customisedProjectOnProjectListingPage, EnvSetUp.getDataKeyValue(Constant.ProjectName)).click();


    }


    public void validateProjectNameNotOnProjectListingPage(String projectName) throws InterruptedException {

        CommonFunction.waitForElementToAppear(driver, searchBoxOnProjectListingPage);
        searchBoxOnProjectListingPage.sendKeys(EnvSetUp.getDataKeyValue(Constant.ProjectName));
        CommonFunction.waitForSomeTime();
        validatePresenceOfElement();

    }

    public void validatePresenceOfElement() {
        if (CommonFunction.getCustomisedWebElements(driver, customisedProjectOnProjectListingPage, EnvSetUp.getDataKeyValue(Constant.ProjectName)).size() > 0) {
            Assert.fail("Project is Present on Project Listing page");
        } else {
            Reporter.addStepLog("Element is not present on Project listing page");
        }
    }

    public void deleteProjectOnProjectListingPage() throws InterruptedException {

        CommonFunction.clickByHoveringMouse(driver, deleteButtonOnProjectListingPage);
        CommonFunction.waitForSomeTime();
        CommonFunction.clickForceFully(driver, deleteButtonOnDeleteProjectPopUp);
        CommonFunction.waitForMinimalTime();
        deleteButtonOnDeleteProjectPopUp.click();

    }


    public void deleteAllProjectsOnProjectListingPage() {


        for (WebElement dele : deleteButtonsOnProjectListingPage) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                CommonFunction.waitForElementToAppear(driver, createProjectButton);
                dele.click();
                CommonFunction.waitForElementToAppear(driver, deleteButtonOnDeleteProjectPopUp);
                deleteButtonOnDeleteProjectPopUp.click();
                CommonFunction.waitForSomeTime();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }



/*
		for(int i=0;i<deleteButtonsOnProjectListingPage.size();i++)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			deleteButtonsOnProjectListingPage.get(i).click();
			Commonfunction.waitForElementToAppear(driver,deleteButtonOnDeleteProjectPopUp);
			deleteButtonOnDeleteProjectPopUp.click();
			//Commonfunction.scrollToElement(driver,deleteButtonsOnProjectListingPage.get(i-1));

		}*/


    }

}
