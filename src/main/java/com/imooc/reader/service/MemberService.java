package com.imooc.reader.service;

import com.imooc.reader.entity.Member;

public interface MemberService {
    public Member createMember(String username,String password,String nickname);
    public Member checkLogin(String username,String password);
    public Member getMemberById(Integer id);
}
