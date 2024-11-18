package com.ziad.orderservice.service;

import com.ziad.orderservice.dto.InventoryResponse;
import com.ziad.orderservice.dto.OrderLineItemsDto;
import com.ziad.orderservice.dto.OrderRequest;
import com.ziad.orderservice.dto.OrderResponse;
import com.ziad.orderservice.model.Order;
import com.ziad.orderservice.model.OrderLineItems;
import com.ziad.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public List<OrderResponse> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::mapOrderResponse).toList();
    }

    private OrderResponse mapOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderLineItemsList(
                        order.getOrderLineItemsList()
                                .stream()
                                .map(this::mapToOrderLineItems)
                                .toList()
                )
                .build();
    }

    private OrderLineItemsDto mapToOrderLineItems(OrderLineItems orderLineItems) {
        return OrderLineItemsDto.builder()
                .id(orderLineItems.getId())
                .price(orderLineItems.getPrice())
                .skuCode(orderLineItems.getSkuCode())
                .quantity(orderLineItems.getQuantity())
                .build();
    }

    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItemsList);

        List<String> skuCodes = order.getOrderLineItemsList()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        //Call Inventory Service, and place order if product is in
        //stock

        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build()
                )
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        if (inventoryResponses == null || inventoryResponses.length == 0)
            throw new IllegalArgumentException("No Products in stock, please try again later");
        boolean allProductsInStock = Arrays.stream(inventoryResponses)
                .allMatch(InventoryResponse::isInStock);

        if (Boolean.TRUE.equals(allProductsInStock)) {
            orderRepository.save(order);
            return "Order Placed Successfully";
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        return new OrderLineItems(
                orderLineItemsDto.getId(),
                orderLineItemsDto.getSkuCode(),
                orderLineItemsDto.getPrice(),
                orderLineItemsDto.getQuantity());
    }
}
