package com.kent.hisdemo.common.response;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @program: juyuan
 * @Description 微信支付回调返回数据VO类
 * @Author wuxy
 */
@Data
@XmlRootElement(name = "xml")
public class WxPayCallbackVO {

    private String appid;
    private String attach;
    private String bank_type;
    private String cash_fee;
    private String fee_type;
    private String is_subscribe;
    private String mch_id;
    private String nonce_str;
    private String openid;
    private String out_trade_no;
    private String result_code;
    private String return_code;
    private String sign;
    private String time_end;
    private String total_fee;
    private String trade_type;
    private String transaction_id;

}
