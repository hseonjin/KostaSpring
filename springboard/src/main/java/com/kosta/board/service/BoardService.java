package com.kosta.board.service;

import com.kosta.board.dto.Board;
import com.kosta.board.dto.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    List<Board> boardListByPage(PageInfo pageInfo) throws Exception;
    Board writeBoard(Board board, MultipartFile file) throws Exception;
}
