package org.example;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TestOrderInput {
    private int userId;
    private int productId;
    private int quantity;

}
