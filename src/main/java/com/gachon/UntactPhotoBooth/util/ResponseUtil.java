package com.gachon.UntactPhotoBooth.util;

import com.gachon.UntactPhotoBooth.Controller.CommonResult;
import com.gachon.UntactPhotoBooth.Controller.PageResult;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Date 2021. 08. 06.
 * @author leesw
 * @email leesw@infobank.net
 * @description : API 응답 값 처리 유틸
 */
public final class ResponseUtil {

    private static final String FAIL_MESSAGE = "요청을 실패하였습니다.";
    private static final String SUCCESS_MESSAGE = "요청을 성공하였습니다.";

    /**
     * @description : 기본 성공 응답 / Object를 포함하지 않음.
     * @return
     */
    public static CommonResult getSuccessResult() {
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(true);
        commonResult.setCode("0");
        commonResult.setMessage(SUCCESS_MESSAGE);

        return commonResult;
    }

    /**
     * @description : 기본 성공 응답 / code 변경가능
     * @return
     */
    public static CommonResult getSuccessResult(String code) {
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(true);
        commonResult.setCode(code);
        commonResult.setMessage(SUCCESS_MESSAGE);

        return commonResult;
    }

    /**
     * @description : 기본 실패 응답
     * @param
     * @return
     */
    public static CommonResult getFailResult() {
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(false);
        commonResult.setCode("-1");
        commonResult.setMessage(FAIL_MESSAGE);

        return commonResult;
    }

    /**
     * @description : Exception에 의한 실패응답
     * @param se
     * @return
     */
    public static CommonResult getFailResult(ServiceException se) {
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(false);
        commonResult.setCode("500");
        commonResult.setMessage(se.getMessage());

        return commonResult;
    }

    /**
     * @description : 에러코드, 메시지 직접입력
     * @param
     * @return
     */
    public static CommonResult getFailResult(String errorCode, String message) {
        CommonResult commonResult = new CommonResult();
        commonResult.setSuccess(false);
        commonResult.setCode(errorCode);
        commonResult.setMessage(message);

        return commonResult;
    }

    /**
     * @description : 단일 결과 응답
     * @param <T>
     * @param data
     * @return
     */
    /*public static <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        if (data != null)
            result.setData(data);
        setSuccessResult(result);
        return result;
    }*/

    /**
     * @description : 리스트 결과 응답
     * @param <T>
     * @param list
     * @return
     */
    /*public static <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }*/

    /**
     * @description : 페이지 결과 응답
     * @param <T>
     * @param
     * @return
     */
    public static <T> PageResult<T> getPageListResult(Page<T> pageList) {
        PageResult<T> result = new PageResult<>();
        result.setPageList(pageList);
        setSuccessResult(result);
        return result;
    }

    /**
     * @description : 성공 메시지 set
     * @param result
     */
    private static void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode("0");
        result.setMessage(SUCCESS_MESSAGE);
    }
}