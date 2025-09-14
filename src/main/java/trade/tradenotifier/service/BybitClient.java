package trade.tradenotifier.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Set;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import trade.tradenotifier.dto.external.OrderResponseWrapper;
import trade.tradenotifier.dto.external.SingleOrderResponseDto;

@Component
@RequiredArgsConstructor
@Slf4j
public class BybitClient {
    private static final String SPOT_ORDERS_URL =
            "https://api.bybit.com/v5/order/realtime?category=spot";
    private static final String HMAC_SHA256 = "HmacSHA256";
    private static final long RECV_WINDOW = 10000; // 5 seconds
    @Value("${bybit.api.key}")
    private String apiKey;
    @Value("${bybit.secret.api}")
    private String apiSecret;
    private final ObjectMapper objectMapper;

    public Set<SingleOrderResponseDto> getSpotOrders() {
        long timestamp = Instant.now().toEpochMilli();
        String queryString = "category=spot";
        String signaturePayload = timestamp + apiKey + RECV_WINDOW + queryString;
        String signature = generateSignature(signaturePayload, apiSecret);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(SPOT_ORDERS_URL))
                .header("X-BAPI-API-KEY", apiKey)
                .header("X-BAPI-SIGN", signature)
                .header("X-BAPI-TIMESTAMP", String.valueOf(timestamp))
                .header("X-BAPI-RECV-WINDOW", String.valueOf(RECV_WINDOW))
                .header("Content-Type", "application/json")
                .build();

        try {
            HttpResponse<String> response =
                    httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            OrderResponseWrapper result =
                    objectMapper.readValue(response.body(), OrderResponseWrapper.class);
            log.info("Received order response: {}", result);
            return result.getOrdersAndCategory().getOrders();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateSignature(String data, String secret) {
        try {
            Mac sha256Hmac = Mac.getInstance(HMAC_SHA256);
            SecretKeySpec secretKey =
                    new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
            sha256Hmac.init(secretKey);
            byte[] hash = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to generate signature", e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
