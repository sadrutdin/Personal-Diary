package com.zaynukov.dev.angeldiary.service.cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

public interface MyCipher {
    String encrypt(String key, String value);

    String decrypt(String key, String value)
            throws BadPaddingException, IllegalBlockSizeException,
            InvalidAlgorithmParameterException, InvalidKeyException;
}
