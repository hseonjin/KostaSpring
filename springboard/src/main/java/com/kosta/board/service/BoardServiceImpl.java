package com.kosta.board.service;

import com.kosta.board.dao.BoardDAO;
import com.kosta.board.dto.Board;
import com.kosta.board.dto.FileVO;
import com.kosta.board.dto.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    private BoardDAO boardDAO;

    @Override
    public List<Board> boardListByPage(PageInfo pageInfo) throws Exception {
        int boardCount = boardDAO.selectBoardCount();
        int allPage = (int)Math.ceil((double) boardCount/10);
        int startPage = (pageInfo.getCurPage()-1)/10*10+1;
        int endPage = Math.min(startPage+10-1, allPage);

        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);
        if(pageInfo.getCurPage() > allPage) pageInfo.setCurPage(allPage);

        Integer row = (pageInfo.getCurPage()-1)*10+1;
        return boardDAO.selectBoardList(row-1);
   }

    @Override
    public Board writeBoard(Board board, MultipartFile file) throws Exception {
        if(file != null && !file.isEmpty()) {
            // 파일이 있는 경우에만 파일을 처리하고 저장하는 로직 실행
            String dir = "D:/seonjin/upload/";
            FileVO fileVO = new FileVO();
            fileVO.setDirectory(dir);
            fileVO.setName(file.getOriginalFilename());
            fileVO.setSize(file.getSize());
            fileVO.setContenttype(file.getContentType());
            fileVO.setData(file.getBytes());

            boardDAO.insertFile(fileVO);
            Integer num = fileVO.getNum();

            File uploadFile = new File(dir + num);
            file.transferTo(uploadFile);
            board.setFileurl(num + "");
        }

        // 파일이 없는 경우에는 파일 관련 로직을 스킵
        boardDAO.insertBoard(board);
        return boardDAO.selectBoard(board.getNum());
    }

}
