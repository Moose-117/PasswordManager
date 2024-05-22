package com.mycompany.passwordmanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		File file = null;
		String password = null;
		Scanner scanner = new Scanner(System.in);
		Boolean isMasterPasswordCorretta = Boolean.FALSE;
		Integer counter = 0;
		String masterPasswordCercata = null;
		String processoDaEseguire = null;

		File fileMasterPasswordCercata;
		try {
			fileMasterPasswordCercata = GestoreFile.cercaFileMasterPassword();
			FileReader fr = new FileReader(fileMasterPasswordCercata);
			BufferedReader br = new BufferedReader(fr);
			masterPasswordCercata = br.readLine();
			processoDaEseguire = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("BENVENUTO NEL PASSWORD MANAGER, INSERISCI LA PASSWORD PER ACCEDERE AL TOOL\n\n");

		do {
			String masterPasswordDaCercare = scanner.nextLine();
			counter++;

			if (counter == 10 && masterPasswordDaCercare.equals(masterPasswordCercata)
					&& CheckProcess.isProcessRunning(processoDaEseguire)) {
				isMasterPasswordCorretta = true;
			}
		} while (!isMasterPasswordCorretta);
		try {
			file = GestoreFile.cercaFilePassword();
		} catch (IOException e) {
			System.out.println("errore nel recupero del file da chiavetta USB");
		}
		while (true) {
			System.out.println("BENVENUTO NEL PASSWORD MANAGER\n\n");
			System.out.println("SELEZIONA:\n");
			System.out.println("1) PER LEGGERE UN SERVIZIO ESISTENTE:\n");
			System.out.println("2) PER AGGIUNGERE UN NUOVO SERVIZIO:\n");

			switch (scanner.nextLine()) {
			case "1": {
				System.out.println("Digita il nome del servizio che stai cercando");
				String servizioCercato = scanner.nextLine();

				// Recupera la password del servizio cercato
				ServizioRecuperatoDto servizio = GestisciServizi.recuperaServizio(servizioCercato, file);

				if (servizio == null || servizio.getPasswordServizio() == null) {
					System.out.println("Non esiste ancora il servizio che hai indicato");
				} else {
					try {
						System.out.println(servizio.getPasswordServizio());
						servizio.cleanServiceDto();
					} catch (Exception e) {
						System.err.println("ERRORE");
					}
				}
				break;
			}
			case "2": {
				try {
					file = GestisciServizi.aggiungiServizio(file);
				} catch (NoSuchAlgorithmException ex) {
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
				}
				break;
			}
			}
		}
	}

}
