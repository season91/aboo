package com.kh.aboo.myapt.vote.model.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.kh.aboo.myapt.vote.model.vo.AuthToVote;
import com.kh.aboo.myapt.vote.model.vo.VoteGen;

public interface VoteService {
	
	int authToVote(String tell, HttpSession session);
	int selectGenerationWonToAuth(AuthToVote authToVote);
	String selectGenerationWonIdxToVote(AuthToVote authToVote);
	String selectGenerationIdxToConfirm(String generationWonIdx);
	String selectGenerationWonTellToVote(AuthToVote authToVote);
	int insertVoteGen(VoteGen voteGen);
	int selectIfParticipate(String generationIdx, String voteNo);
	List<Double> calculateTurnout(String voteNo);
	
}
