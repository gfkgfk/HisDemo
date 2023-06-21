package com.kent.hisdemo.common.exception;

public final class SLDException {

    public final static HisDemoException NO_AUTH                   = new HisDemoException("没有权限",10001);
    public final static HisDemoException PARAMETER_NOT_ALLOW_EMPTY = new HisDemoException("参数不能为空",10002);
    public final static HisDemoException DATA_DISABLED             = new HisDemoException("数据已锁定",10003);
    public final static HisDemoException DATA_DUPLICATION          = new HisDemoException("已存在相同数据",10004);
    public final static HisDemoException SERVICE_ERROR             = new HisDemoException("服务异常，请联系系统管理员",10005);
    public final static HisDemoException PARAMETER_ERROR           = new HisDemoException("参数值不对",250);

//    public final static MallException UNLOGIN                   = new MallException("未登录",501);
    public final static HisDemoException UNAUTHORITY               = new HisDemoException("未授权",251);
    public final static HisDemoException INTERNAL_ERROR            = new HisDemoException("系统内部错误",252);
    public final static HisDemoException UN_SUPPORT                = new HisDemoException("不支持业务",253);
    public final static HisDemoException UN_LOGIN                  = new HisDemoException("未登录",501);
    public final static HisDemoException CHOOSE_PAY_TYPE           = new HisDemoException("请选择支付方式",501);
    public final static HisDemoException BALANCE_NOT_ENOUGH        = new HisDemoException("余额不够",501);
    public final static HisDemoException HAVENT_PAY_CHANNEL        = new HisDemoException("没有对应的支付方式",501);
    public final static HisDemoException GET_ORDER_FAIL            = new HisDemoException("获取订单失败",501);
    public final static HisDemoException REFUND_FAIL               = new HisDemoException("退款失败，请重试",  250);
    public final static HisDemoException MEMBER_ID_NOT_BE_NULL     = new HisDemoException("会员ID不能为空，请重试！", 250);
    public final static HisDemoException MEMBER_NOT_EXSITI         = new HisDemoException("会员不存在，请重试！", 250);


}
