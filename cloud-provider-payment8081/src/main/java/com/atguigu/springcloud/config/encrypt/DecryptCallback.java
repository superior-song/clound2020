package com.atguigu.springcloud.config.encrypt;

import com.alibaba.druid.util.DruidPasswordCallback;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * @author egoo
 * @date 2019/8/6
 */
@Slf4j
public class DecryptCallback extends DruidPasswordCallback {

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String configKey = properties.getProperty("password");
        String encrypt = properties.getProperty("encrypt", "SM4_ECB");
        //KeyManagerService encryptService = SpringCtx.instance().getCtx().getBean("encryptService", KeyManagerService.class);
        try {
            if ("none".equalsIgnoreCase(encrypt)) {
                this.setPassword(configKey.toCharArray());
            } else {
              //  String realKey = new KeyManagerService().decryptInfo(configKey);
               // this.setPassword(realKey.toCharArray());
            }
        } catch (Exception e) {
            log.error("get encrypt secretkey faild.", e);
        }
    }

}
