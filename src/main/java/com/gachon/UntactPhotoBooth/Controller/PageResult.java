package com.gachon.UntactPhotoBooth.Controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

/**
 * @Date 2021. 09. 08
 * @author leesw
 * @email leesw@infobank.net
 * @description : API page 응답 시
 * @param <T>
 */
@Getter
@Setter
public class PageResult<T> extends CommonResult {
    private Page<T> pageList;
}
