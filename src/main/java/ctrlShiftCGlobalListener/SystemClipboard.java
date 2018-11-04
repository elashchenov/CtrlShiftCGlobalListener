package ctrlShiftCGlobalListener;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class SystemClipboard {

    public static void copySelectedText() throws AWTException {
        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_C);
    }

    public static String getText() throws IOException, UnsupportedFlavorException {
        Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        if (systemClipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
            Object text = systemClipboard.getData(DataFlavor.stringFlavor);
            return (String) text;
        }

        return null;
    }

}
