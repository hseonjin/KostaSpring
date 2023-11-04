package com.kosta.boardreview.service;

import com.kosta.boardreview.dao.BoardDAO;
import com.kosta.boardreview.dto.Board;
import com.kosta.boardreview.dto.FileVO;
import com.kosta.boardreview.dto.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardDAO boardDAO;

    // 게시글 작성
    @Override
    public Board boardWrite(Board board, MultipartFile file) throws Exception {
        if(file!=null && !file.isEmpty()) {
            String dir = "D:/seonjin/upload/";
            FileVO fileVO = new FileVO();
            fileVO.setDirectory(dir);
            fileVO.setName(file.getOriginalFilename());
            fileVO.setSize(file.getSize());
            fileVO.setContenttype(file.getContentType());
            fileVO.setData(file.getBytes());
            boardDAO.insertFile(fileVO);
            Integer num = fileVO.getNum();

            File uploadFile = new File(dir+num);
            file.transferTo(uploadFile);
            board.setFileurl(num+"");
        }
        boardDAO.insertBoard(board);
        return boardDAO.selectBoard(board.getNum());
    }

    // 게시글 리스트
    @Override
    public List<Board> boardListByPage(PageInfo pageInfo) throws Exception {
        int boardCount = boardDAO.selectBoardCount();
        if(boardCount==0) return null;

        int allPage = (int)Math.ceil((double)boardCount/10);
        int startPage = (pageInfo.getCurPage()-1)/10*10+1;
        int endPage = Math.min(startPage+10-1, allPage);

        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);
        if(pageInfo.getCurPage()>allPage) pageInfo.setCurPage(allPage);

        int row = (pageInfo.getCurPage()-1)*10+1;
        return boardDAO.selectBoardList(row-1);
    }

    // 파일 보기
    @Override
    public void fileView(Integer num, OutputStream out) throws Exception {
        try {
            FileVO fileVo = boardDAO.selectFile(num);
            FileCopyUtils.copy(fileVo.getData(), out);
            out.flush();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
