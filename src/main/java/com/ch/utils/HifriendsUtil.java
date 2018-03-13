package com.ch.utils;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.MessageDigest;

/**
 * Created by apple on 2018/3/1.
 */
public class HifriendsUtil {

    private static final Logger logger = LoggerFactory.getLogger(HifriendsUtil.class);

    public static String getJSONString(int code,String msg){
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("msg",msg);
        return json.toJSONString();
    }

    public static String getJSONString(int code){
        JSONObject json = new JSONObject();
        json.put("code",code);
        return json.toJSONString();
    }

    public static String MD5(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
//            logger.error("生成MD5失败", e);
            return null;
        }
    }

    public static String saveImage(MultipartFile file, String realPath, String fileName,String floder,String projectPath){
        if (!file.isEmpty()) {
            File filePath = new File(realPath, fileName);
            //判断路径是否存在，如果不存在就创建一个
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            try {
                String url = projectPath+"/"+floder+"/"+fileName;
                file.transferTo(new File(realPath + File.separator + fileName));
                return url;
            }catch (Exception e){
                e.printStackTrace();
                logger.error("图片保存错误"+e.getMessage());
                return "error";
            }
        } else {
            return "error";
        }
    }
}
