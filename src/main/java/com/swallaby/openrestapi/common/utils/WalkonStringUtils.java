package com.swallaby.openrestapi.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WalkonStringUtils {

    public static String convertUuid(String uuid) {

        StringBuilder sb = new StringBuilder(uuid);
        sb.delete(2, 4);
        return sb.toString();
    }

    public static byte[] hexStringToByteArray(String challengeId) {

        int length = challengeId.length();
        byte[] data = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            data[i / 2] = (byte) ((Character.digit(challengeId.charAt(i), 16) << 4)
                    + Character.digit(challengeId.charAt(i + 1), 16));
        }
        return data;
    }
}
