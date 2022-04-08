package com.entity;

/*
* 因微信扫码支付的特殊性，其无法提供地址重定向功能，需要我们自己编写websocket
* 来实现功能，所以特地提供一个已经继承了websocket的支付页面和服务端，大家只需将
* 订单号、商品名称、回调地址数据通过浏览器提交过去即可 (页面form表单提交或者
* 程序内部拼接后重定向),支付接口在支付完成后会自动指定重定向功能，跳转回你指定的页面
* */

public class WeiXin {
    private Result result;
    private String type;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
