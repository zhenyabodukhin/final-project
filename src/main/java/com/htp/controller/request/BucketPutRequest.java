package com.htp.controller.request;

import lombok.*;

import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BucketPutRequest {

    @Min(1)
    private Long goodId;

    @Min(1)
    private Integer count;

}
