package com.imooc.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.reader.dao.MemberReadStateDao;
import com.imooc.reader.entity.MemberReadState;
import com.imooc.reader.service.MemberReadStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MemberReadStateServiceImpl implements MemberReadStateService {
    @Autowired
    private MemberReadStateDao memberReadStateDao;

    @Override
    public MemberReadState getMemberReadeStatus(Integer bookId, Integer memberId) {
        QueryWrapper<MemberReadState> wrapper = new QueryWrapper<>();
        wrapper.eq("book_id", bookId);
        wrapper.eq("member_id", memberId);
        MemberReadState readState = memberReadStateDao.selectOne(wrapper);
        return readState;
    }

    @Override
    public MemberReadState updateMemberStatus(Integer bookId, Integer memberId, Integer readState) {
        QueryWrapper<MemberReadState> wrapper = new QueryWrapper<>();
        wrapper.eq("book_id", bookId);
        wrapper.eq("member_id", memberId);
        MemberReadState state = memberReadStateDao.selectOne(wrapper);
        if (state == null) {
            state = new MemberReadState();
            state.setBookId(bookId);
            state.setReadState(readState);
            state.setCreateTime(new Date());
            state.setMemberId(memberId);
            memberReadStateDao.insert(state);
        } else {
            state.setReadState(readState);
            state.setCreateTime(new Date());
            memberReadStateDao.updateById(state);
        }
        return state;

    }
}
