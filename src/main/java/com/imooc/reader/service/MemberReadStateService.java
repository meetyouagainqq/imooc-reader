package com.imooc.reader.service;

import com.imooc.reader.entity.MemberReadState;

public interface MemberReadStateService {
    public MemberReadState getMemberReadeStatus(Integer bookId, Integer memberId);
    public MemberReadState updateMemberStatus(Integer bookId, Integer memberId,Integer readState);
}
