package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;

import static sample.Logic.SourceCode;
import static sample.Logic.checkInternetConnection;


public class ControllerInterface {

    @FXML
    public static TextArea t;

    @FXML
    public TextField InputString;

    @FXML
    public Label weatherRegion,WeatherTemperature,WeatherUpdated,WeatherCloud,WeatherWind,WeatherRain,WeatherWet,DayTime;




    public String Reg,time,Temperature,Cloud,Wind,Rain,Wet,TimeOfDay;


    public boolean nameOfRegionFlag;

 ////////////////////////////
    @FXML
    public void inputData(TransformerException e) throws IOException, Exception {

        boolean nameOfRegionFlag = false;
        String regions[]= {"baranovichi","bobruisk","borisov","bragin","brest","vasilevichi","verkhnedvinsk","vileika","vitebsk","volkovysk",
                "gomel","gorki","grodno","dokshitsy","zhitkovichi","zhlobin","ivatsevichi","klichev","kostiukovichi","lelchitsy",
                "lepel","lida","lyntupy","marina_gorka","minsk","mogilev","mozyr","novogrudok","orsha","pinsk",
                "polotsk","senno","slavgorodslutsk","stolbtsy","sharkovschina"};

        String s = InputString.getText();

    int count = regions.length;

try {

    for (int i = 0; i < count; i++) {
        if (regions[i].equals(s))
        {
            nameOfRegionFlag = true;
        }}}
catch (Exception a) {
    e.printStackTrace();

    System.out.println("Incorrect Name of Region!!!");
}
}


    @FXML
    private void Confirm() throws IOException, SAXException, ParserConfigurationException {
boolean connectionFlag = false;

            try {

                checkInternetConnection();
                connectionFlag=true;
            } catch (Exception e) {
                e.printStackTrace();

                System.out.println("No Connection!!!");
            }


if(connectionFlag==true) {
    String town = InputString.getText();
    String LINE = null;


    System.out.println(town);

    String XML = "http://www.nepogoda.ru/europe/belarus/" + town + "/forecast.xml";
    String code = "windows-1251";

    try {
        LINE = SourceCode(XML, code);
    } catch (
            Exception e
            ) {
        e.printStackTrace();
        System.out.println("LINE Error!!!");
    }

    System.out.println(LINE);

    DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
    f.setValidating(false);

    DocumentBuilder builder = f.newDocumentBuilder();

    InputSource is = new InputSource(new StringReader(LINE));

    Document doc = builder.parse(is);


    XPath xpath = XPathFactory.newInstance().newXPath();

    String regionName = "/weather/point/point_name";
    String regionS = null;
    try {
        regionS = (String) xpath.evaluate(regionName, doc, XPathConstants.STRING);
        System.out.println(regionS);
    } catch (XPathExpressionException e) {
        e.printStackTrace();
    }
    Reg = regionS;
    weatherRegion.setText(Reg);


    String updateTime = "/weather/point/timestep/datetime";
    String update_time = null;
    try {
        update_time = (String) xpath.evaluate(updateTime, doc, XPathConstants.STRING);
        System.out.println(update_time);
    } catch (XPathExpressionException e) {
        e.printStackTrace();
    }
    time = update_time;
    WeatherUpdated.setText("След.обноление:"+time);


    String temperature = "/weather/point/timestep/temperature_interval";
    String Tempr = null;
    try {
        Tempr = (String) xpath.evaluate(temperature, doc, XPathConstants.STRING);
        System.out.println(Tempr);
    } catch (XPathExpressionException e) {
        e.printStackTrace();
    }
    Temperature = Tempr;
    WeatherTemperature.setText(Temperature);


    String cloud = "/weather/point/timestep/cloud_text";
    String Cld = null;
    try {
        Cld = (String) xpath.evaluate(cloud, doc, XPathConstants.STRING);
        System.out.println(Cld);
    } catch (XPathExpressionException e) {
        e.printStackTrace();
    }
    Cloud = Cld;
    WeatherCloud.setText(Cloud);


    String wind = "/weather/point/timestep/wind_text";
    String Wnd = null;
    try {
        Wnd = (String) xpath.evaluate(wind, doc, XPathConstants.STRING);
        System.out.println(Wnd);
    } catch (XPathExpressionException e) {
        e.printStackTrace();
    }
    Wind = Wnd;
    WeatherWind.setText(Wind);


    String rain = "/weather/point/timestep/precipitation_text";
    String Rn = null;
    try {
        Rn = (String) xpath.evaluate(rain, doc, XPathConstants.STRING);
        System.out.println(Rn);
    } catch (XPathExpressionException e) {
        e.printStackTrace();
    }
    Rain = Rn;
    WeatherRain.setText(Rain);


    String wet = "/weather/point/timestep/humidity";
    String Wt = null;
    try {
        Wt = (String) xpath.evaluate(wet, doc, XPathConstants.STRING);
        System.out.println(Wt);
    } catch (XPathExpressionException e) {
        e.printStackTrace();
    }
    Wet = Wt;
    WeatherWet.setText(Wet + "%");


    String dtime = "/weather/point/timestep/daypartname";
    String dayTime = null;
    try {
        dayTime = (String) xpath.evaluate(dtime, doc, XPathConstants.STRING);
        System.out.println(dayTime);
    } catch (XPathExpressionException e) {
        e.printStackTrace();
    }
    TimeOfDay = dayTime;
    DayTime.setText(TimeOfDay);
}
        else
    System.out.println("ERROR!!!");
    }
}

