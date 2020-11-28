package com.isharefox.share.web.property;

import lombok.Getter;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 静态资源预加载
 */
@Component
@Getter
public class AssetLoader {

    private File aliLogFile;

    static final String ALIPAY_LOGO_IMAGE_FILE_PATH = "static/images/pay/alipay.png";

    @PostConstruct
    public void init() throws IOException {
        initAlipayLogo();
    }

    /**
     * 缓存支付宝二维码内嵌logo
     */
    private void initAlipayLogo() throws IOException {
        // load image
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = AssetLoader.class.getClassLoader().getResourceAsStream(ALIPAY_LOGO_IMAGE_FILE_PATH);
            aliLogFile = File.createTempFile("alipay",".png");
            FileCopyUtils.copy(FileCopyUtils.copyToByteArray(resourceAsStream), aliLogFile);
        } finally {
            IOUtils.closeQuietly(resourceAsStream);
        }
    }
}
