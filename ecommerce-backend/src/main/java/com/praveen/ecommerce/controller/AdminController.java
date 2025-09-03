package com.praveen.ecommerce.controller;

import com.praveen.ecommerce.constants.ApplicationConstants;
import com.praveen.ecommerce.dto.ContactResponseDto;
import com.praveen.ecommerce.dto.OrderResponseDto;
import com.praveen.ecommerce.dto.ResponseDto;
import com.praveen.ecommerce.entity.Order;
import com.praveen.ecommerce.service.IContactService;
import com.praveen.ecommerce.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final IOrderService iOrderService;
    private final IContactService iContactService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponseDto>> getAllPendingOrders() {
        return ResponseEntity.ok().body(iOrderService.getAllPendingOrders());
    }

    @PatchMapping("/orders/confirm/{orderId}")
    public ResponseEntity<ResponseDto> confirmOrder(@PathVariable Long orderId) {iOrderService.updateOrderStatus(orderId, ApplicationConstants.ORDER_STATUS_CONFIRMED);
        return ResponseEntity.ok(
                new ResponseDto("200", "Order #" + orderId + " has been confirmed")
        );
    }

    @PatchMapping("/orders/cancel/{orderId}")
    public ResponseEntity<ResponseDto> cancelOrder(@PathVariable Long orderId) {
        iOrderService.updateOrderStatus(orderId, ApplicationConstants.ORDER_STATUS_CANCELLED);
        return ResponseEntity.ok(
                new ResponseDto("200", "Order #" + orderId + " has been canceled")
        );
    }

    @GetMapping("/messages")
    public ResponseEntity<List<ContactResponseDto>> getAllOpenMessages() {
        return ResponseEntity.ok().body(iContactService.getAllOpenMessages());
    }

    @PatchMapping("/messages/close/{contactId}")
    public ResponseEntity<ResponseDto> closeMessages(@PathVariable Long contactId) {
        iContactService.updateMessageStatus(contactId, ApplicationConstants.CLOSED_MESSAGE);
        return ResponseEntity.ok(
                new ResponseDto("200", "Contact #" + contactId + " has closed")
        );
    }
}
