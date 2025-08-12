package com.example.HelloSpringBoot.controller;

import com.example.HelloSpringBoot.model.ReadyResponse;
import com.example.HelloSpringBoot.service.KPService;
import com.example.HelloSpringBoot.service.OrderService;
import com.example.HelloSpringBoot.entity.Order;
import com.example.HelloSpringBoot.repository.OrderRepository;
import com.example.HelloSpringBoot.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class KPayController {
    @Autowired
    private KPService kpService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/kpay")
    public String index() {
        return "index";
    }

    @GetMapping("/ready/{agent}/{openType}")
    public String ready(@PathVariable("agent") String agent, @PathVariable("openType") String openType,
                       @RequestParam("itemId") Long itemId, @RequestParam("count") int count, Model model) {
        // 실제 로그인 사용자 이메일 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        OrderDto orderDto = new OrderDto();
        orderDto.setItemId(itemId);
        orderDto.setCount(count);
        Long orderId = orderService.order(orderDto, email);
        // 결제 준비 시 주문번호를 partnerOrderId로 넘김
        ReadyResponse readyResponse = kpService.ready(agent, openType, orderId);

        if (agent.equals("mobile")) {
            return "redirect:" + readyResponse.getNext_redirect_mobile_url();
        }
        if (agent.equals("app")) {
            model.addAttribute("webviewUrl", "app://webview?url=" + readyResponse.getNext_redirect_app_url());
            return "app/webview/ready";
        }
        model.addAttribute("response", readyResponse);
        return "kakaopayPC/layer/ready";
    }

    @GetMapping("/approve/{agent}/{openType}")
    public String approve(@PathVariable("agent") String agent, @PathVariable("openType") String openType, @RequestParam("pg_token") String pgToken, @RequestParam(value = "partner_order_id", required = false) Long partnerOrderId, Model model) {
        String approveResponse = kpService.approve(pgToken);
        model.addAttribute("response", approveResponse);
        // 주문번호가 넘어오면 Slack 알림 전송
        if (partnerOrderId != null) {
            Order order = orderRepository.findById(partnerOrderId).orElse(null);
            if (order != null) {
                orderService.sendPaymentApprovedMessage(order);
            }
        }
        return "kakaopayPC/layer/approve";
    }

    @GetMapping("/cancel/{agent}/{openType}")
    public String cancel(@PathVariable("agent") String agent, @PathVariable("openType") String openType) {
        // 결제내역조회(/v1/payment/status) api에서 status를 확인한 후 취소 처리
        return "kakaopayPC/layer/cancel";
    }

    @GetMapping("/fail/{agent}/{openType}")
    public String fail(@PathVariable("agent") String agent, @PathVariable("openType") String openType) {
        // 결제내역조회(/v1/payment/status) api에서 status를 확인한 후 실패 처리
        return "kakaopayPC/layer/fail";
    }
}
