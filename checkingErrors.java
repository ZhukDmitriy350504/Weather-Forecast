package sample;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;


public class checkingErrors {

    public static void errorLabelSet(int i,Label label,Label label1,Label label2){
        if(i==1)
            label.setText("can not connect to the server, retry again");
            label.setTextFill(Color.RED);
        if(i==2) {
            label1.setText("can not find this place, check input information");
            label1.setTextFill(Color.RED);
        }
        if(label.getTextFill()==Color.RED||label1.getTextFill()==Color.RED)label2.setTextFill(Color.RED);

    }
    public static void errorLabelDrop(int i,Label label,Label label1,Label label2){
        if(i==1)
        {label.setText("Connection is OK");
            ControllerInterface.connectionFlag=true;
            label.setTextFill(Color.GREEN);
            }
        if(i==2)
        {label1.setText("Place is found");
        label1.setTextFill(Color.GREEN);
            }
        if(label.getTextFill()==Color.GREEN&&label1.getTextFill()==Color.GREEN)label2.setTextFill(Color.GREEN);

    }

    public static boolean checkString(String string){
        try
        {
            Integer.parseInt(string);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }



}