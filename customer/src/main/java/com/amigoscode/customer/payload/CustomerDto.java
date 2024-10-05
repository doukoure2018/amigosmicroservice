package com.amigoscode.customer.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
