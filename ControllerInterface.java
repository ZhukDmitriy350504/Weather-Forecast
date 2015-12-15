package sample;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static sample.Logic.SourceCode;


public class ControllerInterface {

    @FXML
    public TextField InputString;

    @FXML
    public Label weatherRegion,WeatherTemperature,WeatherUpdated,WeatherCloud,WeatherWind,WeatherRain,WeatherWet,DayTime,ErrorLabel,ErrorLabel1,ErrorLabel11;
    @FXML
    public ImageView image;

@FXML
public TabPane mainWindow;



    static boolean connectionFlag = false;

    public String Reg,time,Temperature,Cloud,Wind,Rain,Wet,TimeOfDay;


    public boolean nameOfRegionFlag;

 ////////////////////////////
    @FXML
    public void inputData()  {

        boolean nameOfRegionFlag = false;
        String regions[]= {"baranovichi","bobruisk","borisov","bragin","brest","vasilevichi","verkhnedvinsk","vileika","vitebsk","volkovysk",
                "gomel","gorki","grodno","dokshitsy","zhitkovichi","zhlobin","ivatsevichi","klichev","kostiukovichi","lelchitsy",
                "lepel","lida","lyntupy","marina_gorka","minsk","mogilev","mozyr","novogrudok","orsha","pinsk",
                "polotsk","senno","slavgorodslutsk","stolbtsy","sharkovschina"};


        String s = InputString.getText();

    int count = regions.length;
    boolean a = false;
        System.out.println(regions.length);
        System.out.println("aloha");

    for (int i = 0; i < count; i++)
    {
        if (regions[i].equals(s)) {
            nameOfRegionFlag = true;
            a=true;
        }
    }

        if(a==false){checkingErrors.errorLabelSet(2,ErrorLabel,ErrorLabel1,ErrorLabel11);return;}
        else checkingErrors.errorLabelDrop(2,ErrorLabel,ErrorLabel1,ErrorLabel11);
    }


    @FXML
    private void Confirm() throws IOException, SAXException, ParserConfigurationException {



            try {
                checkInternetConnection();

                //checkingErrors.errorLabelDrop(1,ErrorLabel);
            } catch (Exception e) {
                e.printStackTrace();
                //checkingErrors.errorLabelSet(1,ErrorLabel);
                System.out.println("No Connection!!!");
            }
        inputData();

if(connectionFlag==true) {
    String town = InputString.getText();
    String LINE = null;


    System.out.println(town);

    String XML = "http://www.nepogoda.ru/europe/belarus/" + town + "/forecast.xml";
    String code = "windows-1251";

    try {
        LINE = SourceCode(XML, code);
    } catch (Exception e) { return;
    }

    System.out.println(LINE);

    DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
    f.setValidating(false);

    DocumentBuilder builder = f.newDocumentBuilder();
    InputSource is = new InputSource(new StringReader(LINE));

    Document doc = builder.parse(is);
    //if(builder.parse(is).get)

    XPath xpath = XPathFactory.newInstance().newXPath();

    String regionName = "/weather/point/point_name";
    String regionS = null;
    try {
        regionS = (String) xpath.evaluate(regionName, doc, XPathConstants.STRING);
        System.out.println(regionS);
    } catch (XPathExpressionException e) {
       return;
    }
    Reg = regionS;
    weatherRegion.setText(Reg);


    String updateTime = "/weather/point/timestep/datetime";
    String update_time = null;
    try {
        update_time = (String) xpath.evaluate(updateTime, doc, XPathConstants.STRING);
        System.out.println(update_time);
    } catch (XPathExpressionException e) {
        return;
    }
    time = update_time;
    WeatherUpdated.setText(time);


    String temperature = "/weather/point/timestep/temperature_interval";
    String Tempr = null;
    try {
        Tempr = (String) xpath.evaluate(temperature, doc, XPathConstants.STRING);
        System.out.println(Tempr);
    } catch (XPathExpressionException e) {
       return;
    }

    Temperature = Tempr;
    WeatherTemperature.setText(Temperature);


    String cloud = "/weather/point/timestep/cloud_text";
    String Cld = null;
    try {
        Cld = (String) xpath.evaluate(cloud, doc, XPathConstants.STRING);
        System.out.println(Cld);
    } catch (XPathExpressionException e) {
        return;
    }
    Cloud = Cld;
    WeatherCloud.setText(Cloud);


    String wind = "/weather/point/timestep/wind_text";
    String Wnd = null;
    try {
        Wnd = (String) xpath.evaluate(wind, doc, XPathConstants.STRING);
        System.out.println(Wnd);
    } catch (XPathExpressionException e) {
        return;
    }
    Wind = Wnd;
    WeatherWind.setText(Wind);


    String rain = "/weather/point/timestep/precipitation_text";
    String Rn = null;
    try {
        Rn = (String) xpath.evaluate(rain, doc, XPathConstants.STRING);
        System.out.println(Rn);
    } catch (XPathExpressionException e) {
        return;
    }
    Rain = Rn;
    WeatherRain.setText(Rain);


    String wet = "/weather/point/timestep/humidity";
    String Wt = null;
    try {
        Wt = (String) xpath.evaluate(wet, doc, XPathConstants.STRING);
        System.out.println(Wt);
    } catch (XPathExpressionException e) {
        return;
    }
    Wet = Wt;
    WeatherWet.setText(Wet + "%");


    String dtime = "/weather/point/timestep/daypartname";
    String dayTime = null;
    try {
        dayTime = (String) xpath.evaluate(dtime, doc, XPathConstants.STRING);
        System.out.println(dayTime);
    } catch (XPathExpressionException e) {
       return;
    }
    TimeOfDay = dayTime;
    DayTime.setText(TimeOfDay);

    IMAGE_ANIMATION(image);

} else
    System.out.println("ERROR!!!");
    }


    public boolean checkInternetConnection() {
        Boolean result = false;
        HttpURLConnection con = null;
        try {
            // HttpURLConnection.setFollowRedirects(false);
            // HttpURLConnection.setInstanceFollowRedirects(false)
            con = (HttpURLConnection) new URL("http://www.nepogoda.ru").openConnection();
            con.setRequestMethod("HEAD");
            result = (con.getResponseCode() == HttpURLConnection.HTTP_OK);
            System.out.println("Connection OK!!");
            checkingErrors.errorLabelDrop(1, ErrorLabel, ErrorLabel1, ErrorLabel11);
        }
        catch (Exception e) {
            checkingErrors.errorLabelSet(1, ErrorLabel, ErrorLabel1, ErrorLabel11);
        }
        return result;
    }


    public static boolean IMAGE_ANIMATION(ImageView line){
        FadeTransition ft = new FadeTransition(Duration.millis(200), line);
        ft.setToValue(1);
        ft.setAutoReverse(true);
        ft.play();
        return true;
    }
}

