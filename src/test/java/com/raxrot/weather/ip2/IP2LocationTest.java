package com.raxrot.weather.ip2;

import com.ip2location.IP2Location;
import com.ip2location.IPResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IP2LocationTest {

    private String DBPath="src/main/resources/ip2locdb/IP2LOCATION-LITE-DB3.BIN";

    @DisplayName("test invalid IP")
    @Test
    public void testInvalidIP() throws IOException {
        IP2Location ip2Locator = new IP2Location();
        ip2Locator.Open(DBPath);

        String ipAddress="abc";
        IPResult ipResult=ip2Locator.IPQuery(ipAddress);

        assertThat(ipResult.getStatus()).isEqualTo("INVALID_IP_ADDRESS");
    }

    @DisplayName("test valid IP1")
    @Test
    public void testValidIP1() throws IOException {
        IP2Location ip2Locator = new IP2Location();
        ip2Locator.Open(DBPath);

        String ipAddress="108.30.178.78";//NY
        IPResult ipResult=ip2Locator.IPQuery(ipAddress);

        assertThat(ipResult.getStatus()).isEqualTo("OK");
        assertThat(ipResult.getCity()).isEqualTo("New York City");
    }

    @DisplayName("test valid IP2")
    @Test
    public void testValidIP2() throws IOException {
        IP2Location ip2Locator = new IP2Location();
        ip2Locator.Open(DBPath);

        String ipAddress="103.48.198.141";//Delhi
        IPResult ipResult=ip2Locator.IPQuery(ipAddress);

        assertThat(ipResult.getStatus()).isEqualTo("OK");
        assertThat(ipResult.getCity()).isEqualTo("Delhi");
    }
}
