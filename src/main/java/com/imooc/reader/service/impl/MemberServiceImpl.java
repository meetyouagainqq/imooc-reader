package com.imooc.reader.service.impl;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.reader.dao.MemberDao;
import com.imooc.reader.entity.Member;
import com.imooc.reader.exception.MemberException;
import com.imooc.reader.service.MemberService;
import com.imooc.reader.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Member createMember(String username, String password, String nickname) {
        QueryWrapper<Member> wrapper=new QueryWrapper<>();
        wrapper.eq("username",username);
        List<Member> memberList = memberDao.selectList(wrapper);
        if (memberList.size()>0){
           throw new MemberException("用户名重复");
        }
        Member member=new Member();
        int salt=new Random().nextInt(1000)+1000;
        member.setCreateTime(new Date());
        member.setUsername(username);
        member.setNickname(nickname);
        String passwordStr = Md5Util.md5Digest(password, salt);
        member.setPassword(passwordStr);
        member.setSalt(salt);
        memberDao.insert(member);
        return member;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Member checkLogin(String username, String password) {
        QueryWrapper<Member> wrapper=new QueryWrapper<>();
        wrapper.eq("username",username);
        Member member = memberDao.selectOne(wrapper);
        if(member==null){
            throw new MemberException("用户名或密码错误");
        }
        String md5Str = Md5Util.md5Digest(password, member.getSalt());
        if(!md5Str.equals(member.getPassword())){
            throw new MemberException("用户名或密码错误");
        }
        return member;
    }

    @Override
    public Member getMemberById(Integer id) {
        return memberDao.selectById(id);
    }
}
