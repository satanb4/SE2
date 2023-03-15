

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//class definition for training
public class training {
    //private member variable to store type of training
    private String type;
    // private member variable to store duration of training
    private int duration;
    //constructor for training class
    public training(String type, int duration) {
        this.type = type;
        this.duration = duration;
    }
    // method to get and set type of training
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
