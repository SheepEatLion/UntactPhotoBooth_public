package com.gachon.UntactPhotoBooth.Controller.Dto;


import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class HistoryListRes {

    private String uploader;
    private String image;
    private String result;
    private LocalDate modifiedDate;

}
