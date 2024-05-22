package com.mycompany.passwordmanager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestoreFile {

	public static File cercaFilePassword() throws IOException {
		String percorso = "D:\\\\Servizi_nuovo.txt";
		Path path = Paths.get(percorso);

		if (Files.exists(path)) {
			return path.toFile();
		}

		throw new IOException();
	}
	
	public static File cercaFileMasterPassword() throws IOException {
		String percorso = "D:\\\\MasterPassword.txt";
		Path path = Paths.get(percorso);

		if (Files.exists(path)) {
			return path.toFile();
		}

		throw new IOException();
	}
	
	
}
