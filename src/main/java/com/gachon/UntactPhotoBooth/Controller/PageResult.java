package com.gachon.UntactPhotoBooth.Controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

/**
 *  API page 응답 시
 *
 */
@Getter
@Setter
public class PageResult<T> extends CommonResult {
    private Page<T> pageList;
}
