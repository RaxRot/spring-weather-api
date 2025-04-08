package com.raxrot.weather.utility;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtility {

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.isEmpty()) {
            ip=request.getRemoteAddr();
        }
        log.info("IP Address: " + ip);
        return ip;
    }
}
