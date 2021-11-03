package com.gachon.UntactPhotoBooth.Controller;


import com.gachon.UntactPhotoBooth.Controller.Dto.HistoryDto;
import com.gachon.UntactPhotoBooth.Controller.Dto.HistoryListReq;
import com.gachon.UntactPhotoBooth.Controller.Dto.HistoryListRes;
import com.gachon.UntactPhotoBooth.Service.HistoryService;
import com.gachon.UntactPhotoBooth.Service.S3Service;
import com.gachon.UntactPhotoBooth.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class HistoryController {

    private final HistoryService historyService;
    private final S3Service s3Service;

    @PostMapping("/save/history")
    public void save(@RequestBody HistoryDto dto){
        historyService.save(dto);
    }

    @GetMapping("/my-page/data-list")
    public CommonResult historyList(HistoryListReq listReq){
        Page<HistoryListRes> historyListResPage = null;
        try {
            historyListResPage = historyService.getList(listReq);
        } catch (ServiceException se) {
            return ResponseUtil.getFailResult(se);
        }
        return ResponseUtil.getPageListResult(historyListResPage);
    }

    @GetMapping("/my-page/download")
    public ResponseEntity<byte[]> imageDownload(HttpServletRequest request) throws IOException {
        String imgUrl = request.getParameter("img");
        String[] urls = imgUrl.split("\\.");
        System.out.println("*******************" + urls[urls.length-2]);
        return s3Service.downloadImageFromS3(urls[urls.length-2]);
    }

    @DeleteMapping("/my-page/history-delete/{id}")
    public String delete(@PathVariable("id") Long id){
        historyService.historyDelete(id);
        return "redirect:/mypage";
    }
}

