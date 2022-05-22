package pl.put.movieshop.common.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class ResponseTemplate<T> {
    private Integer status;
    private T payload;
    private String message;
}
