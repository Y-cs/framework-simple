package com.yidian.galaxy.web.support;

import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.yidian.galaxy.web.entity.JwtParam;

import java.util.HashMap;
import java.util.Map;

/**
 * JWT 支持
 *
 * @author changshuai.yuan create on 2024/1/19 15:45
 */
public class JwtSupport {
    
    private static final String KEY = "YxUjucmxDgvp";
    
    private static final JWTSigner SIGNER = JWTSignerUtil.hs256(KEY.getBytes());
    
    public static final String AID = "a";
    
    public static final String UID = "u";
    
    public static final String S = "s";
    
    /**
     * 创建JWT
     *
     * @param jwtParam JWT对象
     * @return JWT
     */
    public static String createJwtToken(JwtParam jwtParam) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put(AID, jwtParam.getAccountId());
        userMap.put(UID, jwtParam.getUserId());
        userMap.put(S, jwtParam.getSystem());
        return JWTUtil.createToken(userMap, SIGNER);
    }
    
    /**
     * 解析JWT
     *
     * @param jwtStr JWT
     * @return JWT对象
     */
    public static JwtParam parseJwtToken(String jwtStr) {
        JWT jwt = JWTUtil.parseToken(jwtStr);
        JSONObject payloads = jwt.getPayloads();
        return new JwtParam().setAccountId(payloads.getLong(AID)).setUserId(payloads.getLong(UID))
                .setSystem(payloads.getInt(S));
    }
    
}
