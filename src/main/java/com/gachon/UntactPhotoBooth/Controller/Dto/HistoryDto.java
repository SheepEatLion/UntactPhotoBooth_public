package com.gachon.UntactPhotoBooth.Controller.Dto;


import com.gachon.UntactPhotoBooth.Domain.History.History;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HistoryDto {

    private String uploader;
    private String image;
    private String result;


    @Builder
    public HistoryDto(String uploader, String image, String result){
        this.uploader = uploader;
        this.image = image;
        this.result = result;
    }

    public History toEntity(){
        return History.builder()
                .uploader(uploader)
                .image(image)
                .result(result)
                .build();
    }
}
