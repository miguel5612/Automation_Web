package userinterface;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class MeetPage extends PageObject {

    public static final Target MEETINGS_LINK = Target.the("link of the meetings").located(By.xpath("//span[contains(text(), 'Meeting')]"));
    public static final Target MEETINGS_PAGE = Target.the("link of the meetings").located(By.xpath("//span[contains(text(), 'Meetings')]"));
    public static final Target NEW_MEETING_BUTTON = Target.the("button to create a new meeting").located(By.cssSelector("div.tool-button.add-button.icon-tool-button"));
    public static final Target NAME_INPUT = Target.the("where do we write the name of the meeting").located(By.name("MeetingName"));
    public static final Target TYPE_SELECT = Target.the("select the type of meeting").located(By.xpath("//div[@class='select2-container select2-allowclear editor s-Serenity-LookupEditor s-LookupEditor has-inplace-button required']"));
    public static final Target TYPE_WEEKLY = Target.the("select the type of meeting").located(By.xpath("//div[@id='select2-drop']//ul//li[6]"));
    public static final Target NUMBER_INPUT = Target.the("where do we write the number of the new meeting").located(By.id("Serenity_Pro_Meeting_MeetingDialog10_MeetingNumber"));
    public static final Target DATE_START_INPUT = Target.the("where do we write the start date of the new meeting").located(By.id("Serenity_Pro_Meeting_MeetingDialog10_StartDate"));
    public static final Target TIME_START_SELECT = Target.the("select the time when the meeting start").located(By.xpath("//div[@class='field StartDate col-sm-6']//select"));
    public static final Target DATE_END_INPUT = Target.the("where do we write the end date of the new meeting").located(By.id("Serenity_Pro_Meeting_MeetingDialog10_EndDate"));
    public static final Target TIME_END_SELECT = Target.the("select the time when the meeting end").located(By.xpath("//div[@class='field EndDate col-sm-6']//select"));
    public static final Target LOCATION_SELECT = Target.the("select the location of the meeting").located(By.xpath("//div[@class='field LocationId col-sm-6']//div"));
    public static final Target LOCATION_SD = Target.the("select the type of meeting").located(By.xpath("//div[@id='select2-drop']//ul//li[4]"));
    public static final Target UNIT_SELECT = Target.the("select the unit of the meeting").located(By.xpath("//div[@class='field UnitId col-sm-6']//div"));
    public static final Target UNIT_INPUT = Target.the("select the unit of the meeting").located(By.xpath("//div[@id='select2-drop']//div//input"));
    public static final Target UNIT_PRUEBA = Target.the("select the unit of meeting").located(By.xpath("//div[@id='select2-drop']//ul//li[1]"));
    public static final Target ORGANIZED_SELECT = Target.the("select who organized the meeting").located(By.xpath("//div[@class='field OrganizerContactId col-sm-6']//div"));
    public static final Target ORGANIZED = Target.the("select who organized the meeting").located(By.xpath("//div[@id='select2-drop']//ul//li[53]"));
    public static final Target REPORTER_SELECT = Target.the("select who is the reporter of the meeting").located(By.xpath("//div[@class='field ReporterContactId col-sm-6']//div"));
    public static final Target REPORTER = Target.the("select who is the reporter of meeting").located(By.xpath("//div[@id='select2-drop']//ul//li[34]"));
    public static final Target TABLE_MEET_CREATED = Target.the("meet created is exist in the table").located(By.xpath("//a[contains(text(), 'PruebaChoucair')]"));

}
