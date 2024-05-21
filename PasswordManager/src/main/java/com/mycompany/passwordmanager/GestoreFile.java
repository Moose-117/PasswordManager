package com.mycompany.passwordmanager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestoreFile {

	public static File cercaFile() throws IOException {
		String percorso = "D:\\\\new.txt";
		Path path = Paths.get(percorso);

		if (Files.exists(path)) {
			System.out.println("IL PATH TROVATO E': " + percorso);
			return path.toFile();
		}

		throw new IOException();
	}
}
