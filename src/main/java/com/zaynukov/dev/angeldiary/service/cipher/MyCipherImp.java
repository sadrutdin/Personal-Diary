package com.zaynukov.dev.angeldiary.service.cipher;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

@Service
class MyCipherImp implements MyCipher {

    private static final String salt = "563cc5fd5fb45321";

    @Override
    public String encrypt(String key, String value) {
        TextEncryptor textEncryptor = Encryptors.delux(key, salt);
        return textEncryptor.encrypt(value);
    }

    @Override
    public String decrypt(String key, String value) {
        TextEncryptor textEncryptor = Encryptors.delux(key, salt);
        return textEncryptor.decrypt(value);
    }

}

