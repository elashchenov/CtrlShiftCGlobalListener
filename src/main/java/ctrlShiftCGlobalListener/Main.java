package ctrlShiftCGlobalListener;

import java.util.logging.Level;


public class Main {

    public static void main(String[] args) {
        try {
            CtrlShiftCGlobalListener.setLogsLevel(Level.OFF);
            CtrlShiftCGlobalListener.start();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

}
