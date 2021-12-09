package com.hancom.authserver.util;

import java.util.UUID;

public class UuidUtil {
    public static String createUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
