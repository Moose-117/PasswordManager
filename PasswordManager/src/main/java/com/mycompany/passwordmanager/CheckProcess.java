package com.mycompany.passwordmanager;

/**
 *
 * @author marco
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CheckProcess {

    public static boolean isProcessRunning(String processName) {
        try {
            Process process = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(processName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
