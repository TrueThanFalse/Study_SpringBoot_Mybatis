package com.example.mybatis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mybatis.domain.BoardDTO;
import com.example.mybatis.domain.BoardVO;
import com.example.mybatis.domain.FileVO;
import com.example.mybatis.domain.PagingVO;
import com.example.mybatis.repository.BoardMapper;
import com.example.mybatis.repository.FileMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardMapper bmapper;
	private final FileMapper fmapper;

	@Override
	public int registerDTO(BoardDTO bdto) {
		// TODO Auto-generated method stub
		int isOK = bmapper.insertBvo(bdto.getBvo());
		if(isOK > 0) {
			long bno = bmapper.getBno();
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isOK *= fmapper.insertFile(fvo);
			}
		}
		
		return isOK;
	}

	@Override
	public List<BoardVO> getBvoList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bmapper.selectAllBvoList(pgvo);
	}

	@Override
	public BoardDTO getDetail(long bno) {
		// TODO Auto-generated method stub
		BoardDTO bdto = new BoardDTO();
		bdto.setBvo(bmapper.selectOneBvo(bno));
		bdto.setFlist(fmapper.getFileList(bno));
		return bdto;
	}

	@Override
	public int deleteBvo(long bno) {
		// TODO Auto-generated method stub
		return bmapper.deleteBvo(bno);
	}

	@Override
	public int editBvo(BoardDTO bdto) {
		// TODO Auto-generated method stub
		int isOK = bmapper.updateBvo(bdto.getBvo());
		if(isOK > 0 && bdto.getFlist().size()>0) {
			long bno = bdto.getBvo().getBno();
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isOK *= fmapper.insertFile(fvo);
			}
		}
		
		return isOK;
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bmapper.selectAllBvoCount(pgvo);
	}

	@Override
	public int registerBvo(BoardVO bvo) {
		// TODO Auto-generated method stub
		return bmapper.insertBvo(bvo);
	}
}
