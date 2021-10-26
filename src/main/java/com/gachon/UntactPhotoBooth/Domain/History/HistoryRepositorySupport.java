package com.gachon.UntactPhotoBooth.Domain.History;

import com.gachon.UntactPhotoBooth.Controller.Dto.HistoryListReq;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.gachon.UntactPhotoBooth.Domain.History.QHistory.history;

@Slf4j
@Repository
public class HistoryRepositorySupport extends QuerydslRepositorySupport {

    @Autowired
    private final JPAQueryFactory queryFactory;

    public HistoryRepositorySupport(JPAQueryFactory jpaQueryFactory){
        super(History.class);
        this.queryFactory = jpaQueryFactory;
    }

    // 마이 페이지 히스토리 리스트 조회
    public Page<History> findHistoryList(HistoryListReq listReq){
        Pageable pageable = PageRequest.of(listReq.getPageNumber(), listReq.getPageSize());
        String userEmail = listReq.getSearchTxt();
        QueryResults<History> result = queryFactory.selectFrom(history)
                .where(history.uploader.eq(userEmail))
                .orderBy(history.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }
}
