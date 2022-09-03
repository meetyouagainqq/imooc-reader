package com.imooc.reader.controller;

import com.google.code.kaptcha.Producer;
import com.imooc.reader.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class KaptchaController {
    @Autowired
    private Producer kaptchaProducer;

    @GetMapping("/api/verify_code")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //每次刷新都能得到新的图片
        response.setDateHeader("Expires", 0);
        //不缓存任何图片数据
        response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
        response.setHeader("Cache-Control", "post-check=0,pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/png");
        //生成图片文本信息
        String text = kaptchaProducer.createText();
        request.getSession().setAttribute("kaptchaVerifyCode", text);
        BufferedImage image = kaptchaProducer.createImage(text);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
