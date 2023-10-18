package com.shop.productservice.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Catergory extends BaseEntity {
    private String name;
    private String description;
    private String voucherId;
}
