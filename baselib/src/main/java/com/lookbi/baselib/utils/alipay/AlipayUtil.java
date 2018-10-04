package com.lookbi.baselib.utils.alipay;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.lookbi.baselib.utils.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;


/**
 * Created by zhangyisheng on 2017/6/22.
 */

public class AlipayUtil {
    private static AlipayUtil mUtil;
    public static final String APPID = "2018090361261266";
    //商户私钥，旧版为pkcs8格式，新版为2048格式
    public static final String RSA_PRIVATE ="MIIEugIBADANBgkqhkiG9w0BAQEFAASCBKQwggSgAgEAAoIBAQCNWYd75Om9hv9ycp5AxX4mipSUZFVv0A5Pd3ARVQ69SgeIRj93X6WnaXYuEsRnUCRJv2qr+OVXnr01fsBImRBkQGov7GDQqtU9mx4oF/1LDb/XTJOtGGF6deXapY2bjLeqKLo8n55K38iN9oCgMnykqbDmMeNjIRMVe8t/WUuQ5YzeoIeRSoxGTadP+rMDmQT2cGuPXMstGfsnwNogDQZ32NviVeZL/WXWsJtHsmly1b+cjRGi66py9pV7rPOxfMDc6yywqGNgW8o7F0aJlFgK4abTajOhAX6CoXTw4NwPv50snvducxx+vkItPeljej9D/lVAWYoEb3lORFUB8zQ3AgMBAAECggEAQGkTu/lMKfliXhys0IcuPRHZMuWlqRX972hVwRApb0hUtlyMfzEuo2MDX2td5FY4swJu8X6xw4OqZL4lkO9u8MAJo1zroSq3kSMmUs7btV/Gk61pfe8mPE+NNl1Kch9lcjys4QM7dpMTKN2zL0zST5aj7cG+BGYF9Dw9NwLR1GyRZ2qOr1vEvrgWbn3JNLoTa+UoOXIQomqEVXs3YtriuXJFuU/+AecoBRPOv32i2cYXxxQKOo+/atCuGzmdN6Q/m0DnakPIocxGRjhrEZGv7Vus7vkN24CD303EY0yM2m9YQnWIfKcayrf+otEUxcMGoMYFrNmFSXw5xy4t/j4owQKBgQD96W5tjoSy9GChoR6Sd2TYzksfnQcoraFKIazKSerFYvbFqd2fp1ddMJWfR50vgaXM8y/hjrEBdtXxu/OoTYa2saaRpiyfV07I80ExTK29A7heGkqu570ACO/zQXJab+cWnLV9y8isE0kXSfrHQ74z8j8WD6STILibSuRcdti6HQKBgQCOgx4GUAnpRvsJVHBB2tWIwQAGE/yI4U36eGan9T+gTHTrH4b1FpSHr8+EKsTm5lgNblrGwYH2utdA9rmzwTbIi2jKrNkBo4OTMTeFRs3EhownPA4O796GS3VDi30uPifHNx99LsXxiSC8qNYqxk+ncBZI+ORDvTq2wfPHnBM3YwJ/HcE9L5mD83Sv3kEDVCapqr6CDzJXpGy0eft4RDxlRrxSLte5IPx7QHBxGDlMMkT/KQVWlxghpgpHCAz+gLqVDtPCsU7H6+RPVz0qZpyVv5CwClcKaXS+sUJa3CbPP8ndSskSe6tl2MRpzFDfEnrdzziULAGpR5SODaS5HhzPSQKBgGZYIULVThjaYcQUgL26g2k/eV3kEpLWguKifs6V4Mikv75vGMOAINDE4JmBAYD04T1cCBL+uu/OxHEh/sDpvAcPnwUWVo3HuOEHEmFDNNiy64ntpgbyGnOFU0Rbs113Q6fcnV+QLJVGLl1uPsffPSr44vgqzfsbtUO0S/cIzgATAoGAZ+u0nIPvmV9ocDVFKaXjOyedYXNXZfijMzkeSL2D/quD7cU8ym0ZzZ2J75DIk68tyz07WscYsn2Keo+/LQjkUZ8t82FlkOi4YVfktLVd/4PY/2ohT2wXyk4fqiB5rMhK3wF2JsPjc+F+5wG1nf7+rl7tyGSjSKj04BhF8wZmZRk=";
    boolean rsa2 = true;
    private static final int SDK_PAY_FLAG = 1;
    private String OutTradeNo; //商户唯一支付订单号
    private static final int SDK_AUTH_FLAG = 2;
    //测试回调地址
    private static final String NOTIFY_URL = "http://xzyp.ketao.com/api/trade/notify/alipay";
    //正式回调地址
//    private static final String NOTIFY_URL = "http://xzyp.1netbi.com/api/trade/notify/alipay";
//    private Context context;

//    public AlipayUtil(Context context){
//        this.context=context;
//    }

    public static AlipayUtil getInstance() {
        if (mUtil == null) {
            mUtil = new AlipayUtil();

        }
        return mUtil;
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    LogUtil.e("resultInfo=" + resultInfo + "---resultStatus=" + resultStatus);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        Toast.makeText(PayDemoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        if (callback != null) {
                            callback.success(OutTradeNo, resultInfo);
                        }
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        if (callback != null) {
                            callback.fail(resultInfo);
                        }
//                        Toast.makeText(PayDemoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
//                        Toast.makeText(PayDemoActivity.this,
//                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
//                                .show();
                        LogUtil.e("授权成功");
                    } else {
                        // 其他状态值则为授权失败
//                        Toast.makeText(PayDemoActivity.this,
//                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();
                        LogUtil.e("授权失败");
                        if (callback != null) {
                            callback.authFail();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    /**
     * call alipay sdk pay. 调用SDK支付
     */
    public void pay(final Activity activity, String body, String price, AlipayCallBack callback) {
        this.OutTradeNo = body;
        this.callback = callback;



        Map<String, String> params = OrderInfoUtil.buildOrderParamMap(APPID, body, price, "小臻优品-" + body, NOTIFY_URL, rsa2);
        String orderParam = OrderInfoUtil.buildOrderParam(params);

        String privateKey = RSA_PRIVATE;
        String sign = OrderInfoUtil.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
//                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }


    /**
     * create the order info. 创建订单信息
     */
    /*public String getOrderInfo(String subject, String body, String price) {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        //OutTradeNo=getOutTradeNo();
        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + OutTradeNo + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://miaoguo.lookbi.com/api/trade/notify/alipay"
                + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }*/

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    public String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);
        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    public String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE, false);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    public String getSignType() {
        return "sign_type=\"RSA\"";
    }

    public AlipayCallBack callback;

    public interface AlipayCallBack {
        void success(String ordernumber, String resultInfo);

        void fail(String resultInfo);

        void authFail();
    }
}
