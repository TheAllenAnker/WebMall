package com.allenanker.webstore.utils;

import java.util.UUID;

public class UUIDUtils {
    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }


    public static String getUUID64() {
        return getId() + getId();
    }

    /**
     * Generate random code for activation of account.
     *
     * @return activation code
     */
    public static String getCode() {
        return getId();
    }
}
