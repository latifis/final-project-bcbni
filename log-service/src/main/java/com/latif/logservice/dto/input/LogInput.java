package com.latif.logservice.dto.input;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LogInput {
    private String name;
    private Object data;
}
