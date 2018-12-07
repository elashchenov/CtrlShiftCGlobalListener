package ctrlShiftCGlobalListener;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlShiftCGlobalListener implements NativeKeyListener {
    private boolean ctrlPressed = false;
    private boolean shiftPressed = false;
    private boolean cPressed = false;
    private boolean hotKeyCaught = false;

    static void start() throws NativeHookException {
        GlobalScreen.addNativeKeyListener(new CtrlShiftCGlobalListener());
        GlobalScreen.registerNativeHook();
    }

    public static void stop() throws NativeHookException {
        GlobalScreen.unregisterNativeHook();
    }

    static void setLogsLevel(Level logsLevel) {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(logsLevel);
        logger.setUseParentHandlers(false);
    }

    public void nativeKeyReleased(NativeKeyEvent nativeEvent) {

        if (nativeEvent.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
            ctrlPressed = false;
        } else if (nativeEvent.getKeyCode() == NativeKeyEvent.VC_SHIFT) {
            shiftPressed = false;
        } else if (nativeEvent.getKeyCode() == NativeKeyEvent.VC_C) {
            cPressed = false;
        }


        if (!ctrlPressed && !shiftPressed && !cPressed && hotKeyCaught) {
            hotKeyCaught = false;
            try {
                SystemClipboard.copySelectedText();
                System.out.println(SystemClipboard.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        if (nativeEvent.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
            ctrlPressed = true;
        } else if (nativeEvent.getKeyCode() == NativeKeyEvent.VC_SHIFT) {
            shiftPressed = true;
        } else {
            cPressed = (nativeEvent.getKeyCode() == NativeKeyEvent.VC_C)
                    && shiftPressed && ctrlPressed;
        }

        if (ctrlPressed && shiftPressed && cPressed) {
            hotKeyCaught = true;
        }
    }

    /**
     * This method is never called. I don't know why...
     */
    public void nativeKeyTyped(NativeKeyEvent nativeEvent) {
        //System.out.println("typed");
    }

}
