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
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardDAO boardDAO;

    // 게시글 리스트
    @Override
    public List<Board> boardListByPage(PageInfo pageInfo) throws Exception {
        int boardCount = boardDAO.selectBoardCount(); // 게시글 개수 파악
        if(boardCount==0) return null; // 게시글 없는 경우 null 리턴

        // 페이징 처리
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

    // 파일 보기
    @Override
    public void fileView(Integer num, OutputStream out) throws Exception {
        try {
            FileVO fileVO = boardDAO.selectFile(num);
            FileInputStream fis = new FileInputStream(fileVO.getDirectory() + num);
            FileCopyUtils.copy(fis, out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 글 상세
    @Override
    public Board boardDetail(Integer num) throws Exception {
        return boardDAO.selectBoard(num);
    }

    // 게시글 수정
    @Override
    public Board boardModify(Board board, MultipartFile file) throws Exception { // 생성 때와 유사한 로직
        // file이 존재할 때
        if(file!=null && !file.isEmpty()) {
            // 1. 파일정보 DB에 추가
            String dir = "D:/seonjin/upload/";
            FileVO fileVO = new FileVO();
            fileVO.setDirectory(dir);
            fileVO.setName(file.getOriginalFilename());
            fileVO.setSize(file.getSize());
            fileVO.setContenttype(file.getContentType());
            fileVO.setData(file.getBytes());
            boardDAO.insertFile(fileVO);

            // 2. upload 폴더에 파일 업로드
            File uploadFile = new File(dir+fileVO.getNum());
            file.transferTo(uploadFile);

            // 3. 기존 파일번호 삭제를 위해 받아놓기
            Integer deleteFileNum = null;
            if(board.getFileurl()!=null && board.getFileurl().trim().equals("")) {
                deleteFileNum = Integer.parseInt(board.getFileurl());
            }

            // 4. 파일번호를 board fileUrl에 복사 & board update
            board.setFileurl(fileVO.getNum()+"");
            boardDAO.updateBoard(board);

            // 5. board fileUrl에 해당하는 파일 번호를 파일 테이블에서 삭제
            if(deleteFileNum!=null) {
                boardDAO.deleteFile(deleteFileNum);
            }
        } else {
            boardDAO.updateBoard(board);
        }
        return boardDAO.selectBoard(board.getNum());
    }

    // 게시글 삭제
    @Override
    public void boardDelete(Integer num) throws Exception {
        Board board = boardDAO.selectBoard(num);
        if(board != null) {
            if(board.getFileurl()!=null) { // 파일이 있는 경우 파일 먼저 삭제
                boardDAO.deleteFile(Integer.parseInt(board.getFileurl()));
            }
            boardDAO.deleteBoard(num);
        }
    }

    //  좋아요 - 해당 게시글에 특정 회원이 좋아요를 눌렀는지 여부 확인
    @Override
    public Boolean isBoardLike(String userId, Integer boardNum) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("id", userId);
        param.put("num", boardNum);
        Integer likeNum = boardDAO.selectBoardLike(param);
        return likeNum==null ? false : true; // 좋아요 선택 여부가 없는 경우 false로 설정, 있는 경우 true로 설정
    }

    @Override
    public Boolean selectBoardLike(String userId, Integer boardNum) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("id", userId);
        param.put("num", boardNum);
        Integer likeNum = boardDAO.selectBoardLike(param);
        if(likeNum == null) { // 좋아요 선택 여부가 없는 경우
            boardDAO.insertBoardLike(param); // 좋아요 추가
            boardDAO.plusBoardLikeCount(boardNum); // 해당 게시글의 좋아요 수 +
            return true;
        } else {
            boardDAO.deleteBoardLike(param);
            boardDAO.minusBoardLikeCount(boardNum);
            return false;
        }
    }
}
