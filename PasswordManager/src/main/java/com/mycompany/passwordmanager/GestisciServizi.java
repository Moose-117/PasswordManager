package com.mycompany.passwordmanager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.DecoderException;

public class GestisciServizi {

    public static void aggiungiServizio() throws NoSuchAlgorithmException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Servizi_nuovo.txt", true))) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Inserisci il nome del servizio:");
            String nomeServizio = scanner.nextLine();
            System.out.println("Inserisci la password associata al servizio:");
            String password = scanner.nextLine();

            byte[] bytedServizio = nomeServizio.getBytes(StandardCharsets.UTF_8);
            byte[] bytedPassword = password.getBytes(StandardCharsets.UTF_8);

            byte[] salt = CriptaDecripta.generateSalt();
            byte[] servizioCriptato = CriptaDecripta.cripta(bytedServizio, salt);
            byte[] passwordCriptata = CriptaDecripta.cripta(bytedPassword, salt);

            String servizioEncoded = Base64.toBase64String(servizioCriptato);
            String passwordEncoded = Base64.toBase64String(passwordCriptata);
            String saltEncoded = Base64.toBase64String(salt);

            bufferedWriter.write(servizioEncoded + "," + passwordEncoded + "," + saltEncoded);
            bufferedWriter.newLine();

            System.out.println("Servizio aggiunto correttamente.");
        } catch (IOException e) {
            System.out.println("Si è verificato un errore: " + e.getMessage());
        }
    }

    public static ServizioRecuperatoDto recuperaServizio(String servizioCercato) throws NoSuchAlgorithmException {

        try (BufferedReader reader = new BufferedReader(new FileReader("Servizi_nuovo.txt"))) {

            ServizioRecuperatoDto servizioRecuperato = new ServizioRecuperatoDto();
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] parti = linea.split(",");
                if (parti.length == 3) {
                    try {
                        byte[] servizioCrittato = Base64.decode(parti[0].trim());
                        byte[] passwordCrittata = Base64.decode(parti[1].trim());
                        byte[] salt = Base64.decode(parti[2].trim());

                        byte[] servizioDecriptato = CriptaDecripta.decripta(servizioCrittato, salt);
                        servizioRecuperato.setNomeServizio(new String(servizioDecriptato, StandardCharsets.UTF_8));

                        byte[] passwordDecriptata = CriptaDecripta.decripta(passwordCrittata, salt);
                        servizioRecuperato.setPasswordServizio(new String(passwordDecriptata, StandardCharsets.UTF_8));

                        servizioRecuperato.setSaltServizio(Base64.toBase64String(salt));

                        if (servizioRecuperato.getNomeServizio().equalsIgnoreCase(servizioCercato)) {
                            return servizioRecuperato;
                        } else {
                            servizioRecuperato.cleanServiceDto();
                        }
                    } catch (DecoderException e) {
                        // Gestione dell'eccezione di decodifica
                        System.err.println("Errore durante la decodifica della stringa Base64: " + e.getMessage());
                    }
                }
            }
            return null;
        } catch (IOException ex) {
            Logger.getLogger(GestisciServizi.class.getName()).log(Level.SEVERE, null, ex);
            return null; // Gestione dell'eccezione di lettura del file
        }
    }
}
