package com.kosta.boardreview.service;


import com.kosta.boardreview.dto.Board;
import com.kosta.boardreview.dto.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.List;

public interface BoardService {
    // 게시글 작성
    Board boardWrite(Board board, MultipartFile file) throws Exception;

    // 게시글 리스트 (+페이징 처리)
    List<Board> boardListByPage(PageInfo pageInfo) throws Exception;

    // 파일 보기
    void fileView(Integer num, OutputStream out) throws Exception;
}
