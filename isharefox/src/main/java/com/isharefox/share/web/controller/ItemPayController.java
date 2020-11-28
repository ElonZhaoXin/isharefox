package com.isharefox.share.web.controller;

import com.isharefox.share.common.qrcode.QrcodeUtils;
import com.isharefox.share.trade.pay.PayService;
import com.isharefox.share.trade.pay.alipay.model.TradePrecreateReqeust;
import com.isharefox.share.trade.pay.alipay.model.TradePrecreateResponse;
import com.isharefox.share.web.property.AssetLoader;
import com.isharefox.share.web.view.Constant;
import com.isharefox.share.web.view.PageFactory;
import com.isharefox.share.web.view.QrPage;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Api(value = "商品支付服务")
@Controller
@RequestMapping("/item")
@AllArgsConstructor
@Slf4j
public class ItemPayController {

    final PayService payService;

    final PageFactory pageFactory;

    final AssetLoader assetLoader;

    /**
     * @param itemId 资源
     * @return 返回该资源的二维码支付页面
     */
    @GetMapping("/{itemId}")
    public String share(@RequestParam(required = false) @PathVariable String itemId, Model model) {
        TradePrecreateReqeust reqeust = TradePrecreateReqeust.builder()
                .orderId("202011150001"+ new Random().nextInt(1000000))
                .amount("10.00")
                .title("资源").build();
        TradePrecreateResponse response = payService.tradePrecreate(reqeust);
        String qrImageStringBase64 = QrcodeUtils.createQrcodeBase64(response.getQrCode(), assetLoader.getAliLogFile());
        //构造页面属性
        QrPage qrPage = pageFactory.buildQrPage();
        qrPage.setQrCodeBase64("data:image/png;base64," + qrImageStringBase64);
        model.addAttribute(Constant.ATTRIBUTE_NAME_PAGE, qrPage);

        log.info("生成orderid：" + reqeust.getOrderId());
        return "qrpage";
    }
}
