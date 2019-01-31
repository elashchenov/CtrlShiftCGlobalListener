package ctrlShiftCGlobalListener;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class SystemClipboard {

    static void copySelectedText() {
        try {
            Robot robot = new Robot();
            robot.setAutoDelay(30);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String getText() throws IOException, UnsupportedFlavorException {
        Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (systemClipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
            Object text = systemClipboard.getData(DataFlavor.stringFlavor);
            return (String) text;
        }

        return null;
    }

}
