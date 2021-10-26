package com.gachon.UntactPhotoBooth.Controller.Dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HistoryListReq {

    private int pageNumber;     // 현재 페이지 번호
    private int pageSize;       // 페이지 당 개수
    private String searchTxt;   // 검색할 쿼리 대상 ( 유저 이메일 )

}
