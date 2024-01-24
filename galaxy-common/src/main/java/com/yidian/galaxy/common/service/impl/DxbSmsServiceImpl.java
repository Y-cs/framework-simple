package com.yidian.galaxy.common.service.impl;

import com.yidian.galaxy.common.biz.NoticeTemplateBiz;
import com.yidian.galaxy.common.config.properties.DxbSmsProperties;
import com.yidian.galaxy.common.consts.NoticeTemplate;
import com.yidian.galaxy.common.service.SmsService;
import com.yidian.galaxy.web.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SmsServiceImpl
 *
 * @author changshuai.yuan create on 2024/1/24 17:54
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DxbSmsServiceImpl implements SmsService {
    
    private static final CloseableHttpClient HTTP_CLIENT = HttpClients.createDefault();
    
    private final NoticeTemplateBiz noticeTemplateBiz;
    
    private final DxbSmsProperties dxbSmsProperties;
    
    @Override
    public boolean sendSms(String phone, NoticeTemplate.UsesEnum usesEnum, Object... args) {
        try {
            String template = noticeTemplateBiz.findTemplate(usesEnum);
            String content = String.format(template, args);
            log.info("发送短信:手机号:{},内容:{}", phone, content);
            URI uri = new URIBuilder(dxbSmsProperties.getUrl()).setParameter("u", dxbSmsProperties.getUsername())
                    .setParameter("p", md5(dxbSmsProperties.getPassword())).setParameter("m", phone)
                    .setParameter("c", encodeUrlString(content, "UTF-8")).build();
            HttpGet httpGet = new HttpGet(uri);
            try (CloseableHttpResponse response = HTTP_CLIENT.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    String responseContent = EntityUtils.toString(response.getEntity(), "UTF-8");
                    if ("0".equals(responseContent)) {
                        return true;
                    } else {
                        log.error("短信宝短信第三方报错误：{}", responseContent);
                    }
                }
            }
            return false;
        } catch (Exception e) {
            log.error("短信发送失败：", e);
            throw new BusinessException("短信发送失败");
        }
    }
    
    /**
     * md5
     *
     * @param plainText plainText
     * @return Str
     */
    public String md5(String plainText) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            log.error("获取Md5失败：", e);
            throw new BusinessException("短信发送失败");
        }
        md.update(plainText.getBytes());
        byte[] b = md.digest();
        int i;
        StringBuilder buf = new StringBuilder();
        for (byte value : b) {
            i = value;
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }
    
    /**
     * urlEncoding
     *
     * @param str     str
     * @param charset charset
     * @return encodingStr
     */
    public String encodeUrlString(String str, String charset) {
        if (str == null) {
            return null;
        }
        try {
            return java.net.URLEncoder.encode(str, charset);
        } catch (UnsupportedEncodingException e) {
            log.error("短信Encoding失败:", e);
            throw new BusinessException("短信发送失败");
        }
    }
    
}
