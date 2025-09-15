package trade.tradenotifier.service;

import com.bybit.api.client.config.BybitApiConfig;
import com.bybit.api.client.domain.CategoryType;
import com.bybit.api.client.domain.trade.request.TradeOrderRequest;
import com.bybit.api.client.restApi.BybitApiTradeRestClient;
import com.bybit.api.client.service.BybitApiClientFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;
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
    @Value("${bybit.api.key}")
    private String apiKey;
    @Value("${bybit.secret.api}")
    private String apiSecret;
    private final ObjectMapper objectMapper;

    public Set<SingleOrderResponseDto> getSpotOrders() {
        var ordersResult = getClientFactory().getOpenOrders(
                TradeOrderRequest
                        .builder()
                        .category(CategoryType.SPOT)
                        .build());
        return parseOrdersFromResponse(ordersResult);
    }

    public Set<SingleOrderResponseDto> getInverseOrders() {
        var ordersResult = getClientFactory().getOpenOrders(
                TradeOrderRequest
                        .builder()
                        .category(CategoryType.INVERSE)
                        .build());
        return parseOrdersFromResponse(ordersResult);
    }

    public Set<SingleOrderResponseDto> getLinearOrders() {
        var ordersResult = getClientFactory().getOpenOrders(
                TradeOrderRequest
                        .builder()
                        .category(CategoryType.LINEAR)
                        .settleCoin("USDT")
                        .build());
        return parseOrdersFromResponse(ordersResult);
    }

    private Set<SingleOrderResponseDto> parseOrdersFromResponse(Object ordersResult) {
        try {
            String jsonResponse = objectMapper.writeValueAsString(ordersResult);
            log.info("JSON Response: {}", jsonResponse);
            OrderResponseWrapper result =
                    objectMapper.readValue(jsonResponse, OrderResponseWrapper.class);
            return result.getResult().getOrders();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while working with jsonResponse occurred", e);
        }
    }

    private BybitApiTradeRestClient getClientFactory() {
        return BybitApiClientFactory
                .newInstance(apiKey, apiSecret, BybitApiConfig.MAINNET_DOMAIN)
                .newTradeRestClient();
    }
}
