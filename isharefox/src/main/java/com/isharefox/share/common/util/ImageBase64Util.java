package com.isharefox.share.common.util;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageBase64Util {

    private static final String IMAGE_TYPE_PREFIX = "data:image/png;base64,";

    /**
     * 图片转base64，用于前端 <img src="">直接展示
     * @param im
     * @return
     * @throws IOException
     */
    public static String image2Base64(RenderedImage im) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(im, "png", byteArrayOutputStream);
        String base64Img = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        return IMAGE_TYPE_PREFIX + base64Img;
    }

    /**
     * @param base64ImageSuffix 图片转成base64
     * @return 为前端页面添加前缀，方便使用
     */
    public static String appendImageTypePrefix(String base64ImageSuffix) {
        return IMAGE_TYPE_PREFIX + base64ImageSuffix;
    }

    private ImageBase64Util() {}
}
