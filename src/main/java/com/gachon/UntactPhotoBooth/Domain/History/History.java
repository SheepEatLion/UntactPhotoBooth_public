package com.gachon.UntactPhotoBooth.Domain.History;

import com.gachon.UntactPhotoBooth.Domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "MY_HISTORY")
public class History extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uploader;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String result;

    @Builder
    public History(String uploader, String image, String result){
        this.uploader = uploader;
        this.image = image;
        this.result = result;
    }
}
