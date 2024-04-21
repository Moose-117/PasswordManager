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
            // Esegui il comando per ottenere l'elenco dei processi in esecuzione
            Process process = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            // Leggi l'output del comando riga per riga
            while ((line = reader.readLine()) != null) {
                // Controlla se il nome del processo corrisponde al programma specificato
                if (line.contains(processName)) {
                    return true; // Il processo è in esecuzione
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Il processo non è in esecuzione
    }
}
