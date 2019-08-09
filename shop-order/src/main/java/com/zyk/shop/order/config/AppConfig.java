package com.zyk.shop.order.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class AppConfig {
    @Value("${app.uc.url}")
    private String ucUrl;
}
