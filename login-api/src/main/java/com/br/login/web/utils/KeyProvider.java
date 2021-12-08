package com.br.login.web.utils;

import java.util.UUID;

public class KeyProvider {

    public static String createKey() {

        return UUID.randomUUID().toString().replace("-", "");

    }

}
