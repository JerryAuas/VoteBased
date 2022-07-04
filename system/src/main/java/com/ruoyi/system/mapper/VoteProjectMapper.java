package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VoteProjectMapper {
    int deleteByPrimaryKey(String vid);

    int insert(VoteProject record);

    int insertSelective(VoteProject record);

    VoteProject selectByPrimaryKey(String vid);
//    新增投票项目
    Integer insertProject(VoteProject voteProject);

//    获取系统所有投票项目
    List<VoteProject> selectAll();

//    获取用户的投票项目
    List<VoteProject> selectMy(Integer uid);

//    获取项目对应的候选人
    List<Candidate> selectMyCan(String vid);

//    获取项目对应的投票者
    List<Voter> selectMyVoter(String vid);

//    获取用户简洁信息
    List<Vuser> selectAllUser();

//    获取该投票项目下各个候选人得票数
    List<VoteCount> selectAllCount(String vid);

//    先删除
    Integer delVote(String vid);


    //    获取某个投票项目目前投票信息(根据投票项目id)
    List<Candidate> selectVoteInfo(String vid);

//    新增投票者
    Integer insertVoter(VoterProject voterProject);

//    新增候选人
    Integer insertCandidate(CandidateProject candidateProject);
//    新增投票
    Integer insertVote(VoteResult voteResult);

    int updateByPrimaryKeySelective(VoteProject record);

    int updateByPrimaryKey(VoteProject record);

//    更新投票状态
    int updateVoteState(VoterProject voterProject);
//    保存index
    int updateIndex(VoterProject voterProject);
}