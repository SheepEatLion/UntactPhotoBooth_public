package com.gachon.UntactPhotoBooth.Service;

import com.gachon.UntactPhotoBooth.Controller.Dto.HistoryDto;
import com.gachon.UntactPhotoBooth.Controller.Dto.HistoryListReq;
import com.gachon.UntactPhotoBooth.Controller.Dto.HistoryListRes;
import com.gachon.UntactPhotoBooth.Domain.History.History;
import com.gachon.UntactPhotoBooth.Domain.History.HistoryRepository;
import com.gachon.UntactPhotoBooth.Domain.History.HistoryRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HistoryService {

    private final ModelMapper modelMapper;
    private final HistoryRepository historyRepository;
    private final HistoryRepositorySupport historyRepositorySupport;

    public void save(HistoryDto dto){
        historyRepository.save(dto.toEntity());
    }

    public Page<HistoryListRes> getList(HistoryListReq listReq){
        Page<History> entityResult = historyRepositorySupport.findHistoryList(listReq);
        Page<HistoryListRes> historyListReqPage = entityResult.map(history -> modelMapper.map(history, HistoryListRes.class));
        return historyListReqPage;
    }

    public void historyDelete(Long id){
        historyRepository.deleteById(id);
    }
}
