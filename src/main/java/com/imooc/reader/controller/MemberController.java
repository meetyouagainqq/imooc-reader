package com.imooc.reader.controller;


import com.imooc.reader.entity.Evaluation;
import com.imooc.reader.entity.Member;
import com.imooc.reader.entity.MemberReadState;
import com.imooc.reader.service.EvaluationService;
import com.imooc.reader.service.MemberReadStateService;
import com.imooc.reader.service.MemberService;
import com.imooc.reader.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberReadStateService memberReadStateService;
    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("/registe")
    public ResponseUtil register(String username, String password, String nickname, String vc, HttpServletRequest request) {
        String verifyCode = (String) request.getSession().getAttribute("kaptchaVerifyCode");
        ResponseUtil response;
        if (vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)) {
            response = new ResponseUtil("VerifyCodeError", "验证码错误");
        } else {
            response = new ResponseUtil();
            try {
                Member member = memberService.createMember(username, password, nickname);
                response = new ResponseUtil();
            } catch (Exception e) {
                e.printStackTrace();
                response = new ResponseUtil(e.getClass().getSimpleName(), e.getMessage());
            } finally {
            }
        }
        return response;
    }

    @PostMapping("/check_login")
    public ResponseUtil checkLogin(String username, String password, String nickname, String vc, HttpServletRequest request) {
        String verifyCode = (String) request.getSession().getAttribute("kaptchaVerifyCode");
        ResponseUtil response;
        if (vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)) {
            response = new ResponseUtil("VerifyCodeError", "验证码错误");
        } else {
            response = new ResponseUtil();
            try {
                Member member = memberService.checkLogin(username, password);
                member.setPassword(null);
                member.setSalt(null);
                response = new ResponseUtil().put("member", member);
            } catch (Exception e) {
                e.printStackTrace();
                response = new ResponseUtil(e.getClass().getSimpleName(), e.getMessage());
            } finally {
            }
        }
        return response;
    }

    @GetMapping("/select_by_id")
    public ResponseUtil getMemberById(Integer memberId) {
        ResponseUtil response;
        try {
            Member member = memberService.getMemberById(memberId);
            response = new ResponseUtil().put("member", member);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseUtil(e.getClass().getSimpleName(), e.getMessage());
        }
        return response;
    }

    @GetMapping("/select_read_state")
    public ResponseUtil getMemberReadStatus(Integer bookId, Integer memberId) {
        ResponseUtil response;
        try {
            MemberReadState memberReadeStatus = memberReadStateService.getMemberReadeStatus(bookId, memberId);
            response = new ResponseUtil().put("readState", memberReadeStatus);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseUtil(e.getClass().getSimpleName(), e.getMessage());
        }
        return response;
    }

    @PostMapping("/update_read_state")
    public ResponseUtil updateReadState(Integer bookId, Integer memberId, Integer readState) {
        ResponseUtil response;
        try {
            MemberReadState state = memberReadStateService.updateMemberStatus(bookId, memberId, readState);
            response = new ResponseUtil().put("readState", state);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseUtil(e.getClass().getSimpleName(), e.getMessage());
        }
        return response;
    }

    @PostMapping("/evaluate")
    public ResponseUtil addEvaluate(Integer memberId, Integer bookId, Integer score, String content) {
        ResponseUtil response;
        try {
            Evaluation evaluation = evaluationService.addEvaluation(memberId, bookId, score, content);
            response = new ResponseUtil().put("evaluation", evaluation);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseUtil(e.getClass().getSimpleName(), e.getMessage());
        }
        return response;
    }

    @PostMapping("/enjoy")
    public ResponseUtil addEnjoy(Integer evaluationId) {
        ResponseUtil response;
        try {
            Evaluation evaluation = evaluationService.updateEnjoy(evaluationId);
            response = new ResponseUtil().put("evaluation", evaluation);
        } catch (Exception e) {
            e.printStackTrace();
            response = new ResponseUtil(e.getClass().getSimpleName(), e.getMessage());
        }
        return response;
    }
}
