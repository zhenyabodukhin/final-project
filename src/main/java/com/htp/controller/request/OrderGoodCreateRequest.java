package com.htp.controller.request;

import lombok.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EnableConfigurationProperties
@ToString
public class OrderGoodCreateRequest {

    @Min(1)
    private Long orderId;

    @Min(1)
    private Long goodId;

    @Min(1)
    private Integer count;
}
