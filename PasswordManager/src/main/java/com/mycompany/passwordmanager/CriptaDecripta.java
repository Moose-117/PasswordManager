package com.mycompany.passwordmanager;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import java.util.Random;
import java.util.UUID;

public class CriptaDecripta {

    private static final int ITERATION_COUNT = 10000;
    private static final int KEY_SIZE = 256;

    public static byte[] cripta(byte[] input, byte[] salt) {
        String password = "?Cars_Il_Film_81";

        try {
            PKCS5S2ParametersGenerator generator = new PKCS5S2ParametersGenerator();
            generator.init(PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(password.toCharArray()), salt, ITERATION_COUNT);
            CipherParameters parameters = generator.generateDerivedParameters(KEY_SIZE);

            BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
            cipher.init(true, parameters);

            byte[] output = new byte[cipher.getOutputSize(input.length)];
            int bytesProcessed = cipher.processBytes(input, 0, input.length, output, 0);
            int bytesWritten = cipher.doFinal(output, bytesProcessed);

            byte[] result = new byte[bytesProcessed + bytesWritten];
            System.arraycopy(output, 0, result, 0, result.length);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] decripta(byte[] cipherText, byte[] salt) {
        String password = "?Cars_Il_Film_81";

        try {
            PKCS5S2ParametersGenerator generator = new PKCS5S2ParametersGenerator();
            generator.init(PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(password.toCharArray()), salt, ITERATION_COUNT);
            CipherParameters parameters = generator.generateDerivedParameters(KEY_SIZE);

            BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
            cipher.init(false, parameters);

            byte[] output = new byte[cipher.getOutputSize(cipherText.length)];
            int bytesProcessed = cipher.processBytes(cipherText, 0, cipherText.length, output, 0);
            int bytesWritten = cipher.doFinal(output, bytesProcessed);

            byte[] result = new byte[bytesProcessed + bytesWritten];
            System.arraycopy(output, 0, result, 0, result.length);
            return result;
        } catch (InvalidCipherTextException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] generateSalt() {
        byte[] salt = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        return salt;
    }
}
