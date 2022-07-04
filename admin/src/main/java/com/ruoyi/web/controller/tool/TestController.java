package com.ruoyi.web.controller.tool;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.VoteProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

/**
 * swagger 用户测试方法
 * 
 * @author ruoyi
 */
@Api("用户信息管理")
@RestController
@RequestMapping("/test/user")
public class TestController extends BaseController
{
    private final static Map<Integer, UserEntity> users = new LinkedHashMap<Integer, UserEntity>();
    {
        users.put(1, new UserEntity(1, "admin", "admin123", "15888888888"));
        users.put(2, new UserEntity(2, "ry", "admin123", "15666666666"));
    }

    @Autowired
    private VoteProjectMapper voteProjectMapper;

//    新增投票项目
    @PostMapping("/addProject")
    public Integer addProject(@RequestBody VoteProject voteProject)
    {
        return voteProjectMapper.insertProject(voteProject);
    }
//    测试获取投票项目信息
    @GetMapping("/info")
    public List<VoteProject> voteProjectInfo()
    {
        return voteProjectMapper.selectAll();
    }

//    根据用户id获取用户需要投票的项目信息
    @GetMapping("/myInfo")
    public List<VoteProject> myProjectInfo(@RequestParam("uid") Integer uid)
    {
        return voteProjectMapper.selectMy(uid);
    }
//    根据项目id获取候选人信息
    @GetMapping("/myCandidateInfo")
    public List<Candidate> myCandidateInfo(@RequestParam("vid") String vid)
    {
        return voteProjectMapper.selectMyCan(vid);
    }
//    根据项目id获取投票者信息
    @GetMapping("/myVoterInfo")
    public List<Voter> myVoterInfo(@RequestParam("vid") String vid)
    {
        return voteProjectMapper.selectMyVoter(vid);
    }
//    获取所有用户信息
    @GetMapping("/allUser")
    public List<Vuser> allUser()
    {
        return voteProjectMapper.selectAllUser();
    }
//    为投票项目添加投票者
    @PostMapping("/addVoter")
    public Integer addVoter(@RequestBody VoterProject voterProject)
    {
        return voteProjectMapper.insertVoter(voterProject);
    }
//    为投票项目添加候选人
    @PostMapping("/addCandidate")
    public Integer addCandidate(@RequestBody CandidateProject candidateProject)
    {
        return voteProjectMapper.insertCandidate(candidateProject);
    }
//    根据投票项目id获取投票信息
    @GetMapping("/getVotedInfo")
    public List<Candidate> getVotedInfo(@RequestParam("vid") String vid){
        return voteProjectMapper.selectVoteInfo(vid);
    }
//    上传区块链上投票信息至数据库
    @PostMapping("/addVoteResult")
    public Integer addVoteResult(@RequestBody VoteResult voteResult){
        return voteProjectMapper.insertVote(voteResult);
    }

//    获取该投票项目下各个候选人得票数
    @GetMapping("/getCount")
    public List<VoteCount> getCount(@RequestParam("vid") String vid){
        return voteProjectMapper.selectAllCount(vid);
    }

//    根据id 删除
    @GetMapping("/delVote")
    public Integer delVote(@RequestParam("vid") String vid){
        return voteProjectMapper.delVote(vid);
    }
//    更新投票状态
    @PostMapping("/updateState")
    public Integer updateVoteState(@RequestBody VoterProject voterProject){
        //System.out.println(voterProject.getProjectId());
        return voteProjectMapper.updateVoteState(voterProject);
    }
//    保存index
    @PostMapping("/updateIndex")
    public Integer updateIndex(@RequestBody VoterProject voterProject){
        //System.out.println(voterProject.getProjectId());
        return voteProjectMapper.updateIndex(voterProject);
    }




    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public R<List<UserEntity>> userList()
    {
        List<UserEntity> userList = new ArrayList<UserEntity>(users.values());
        return R.ok(userList);
    }

    @ApiOperation("获取用户详细")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @GetMapping("/{userId}")
    public R<UserEntity> getUser(@PathVariable Integer userId)
    {
        if (!users.isEmpty() && users.containsKey(userId))
        {
            return R.ok(users.get(userId));
        }
        else
        {
            return R.fail("用户不存在");
        }
    }

    @ApiOperation("新增用户")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Integer", dataTypeClass = Integer.class),
        @ApiImplicitParam(name = "username", value = "用户名称", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "password", value = "用户密码", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "mobile", value = "用户手机", dataType = "String", dataTypeClass = String.class)
    })
    @PostMapping("/save")
    public R<String> save(UserEntity user)
    {
        if (StringUtils.isNull(user) || StringUtils.isNull(user.getUserId()))
        {
            return R.fail("用户ID不能为空");
        }
        users.put(user.getUserId(), user);
        return R.ok();
    }

    @ApiOperation("更新用户")
    @PutMapping("/update")
    public R<String> update(@RequestBody UserEntity user)
    {
        if (StringUtils.isNull(user) || StringUtils.isNull(user.getUserId()))
        {
            return R.fail("用户ID不能为空");
        }
        if (users.isEmpty() || !users.containsKey(user.getUserId()))
        {
            return R.fail("用户不存在");
        }
        users.remove(user.getUserId());
        users.put(user.getUserId(), user);
        return R.ok();
    }

    @ApiOperation("删除用户信息")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @DeleteMapping("/{userId}")
    public R<String> delete(@PathVariable Integer userId)
    {
        if (!users.isEmpty() && users.containsKey(userId))
        {
            users.remove(userId);
            return R.ok();
        }
        else
        {
            return R.fail("用户不存在");
        }
    }
}

@ApiModel(value = "UserEntity", description = "用户实体")
class UserEntity
{
    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户手机")
    private String mobile;

    public UserEntity()
    {

    }

    public UserEntity(Integer userId, String username, String password, String mobile)
    {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
}
