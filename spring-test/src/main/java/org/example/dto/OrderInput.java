package org.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderInput {
    private int userId;
    private int productId;
    private int quantity;

}
