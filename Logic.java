package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class Logic{

    public static boolean checkInternetConnection() {
        Boolean result = false;
        HttpURLConnection con = null;
            try {
                // HttpURLConnection.setFollowRedirects(false);
                // HttpURLConnection.setInstanceFollowRedirects(false)
                con = (HttpURLConnection) new URL("http://www.nepogoda.ru").openConnection();
                con.setRequestMethod("HEAD");
                result = (con.getResponseCode() == HttpURLConnection.HTTP_OK);
                System.out.println("Connection OK!!");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    try {
                        con.disconnect();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("NO CONECTION!!");
                    }
                }
            }


            return result;
        }


    public static String SourceCode(String pageAddress, String codePage) throws Exception {

        StringBuilder sb = new StringBuilder();
        URL pageURL = new URL(pageAddress);
        URLConnection uc = pageURL.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream(), codePage));
        try {
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
        } finally {
            br.close();
        }
        return sb.toString();
    }


}
