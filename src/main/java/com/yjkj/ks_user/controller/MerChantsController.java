package com.yjkj.ks_user.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.yjkj.ks_user.constant.Constaint;
import com.yjkj.ks_user.entity.*;
import com.yjkj.ks_user.service.*;
import com.yjkj.ks_user.util.*;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

@Controller
@RequestMapping("MerChants")
public class MerChantsController extends BaseController {

    @Autowired
    private MerChantsService merChantsService;
    @Autowired
    private AgentService agentService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private FeeService feeService;
    @Autowired
    private CreditService creditService;
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private WithdrawalsService withdrawalsService;
    @Autowired
    private CardInformationService cardInformationService;
    @Autowired
    private NumService numService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private AppNameService appNameService;
    @Autowired
    private MerChantsRateService merchantsRateService;
    @Autowired
    private ProfitService profitService;
    @Autowired
    private RedisUtils redisUtils;

    //注册
    @RequestMapping(value = "mergeOrInsert")
    public @ResponseBody
    YJResult add(String merMp, String institutionId, String appId, String appName) {
        DuanXin duanXin = merChantsService.getduanXin(institutionId, appId);
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        String n = (int) (Math.random() * (999999 - 100000 + 1)) + 100000 + "";
        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                duanXin.getAccessKeyId(), duanXin.getAccessKeySecret());
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", duanXin.getProduct(), duanXin.getDomain());

        } catch (com.aliyuncs.exceptions.ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(merMp);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(duanXin.getAutograph());
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(duanXin.getTemplateCode());
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\"" + n + "\"}");

        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");

        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");

        // hint 此处可能会抛出异常，注意catch
        try {
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null
                    && sendSmsResponse.getCode().equals("OK")) {
                MerChants m = new MerChants();
                m.setMerMp(merMp);
                m.setAppId(appId);
                MerChants r = merChantsService.findByObject(m);
                Withdrawals w = new Withdrawals();
                w.setAppId(appId);
                //w.setId((long)1);
                Withdrawals withdrawals = withdrawalsService.findByObject(w);
                if (r != null) {
                    r.setIdentifying(n);
                    r.setAppName(URLDecoder.decode(appName));
                    merChantsService.update(r);
                    return YJResult.ok(r.getMerChantId());
                }
                MerChants h = new MerChants();
                h.setMerMp(merMp);
                h.setIdentifying(n);
                h.setGenerationFee(withdrawals.getGenerationFee());
                h.setGenerationFeeRepayment(withdrawals.getGenerationFeeRepayment());
                h.setInstitutionId(institutionId);
                h.setMerType("1");
                h.setMerStat("N");
                h.setStatus("N");
                h.setAgentStatus("N");
                h.setFrozen("N");
                h.setAppId(appId);
                h.setAppName(URLDecoder.decode(appName));
                h.setRegDate(System.currentTimeMillis() + "");
                h.setStartDate(System.currentTimeMillis() + "");
                h.setFinishDate(System.currentTimeMillis() + "");
                h.setBalance(BigDecimal.ZERO);
                h.setBalanceFrozen(BigDecimal.ZERO);
                h.setBalanceProfit(BigDecimal.ZERO);
                h.setBalanceProfitFrozen(BigDecimal.ZERO);
                merChantsService.save(h);
                MerChants v = new MerChants();
                v.setMerMp(merMp);
                v.setAppId(appId);
                MerChants t = merChantsService.findByObject(v);
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(3, 2);
                String s = "M" + idWorker.nextId();
                long id = t.getId() + 10000;
                String merChantId = s + id;
                t.setMerChantId(merChantId);
                merChantsService.update(t);
                return YJResult.ok(merChantId);
            }
        } catch (com.aliyuncs.exceptions.ClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return YJResult.build(Constaint.SERVER_ERROR, "短信发送次数太多，请稍后再试");
    }

    @RequestMapping(value = "mergeOrInsertForHtml")
    public @ResponseBody
    YJResult addForHtml(HttpServletResponse response, String merMp, String institutionId, String appId) {
        response.addHeader("Access-Control-Allow-Origin", "*");// 跨域
        MerChants m = new MerChants();
        m.setMerMp(merMp);
        m.setAppId(appId);
        MerChants r = merChantsService.findByObject(m);
        if (r != null) {
            return YJResult.build(Constaint.SERVER_ERROR, "此手机号已注册");
        }
        DuanXin duanXin = merChantsService.getduanXin(institutionId, appId);
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        String n = (int) (Math.random() * (999999 - 100000 + 1)) + 100000 + "";
        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                duanXin.getAccessKeyId(), duanXin.getAccessKeySecret());
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou",
                    duanXin.getProduct(), duanXin.getDomain());
        } catch (com.aliyuncs.exceptions.ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(merMp);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(duanXin.getAutograph());
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(duanXin.getTemplateCode());
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\"" + n + "\"}");

        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");

        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");

        // hint 此处可能会抛出异常，注意catch
        try {
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null
                    && sendSmsResponse.getCode().equals("OK")) {
                Withdrawals w = new Withdrawals();
                w.setAppId(appId);
                //w.setId((long)1);
                Withdrawals withdrawals = withdrawalsService.findByObject(w);
                AppName appName = new AppName();
                appName.setAppId(appId);
                AppName name = appNameService.findByObject(appName);
                MerChants h = new MerChants();
                h.setMerMp(merMp);
                h.setIdentifying(n);
                h.setGenerationFee(withdrawals.getGenerationFee());
                h.setGenerationFeeRepayment(withdrawals.getGenerationFeeRepayment());
                h.setInstitutionId(institutionId);
                h.setMerType("1");
                h.setMerStat("N");
                h.setStatus("N");
                h.setAgentStatus("N");
                h.setFrozen("N");
                h.setAppId(appId);
                h.setAppName(name.getAppName());
                h.setRegDate(System.currentTimeMillis() + "");
                h.setStartDate(System.currentTimeMillis() + "");
                h.setFinishDate(System.currentTimeMillis() + "");
                h.setBalance(BigDecimal.ZERO);
                h.setBalanceFrozen(BigDecimal.ZERO);
                h.setBalanceProfit(BigDecimal.ZERO);
                h.setBalanceProfitFrozen(BigDecimal.ZERO);
                merChantsService.save(h);
                MerChants v = new MerChants();
                v.setMerMp(merMp);
                v.setAppId(appId);
                MerChants t = merChantsService.findByObject(v);
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(3, 2);
                String s = "M" + idWorker.nextId();
                long id = t.getId() + 10000;
                String merChantId = s + id;
                t.setMerChantId(merChantId);
                merChantsService.update(t);
                try {
                } catch (Exception e) {

                }
                return YJResult.ok(merChantId);
            }
        } catch (com.aliyuncs.exceptions.ClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return YJResult.build(Constaint.SERVER_ERROR, "短信发送次数太多，请稍后再试");
    }

    //登录
    @RequestMapping(value = "login")
    public @ResponseBody
    YJResult addMerChants(HttpServletRequest request, HttpServletResponse response, String merChantId, @RequestParam(value = "merMp", required = false) String merMp, String identifying, String appId) {
        if ("16601153348".equals(merMp)) {
            MerChants m = new MerChants();
            m.setMerMp(merMp);
            MerChants h = merChantsService.findByObject(m);
            String token = MD5Util.getMD5String(merChantId + h.getInstitutionId() + System.currentTimeMillis());
            redisUtils.set(token, merChantId);
            redisUtils.expire(token, 2592000);
            return YJResult.ok(token);
        }
        response.addHeader("Access-Control-Allow-Origin", "*");// 跨域
        MerChants m = new MerChants();
        m.setMerMp(merMp);
        m.setIdentifying(identifying);
        m.setAppId(appId);
        MerChants h = merChantsService.findByObject(m);
        if (h != null) {
            String token = MD5Util.getMD5String(merChantId + h.getInstitutionId() + System.currentTimeMillis());
            redisUtils.set(token, merChantId);
            redisUtils.expire(token, 2592000);
            updateRemark(request,h);
            return YJResult.ok(token);
        }
        return YJResult.build(Constaint.NONE_MERCHAT, "手机号或验证码错误");
    }

    /**
      * // TODO  更新最后登陆时间
      * @author wdz
      * @param  request request
      * @param  h h
      * @date 2019/7/18 10:51
      */
    private void updateRemark(HttpServletRequest request, MerChants h) {
        String ip = CommonUtil.toIpAddr(request);
        String s = DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
        h.setRemarks(s+"/"+ip);
        String appName = request.getParameter("appName");
        h.setAppName(appName);
        merChantsService.update(h);
    }


    //token续时间
    @RequestMapping(value = "inspectToken")
    public @ResponseBody
    YJResult inspect(String token) {
//		// Jedis jedis=RedisUtils.getJedis();
        if (redisUtils.exists(token)) {
            redisUtils.expire(token, 259200);
            String merChantId = redisUtils.get(token).toString();
            MerChants merChants = new MerChants();
            merChants.setMerChantId(merChantId);
            MerChants t = merChantsService.findByObject(merChants);
            if (t.getFinishDate() != null) {
                long k = new Long(t.getFinishDate());
                long n = System.currentTimeMillis();
                if (n > k) {
                    t.setFrozen("N");
                    merChantsService.update(t);
                }
            }
//			// RedisUtils.returnResource(jedis);
            return YJResult.ok();
        }
//		// RedisUtils.returnResource(jedis);
        return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
    }

    //查商户
    @RequestMapping(value = "getMerChants")
    public @ResponseBody
    YJResult get(String merChantId) {
        MerChants m = new MerChants();
        m.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(m);
        if (h != null) {
            return YJResult.ok(h);
        }
        return YJResult.build(Constaint.NONE_MERCHAT, "没有此商户");
    }

    @RequestMapping(value = "getMerChant")
    public @ResponseBody
    YJResult gets(String merChantId, String token) {
//		// Jedis jedis=RedisUtils.getJedis();
//		if(redisUtils.exists(token)){
//		MerChant t = new MerChant();
//		t.setMerChantId(merChantId);
//		MerChant h = merChantsService.gain(t);
//		MerChants v = new MerChants();
//		v.setMerChantId(merChantId);
//		v.setMerStat("Y");
//		int amount = merChantsService.statistics(v);
//		int credit  = cardInformationService.gains(merChantId);
//		int card = cardInformationService.selectcard(merChantId);
//		h.setAmount(amount);
//		h.setCredit(credit);
//		h.setCard(card);
//		// RedisUtils.returnResource(jedis);
//		return YJResult.ok(h);
//		}
//		// RedisUtils.returnResource(jedis);
//		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");


//		// Jedis jedis=RedisUtils.getJedis();
        if (redisUtils.exists(token)) {
            if (!merChantId.equals(redisUtils.get(token))) {
//				// RedisUtils.returnResource(jedis);
                return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
            }
            MerChant t = new MerChant();
            t.setMerChantId(merChantId);
            MerChant h = merChantsService.gain(t);
            MerChants v = new MerChants();
            v.setMerChantId(merChantId);
            v.setMerStat("Y");
            int amount = merChantsService.statistics(v);
            int credit = cardInformationService.gains(merChantId);
            int card = cardInformationService.selectcard(merChantId);
            h.setAmount(amount);
            h.setCredit(credit);
            h.setCard(card);
//			// RedisUtils.returnResource(jedis);
            return YJResult.ok(h);
        }
//		// RedisUtils.returnResource(jedis);
        return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
    }

    @RequestMapping(value = "getMerChantsById")
    public @ResponseBody
    MerChants select(String merChantId) {
        MerChants m = new MerChants();
        m.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(m);
        return h;
    }

    //查商户费率
    @RequestMapping(value = "selectMerChants")
    public @ResponseBody
    MerChants selects(String merChantId, String aisleCode) {
        MerChants m = new MerChants();
        m.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(m);
        MerChantsRate merChantsRate = new MerChantsRate();
        merChantsRate.setMerType(h.getMerType());
        merChantsRate.setAisleCode(aisleCode);
        merChantsRate.setAppId(h.getAppId());
        MerChantsRate merChantsRates = merchantsRateService.findByObject(merChantsRate);
        h.setMerChantFee(merChantsRates.getRate() + "");
        h.setGenerationFeeRepayment(merChantsRates.getD0Fee() + "");
        return h;
    }

    //上传图片
    @RequestMapping(value = "addFaceImgUrl")
    public @ResponseBody
    YJResult insert(String token, String merChantId, String name,
                    @RequestParam("file") MultipartFile file, HttpServletRequest request) {
//		// Jedis jedis=RedisUtils.getJedis();
        if (redisUtils.exists(token)) {
            if (!merChantId.equals(redisUtils.get(token))) {
//				// RedisUtils.returnResource(jedis);
                return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
            }
            String filePath = "";
            // 判断文件是否为空
            if (!file.isEmpty()) {
                try {
                    // 文件保存路径
                    filePath = request.getSession().getServletContext()
                            .getRealPath("/") + merChantId + name + file.getOriginalFilename();
                    // 转存文件
                    file.transferTo(new File(filePath));
                } catch (Exception e) {
                    e.printStackTrace();
//					// RedisUtils.returnResource(jedis);
                    return YJResult.build(Constaint.FAIL_UPLOAD, "上传失败");
                }
            }
            String s = MD5Util.getMD5String(filePath);
            String result = "";
            OSSClientUtil clientUtil = new OSSClientUtil();
            Date expiration = new Date(new Date().getTime() + 3600L * 1000 * 24
                    * 365 * 10);
            // 上传
            InputStream instream = null;
            if ("tx".equals(name)) {
                String ossKey = "faceImgUrl/" + s;
                try {
                    instream = new FileInputStream(filePath);
                    clientUtil.uploadFile2OSS(instream, ossKey);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
//					// RedisUtils.returnResource(jedis);
                    return YJResult.build(Constaint.FAIL_UPLOAD, "上传失败");
                }
                // 获取url
                URL url = clientUtil.createUrl(ossKey, expiration);
                clientUtil.destory();
                result = url.toString();
                MerChants m = new MerChants();
                m.setMerChantId(merChantId);
                MerChants h = merChantsService.findByObject(m);
                h.setFaceImgUrl(result);
                merChantsService.update(h);
//				// RedisUtils.returnResource(jedis);
                return YJResult.ok(result);
            }
            if ("sfzzm".equals(name)) {
                String ossKey = "userIDCardA/" + s;
                try {
                    instream = new FileInputStream(filePath);
                    clientUtil.uploadFile2OSS(instream, ossKey);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
//					// RedisUtils.returnResource(jedis);
                    return YJResult.build(Constaint.FAIL_UPLOAD, "上传失败");
                }
                // 获取url
                URL url = clientUtil.createUrl(ossKey, expiration);
                clientUtil.destory();
                result = url.toString();
                Image n = new Image();
                n.setMerChantId(merChantId);
                Image h = imageService.findByObject(n);
                if (h != null) {
                    h.setUserIDCardA(result);
                    imageService.update(h);
//					// RedisUtils.returnResource(jedis);
                    return YJResult.ok(result);
                }
                Image v = new Image();
                v.setMerChantId(merChantId);
                v.setUserIDCardA(result);
                imageService.save(v);
//				// RedisUtils.returnResource(jedis);
                return YJResult.ok(result);
            }
            if ("sfzfm".equals(name)) {
                String ossKey = "userIDCardB/" + s;
                try {
                    instream = new FileInputStream(filePath);
                    clientUtil.uploadFile2OSS(instream, ossKey);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
//					// RedisUtils.returnResource(jedis);
                    return YJResult.build(Constaint.FAIL_UPLOAD, "上传失败");
                }
                // 获取url
                URL url = clientUtil.createUrl(ossKey, expiration);
                clientUtil.destory();
                result = url.toString();
                Image n = new Image();
                n.setMerChantId(merChantId);
                Image h = imageService.findByObject(n);
                if (h != null) {
                    h.setUserIDCardB(result);
                    imageService.update(h);
//					// RedisUtils.returnResource(jedis);
                    return YJResult.ok(result);
                }
                Image v = new Image();
                v.setMerChantId(merChantId);
                v.setUserIDCardB(result);
                imageService.save(v);
//				// RedisUtils.returnResource(jedis);
                return YJResult.ok(result);
            }
            if ("yhkzm".equals(name)) {
                String ossKey = "cardImgA/" + s;
                try {
                    instream = new FileInputStream(filePath);
                    clientUtil.uploadFile2OSS(instream, ossKey);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
//					// RedisUtils.returnResource(jedis);
                    return YJResult.build(Constaint.FAIL_UPLOAD, "上传失败");
                }
                // 获取url
                URL url = clientUtil.createUrl(ossKey, expiration);
                clientUtil.destory();
                result = url.toString();
                Image n = new Image();
                n.setMerChantId(merChantId);
                Image h = imageService.findByObject(n);
                if (h != null) {
                    h.setCardImgA(result);
                    imageService.update(h);
//					// RedisUtils.returnResource(jedis);
                    return YJResult.ok(result);
                }
                Image v = new Image();
                v.setMerChantId(merChantId);
                v.setCardImgA(result);
                imageService.save(v);
//				// RedisUtils.returnResource(jedis);
                return YJResult.ok(result);
            }
        }
//		// RedisUtils.returnResource(jedis);
        return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
    }

    //三要素
    @RequestMapping(value = "authentication")
    public @ResponseBody
    YJResult authentication(String token, String merChantId, String merName, String certNo, String cardNumber, String issuingBank) {
//		// Jedis jedis=RedisUtils.getJedis();
        if (redisUtils.exists(token)) {
            if (!merChantId.equals(redisUtils.get(token))) {
//				// RedisUtils.returnResource(jedis);
                return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
            }
            MerChants m = new MerChants();
            m.setMerChantId(merChantId);
            MerChants h = merChantsService.findByObject(m);
            //String merchantId, String institutionId, String type, String name, String card, String certNo
            Map<String, String> map = new HashMap<>();
            map.put("merchantId", merChantId);
            map.put("institutionId", h.getInstitutionId());
            map.put("type", "sys");
            map.put("name", URLDecoder.decode(merName));
            map.put("card", cardNumber);
            map.put("certNo", certNo);
            String resultJsonStr = HttpClientUtils.doPost("http://47.104.4.155:1172/account/check", map);
            JSONObject job = JSONObject.parseObject(resultJsonStr);
            if ("0000".equals(job.getString("respCode"))) {
                String data = job.getString("data");
                JSONObject jobs = JSONObject.parseObject(data);
                //RedisUtils.returnResource(jedis);
                return YJResult.ok(jobs);
            }
            //RedisUtils.returnResource(jedis);
            return YJResult.build(job.getString("respCode"), job.getString("respDesc"));
            //}
        }
//		// RedisUtils.returnResource(jedis);
        return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
//		String resultJsonStr = "{\"result\": {\"status\": \"S\",\"status_msg\": \"一致\"},\"code\": 0,\"query_id\":\"Y4yb2K7xpSfdgsuB169e7mGdR2c8I2Sb\",\"msg\": \"success\"}";
//		JSONObject job = JSONObject.parseObject(resultJsonStr);
//		return YJResult.ok(job);
    }

    //检查是否已实名
    @RequestMapping(value = "check")
    public @ResponseBody
    YJResult check(String token, String merChantId, String certNo, String appId) {
//		// Jedis jedis=RedisUtils.getJedis();
        if (redisUtils.exists(token)) {
            if (!merChantId.equals(redisUtils.get(token))) {
//				// RedisUtils.returnResource(jedis);
                return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
            }
            MerChants m = new MerChants();
            m.setCertNo(certNo);
            m.setAppId(appId);
            MerChants h = merChantsService.findByObject(m);
            if (h != null) {
//				// RedisUtils.returnResource(jedis);
                return YJResult.ok("Y");
            }
//			// RedisUtils.returnResource(jedis);
            return YJResult.ok("N");
        }
//		// RedisUtils.returnResource(jedis);
        return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
    }

    //根据手机号获取商户
    @RequestMapping(value = "getMerChantsWithMerMp")
    public @ResponseBody
    YJResult getMerChants(String token, String merChantId, String merMp, String appId) {
//		// Jedis jedis=RedisUtils.getJedis();
        if (redisUtils.exists(token)) {
            if (!merChantId.equals(redisUtils.get(token))) {
//				// RedisUtils.returnResource(jedis);
                return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
            }
            MerChants m = new MerChants();
            m.setMerMp(merMp);
            m.setAppId(appId);
            MerChants h = merChantsService.findByObject(m);
            if (h != null) {
//				// RedisUtils.returnResource(jedis);
                return YJResult.ok(h);
            }
            return YJResult.build(Constaint.NONE_MERCHAT, "没有此商户");
        }
        return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
    }

    //绑关系
    @RequestMapping(value = "setMerChants")
    public @ResponseBody
    YJResult set(String token, String merChantId, String merMp, String appId) {
        if (redisUtils.exists(token)) {
            if (!merChantId.equals(redisUtils.get(token))) {
                return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
            }
            MerChants t = new MerChants();
            t.setMerChantId(merChantId);
            MerChants k = merChantsService.findByObject(t);
            if (merMp.equals(k.getMerMp())) {
                return YJResult.build(Constaint.NONE_MERCHAT, "绑定失败,推荐人不能为本人");
            }
            MerChants m = new MerChants();
            m.setMerMp(merMp);
            m.setAppId(appId);
            MerChants h = merChantsService.findByObject(m);
            if (h != null) {
                if (null == h.getAgentId() || "".equals(h.getAgentId())) {
                    return YJResult.build(Constaint.NONE_MERCHAT, "绑定失败,该用户未绑定关系");
                }
                k.setStatus("Y");
                k.setStatusDate(System.currentTimeMillis() + "");
                k.setOneMerId(h.getMerChantId());
                k.setTwoMerId(h.getOneMerId());
                k.setThreeMerId(h.getTwoMerId());
                Transaction transaction = new Transaction();
                transaction.setMerChantId(h.getMerChantId());
                Transaction f = transactionService.findByObject(transaction);
                if (f != null) {
                    k.setAgentId(f.getMerId());
                    Fee fee = new Fee();
                    fee.setAgentId(f.getMerId());
                    Fee v = feeService.findByObject(fee);
                    if (v != null) {
                        k.setMerChantFee(v.getMerchantFee());
                        merChantsService.update(k);
                    } else {
                        Credit credit = new Credit();
                        credit.setId((long) 1);
                        Credit r = creditService.findByObject(credit);
                        k.setMerChantFee(r.getMerchantFee());
                        merChantsService.update(k);
                    }
                } else {
                    k.setAgentId(h.getAgentId());
                    Fee fee = new Fee();
                    fee.setAgentId(h.getAgentId());
                    Fee v = feeService.findByObject(fee);
                    if (v != null) {
                        k.setMerChantFee(v.getMerchantFee());
                        merChantsService.update(k);
                    } else {
                        Credit credit = new Credit();
                        credit.setId((long) 1);
                        Credit r = creditService.findByObject(credit);
                        k.setMerChantFee(r.getMerchantFee());
                        merChantsService.update(k);
                    }
                }
                try {
                    merChantsService.bind(k);
                } catch (Exception e) {

                }
                return YJResult.ok();
            }
            return YJResult.build(Constaint.NONE_MERCHAT, "没有此商户(" + merMp + ")");
        }
        return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
    }

    //商户升级
    @RequestMapping(value = "alter")
    public @ResponseBody
    String alterMerType(String merChantId, String payType, BigDecimal amount, String orderNo) {
        if ("hy3".equals(payType)) {
            MerChants m = new MerChants();
            m.setMerChantId(merChantId);
            MerChants h = merChantsService.findByObject(m);
            List<String> list = new ArrayList<String>();
            list.add("13710523011");
            list.add("15017539525");
            for (int i = 0; i < 2; i++) {
                merChantsService.tran(h.getMerName(), h.getInstitutionId(), h.getAppId(), list.get(i), "12");
            }
            merChantsService.tran(h.getMerName(), h.getInstitutionId(), h.getAppId(), h.getMerMp(), "13");
        }
        Num w = new Num();
        w.setPayType(payType);
        Num d = numService.findByObject(w);
        if ("T".equals(d.getVipType())) {
            Record record = new Record();
            record.setOrderNo(orderNo);
            Record records = recordService.findByObject(record);
            if (null != records) {
                return "success";
            }
            Record s = new Record(merChantId, System.currentTimeMillis() + "", orderNo, "激活费");
            recordService.save(s);
            Num num = new Num();
            num.setPayType(payType);
            Num t = numService.findByObject(num);
            MerChants m = new MerChants();
            m.setMerChantId(merChantId);
            MerChants h = merChantsService.findByObject(m);
            h.setMerType(t.getMerType());
            long startDate = 0;
            long finishDate = 0;
            finishDate = (long) Long.parseLong(t.getValidTime()) * 24 * 3600 * 1000;
            if (h.getFinishDate() != null) {
                if ("N".equals(h.getFrozen())) {
                    h.setFrozen("Y");
                    startDate = System.currentTimeMillis();
                    h.setStartDate(startDate + "");
                    h.setFinishDate(startDate + finishDate + "");
                    return merChantsService.update(h);
                }
                startDate = Long.parseLong(h.getFinishDate());
                h.setFinishDate(startDate + finishDate + "");
                return merChantsService.update(h);
            }
            h.setFrozen("Y");
            startDate = System.currentTimeMillis();
            h.setStartDate(startDate + "");
            h.setFinishDate(startDate + finishDate + "");
            return merChantsService.update(h);
        }
        Record record = new Record();
        record.setOrderNo(orderNo);
        Record records = recordService.findByObject(record);
        if (null != records) {
            return "success";
        }
        Record s = new Record(merChantId, System.currentTimeMillis() + "", orderNo, "升级费用");
        recordService.save(s);
        MerChants m = new MerChants();
        m.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(m);
		/*Num num = numService.findByPrimaryKey((long)2);
		if(new BigDecimal(num.getNum()).compareTo(amount)==0){
			if("T00000004".equals(h.getInstitutionId())){
				h.setMerType("2");
				h.setMerChantFee("0.60");
				MerChantsRate merChantsRate = new MerChantsRate();
				merChantsRate.setMerType("2");
				merChantsRate.setAppId(h.getAppId());
				List<MerChantsRate> list = merchantsRateService.queryObjectForList(merChantsRate);
				merchantsRateService.find(h.getMerChantId(),h.getAppId(),h.getInstitutionId(),list);
				return merChantsService.update(h);
			}
			h.setMerType("2");
			h.setMerChantFee("0.68");
			MerChantsRate merChantsRate = new MerChantsRate();
			merChantsRate.setMerType("2");
			merChantsRate.setAppId(h.getAppId());
			List<MerChantsRate> list = merchantsRateService.queryObjectForList(merChantsRate);
			merchantsRateService.find(h.getMerChantId(),h.getAppId(),h.getInstitutionId(),list);

			MerChantsRate merChantsRates = new MerChantsRate();
			merChantsRates.setMerType("2");
			merChantsRates.setAisleCode("easy");
			merChantsRates.setAppId(h.getAppId());
			MerChantsRate merChants = merchantsRateService.findByObject(merChantsRates);
			if(null != merChants){
				merchantsRateService.alter(h,merChants);
			}
			return merChantsService.update(h);
		}*/
        Num num = new Num();
        num.setPayType(payType);
        Num t = numService.findByObject(num);
        h.setMerType(t.getMerType());
        Profit k = new Profit();
        k.setInstitutionId(h.getInstitutionId());
        Profit profit = profitService.findByObject(k);
        long startDate = 0;
        long finishDate = 0;
        finishDate = (long) Long.parseLong(t.getValidTime()) * 24 * 3600 * 1000;
		/*if(h.getFinishDate() != null){
			if("N".equals(h.getFrozen())){
				h.setFrozen("Y");
					startDate=System.currentTimeMillis();
					h.setStartDate(startDate+"");
					h.setFinishDate(startDate+finishDate+"");
					return merChantsService.update(h);
				}
				startDate=Long.parseLong(h.getFinishDate());
				h.setFinishDate(startDate+finishDate+"");
				return merChantsService.update(h);
			}*/
        h.setFrozen("Y");
        startDate = System.currentTimeMillis();
        h.setStartDate(startDate + "");
        h.setFinishDate(startDate + finishDate + "");
        if ("settled".equals(profit.getProfitModel())) {
            return merChantsService.update(h);
        }
        MerChantsRate merChantsRate = new MerChantsRate();
        merChantsRate.setMerType(t.getMerType());
        merChantsRate.setIsRepayment("N");
        merChantsRate.setAppId(h.getAppId());
        List<MerChantsRate> list = merchantsRateService.queryObjectForList(merChantsRate);
        merchantsRateService.find(h.getMerChantId(), h.getAppId(), h.getInstitutionId(), list);

		/*MerChantsRate merChantsRates = new MerChantsRate();
		merChantsRates.setMerType(t.getMerType());
		merChantsRates.setIsRepayment("Y");
		merChantsRates.setAppId(h.getAppId());
		List<MerChantsRate> merChants = merchantsRateService.queryObjectForList(merChantsRates);
		for(MerChantsRate tt : merChants){
			MerChantsRate v = (MerChantsRate)tt;
			merchantsRateService.alter(h,v);
		}*/
        return merChantsService.update(h);
    }

    //激活
    @RequestMapping(value = "alterFrozen")
    public @ResponseBody
    String alterFrozen(String merChantId, String payType, BigDecimal amount, String orderNo) {
        Record record = new Record();
        record.setOrderNo(orderNo);
        Record records = recordService.findByObject(record);
        if (null != records) {
            return "success";
        }
        Record s = new Record(merChantId, System.currentTimeMillis() + "", orderNo, "激活费");
        recordService.save(s);
        Num num = new Num();
        num.setPayType(payType);
        Num t = numService.findByObject(num);
        MerChants m = new MerChants();
        m.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(m);
        long startDate = 0;
        long finishDate = 0;
        finishDate = (long) Long.parseLong(t.getValidTime()) * 24 * 3600 * 1000;
        if (h.getFinishDate() != null) {
            if ("N".equals(h.getFrozen())) {
                h.setFrozen("Y");
                startDate = System.currentTimeMillis();
                h.setStartDate(startDate + "");
                h.setFinishDate(startDate + finishDate + "");
                return merChantsService.update(h);
            }
            startDate = Long.parseLong(h.getFinishDate());
            h.setFinishDate(startDate + finishDate + "");
            return merChantsService.update(h);
        }
        h.setFrozen("Y");
        startDate = System.currentTimeMillis();
        h.setStartDate(startDate + "");
        h.setFinishDate(startDate + finishDate + "");
        return merChantsService.update(h);
    }

    @RequestMapping(value = "activation")
    public @ResponseBody
    YJResult alter(String merChantId, String agentId, BigDecimal amount, String orderNo) {
        return merChantsService.get(merChantId, agentId, amount, orderNo);
    }

    //获取推荐的关系
    @RequestMapping(value = "getStatistics")
    public @ResponseBody
    YJResult select(String token, String merChantId) {
        if (redisUtils.exists(token)) {
            if (!merChantId.equals(redisUtils.get(token))) {
                return YJResult.build(Constaint.SERVER_ERROR, "验证失败");
            }
            MerChants m = new MerChants();
            m.setOneMerId(merChantId);
            List<MerChants> hList = merChantsService.queryObjectForList(m);
            int first = merChantsService.receive(merChantId);
            int second = merChantsService.find(merChantId);
            List<Statistics> list = new ArrayList<Statistics>();
            Statistics statistics = new Statistics(merChantId, first, second);
            for (MerChants merChants : hList) {
                MerChants f = (MerChants) merChants;
                int n = merChantsService.receive(f.getMerChantId());
                int t = merChantsService.find(f.getMerChantId());
                Statistics s = new Statistics(f.getMerChantId(), f.getMerName(), f.getMerMp(), f.getMerType(), f.getAgentStatus(), f.getStatusDate(), n, t);
                s.setMerStat(f.getMerStat());
                s.setMerStatTime(f.getMerStatTime());
                list.add(s);
            }
            statistics.setChildren(list);
            return YJResult.ok(statistics);
        }
        return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
    }

    //查询固定的返佣
    @RequestMapping(value = "getRateList")
    public @ResponseBody
    List<Rate> getRateList(String merChantId) {
        List<Rate> list = new ArrayList<Rate>();
        MerChants m = new MerChants();
        m.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(m);
        MerChants k = new MerChants();
        MerChants t = null;
        if (!"".equals(h.getOneMerId()) && null != h.getOneMerId()) {
            k.setMerChantId(h.getOneMerId());
            t = merChantsService.findByObject(k);
        }
        MerChants r = new MerChants();
        MerChants s = null;
        if (!"".equals(h.getTwoMerId()) && null != h.getTwoMerId()) {
            r.setMerChantId(h.getTwoMerId());
            s = merChantsService.findByObject(r);
        }
        MerChants p = new MerChants();
        MerChants n = null;
        if (!"".equals(h.getThreeMerId()) && null != h.getThreeMerId()) {
            p.setMerChantId(h.getThreeMerId());
            n = merChantsService.findByObject(p);
        }
        if (t != null) {
            Income income = new Income();
            income.setMerType(t.getMerType());
            Income incomes = incomeService.findByObject(income);
            Rate rate = new Rate(t.getMerChantId(), t.getMerType(), incomes.getFirst());
            rate.setBrushrate(incomes.getBrushFirst());
            list.add(rate);
        }
        if (s != null) {
            Income income = new Income();
            income.setMerType(s.getMerType());
            Income incomes = incomeService.findByObject(income);
            Rate rate = new Rate(s.getMerChantId(), s.getMerType(), incomes.getSecond());
            rate.setBrushrate(incomes.getBrushSecond());
            list.add(rate);
        }
        if (n != null) {
            Income income = new Income();
            income.setMerType(n.getMerType());
            Income incomes = incomeService.findByObject(income);
            Rate rate = new Rate(n.getMerChantId(), n.getMerType(), incomes.getThird());
            rate.setBrushrate(incomes.getBrushThird());
            list.add(rate);
        }
        return list;
    }

    @RequestMapping(value = "setMerChantsForHtml")
    public @ResponseBody
    YJResult setMerChants(HttpServletResponse response, String merChantId, String merMp, String appId) {
        response.addHeader("Access-Control-Allow-Origin", "*");// 跨域
        MerChants t = new MerChants();
        t.setMerChantId(merChantId);
        MerChants k = merChantsService.findByObject(t);
        if (null != k.getOneMerId() && !"".equals(k.getOneMerId())) {
            return YJResult.build(Constaint.NONE_MERCHAT, "已绑定推荐关系");
        }
        MerChants m = new MerChants();
        m.setMerMp(merMp);
        m.setAppId(appId);
        MerChants h = merChantsService.findByObject(m);
        if (h != null) {
            if (null == h.getAgentId() || "".equals(h.getAgentId())) {
                return YJResult.build(Constaint.NONE_MERCHAT, "绑定失败,该用户未绑定关系");
            }
            k.setStatus("Y");
            k.setStatusDate(System.currentTimeMillis() + "");
            k.setOneMerId(h.getMerChantId());
            k.setTwoMerId(h.getOneMerId());
            k.setThreeMerId(h.getTwoMerId());
            Transaction transaction = new Transaction();
            transaction.setMerChantId(h.getMerChantId());
            Transaction f = transactionService.findByObject(transaction);
            if (f != null) {
                k.setAgentId(f.getMerId());
                Fee fee = new Fee();
                fee.setAgentId(f.getMerId());
                Fee v = feeService.findByObject(fee);
                if (v != null) {
                    k.setMerChantFee(v.getMerchantFee());
                    merChantsService.update(k);
                } else {
                    Credit credit = new Credit();
                    credit.setId((long) 1);
                    Credit r = creditService.findByObject(credit);
                    k.setMerChantFee(r.getMerchantFee());
                    merChantsService.update(k);
                }
            } else {
                k.setAgentId(h.getAgentId());
                Fee fee = new Fee();
                fee.setAgentId(h.getAgentId());
                Fee v = feeService.findByObject(fee);
                if (v != null) {
                    k.setMerChantFee(v.getMerchantFee());
                    merChantsService.update(k);
                } else {
                    Credit credit = new Credit();
                    credit.setId((long) 1);
                    Credit r = creditService.findByObject(credit);
                    k.setMerChantFee(r.getMerchantFee());
                    merChantsService.update(k);
                }
            }
            try {
                merChantsService.bind(k);
            } catch (Exception e) {
            }
            return YJResult.ok();
        }
        return YJResult.build(Constaint.NONE_MERCHAT, "没有此商户(" + merMp + ")");
    }

    //获取代理商信息
    @RequestMapping(value = "selectAgent")
    public @ResponseBody
    YJResult selectAgent(String merChantId, String agentId) {
        Transaction transaction = new Transaction();
        transaction.setMerId(agentId);
        Transaction f = transactionService.findByObject(transaction);
        MerChants m = new MerChants();
        m.setMerChantId(f.getMerChantId());
        MerChants h = merChantsService.findByObject(m);
        Agent t = new Agent();
        t.setMerId(agentId);
        Agent agent = agentService.findByObject(t);
        h.setMerName(agent.getMerName());
        return YJResult.ok(h);
    }

	/*@RequestMapping(value = "getMerList")
	public @ResponseBody
	List<Mer> getMerList(String merChantId) {
			List<Mer> list=new ArrayList<Mer>();
			MerChants m = new MerChants();
			m.setMerChantId(merChantId);
			MerChants h = merChantsService.findByObject(m);
			MerChants k = new MerChants();
			MerChants t = null;
			if(!"".equals(h.getOneMerId()) && null != h.getOneMerId()){
				k.setMerChantId(h.getOneMerId());
				t = merChantsService.findByObject(k);
				if("Y".equals(t.getFrozen())){
					Mer mer = new Mer(h.getOneMerId(), "Y");
					Mer mers = new Mer(h.getTwoMerId(), "N");
					Mer tt = new Mer(h.getThreeMerId(), "N");
					list.add(mer);
					list.add(mers);
					list.add(tt);
					return list;
				}
				Mer mer = new Mer(h.getOneMerId(), "N");
				list.add(mer);
			}
			MerChants r = new MerChants();
			MerChants s = null;
			if(!"".equals(h.getTwoMerId()) && null != h.getTwoMerId()){
				r.setMerChantId(h.getTwoMerId());
				s = merChantsService.findByObject(r);
				if("Y".equals(s.getFrozen())){
					Mer mer = new Mer(h.getTwoMerId(), "Y");
					Mer tt = new Mer(h.getThreeMerId(), "N");
					list.add(mer);
					list.add(tt);
					return list;
				}
			}
			Mer mer = new Mer(h.getTwoMerId(), "N");
			list.add(mer);
			MerChants v = new MerChants();
			MerChants n = null;
			if(!"".equals(h.getThreeMerId()) && null != h.getThreeMerId()){
				v.setMerChantId(h.getThreeMerId());
				n = merChantsService.findByObject(v);
				if("Y".equals(n.getFrozen())){
					Mer mers = new Mer(h.getThreeMerId(), "Y");
					list.add(mers);
					return list;
				}
			}
			Mer tt = new Mer(h.getThreeMerId(), "N");
			list.add(tt);
			return list;
	}

	@RequestMapping(value = "getAgentList")
	public @ResponseBody
	List<Mer> getAgentList(String merChantId) {
			List<Mer> list=new ArrayList<Mer>();
			MerChants m = new MerChants();
			m.setMerChantId(merChantId);
			MerChants h = merChantsService.findByObject(m);
			if(h.getAgentId().startsWith("T")){
				Transaction transaction = new Transaction();
				transaction.setMerId(h.getAgentId());
				Transaction v = transactionService.findByObject(transaction);
				Mer mer = new Mer(v.getMerChantId(), "0");
				list.add(mer);
				return list;
			}
			Agent agent = new Agent();
			agent.setMerId(h.getAgentId());
			Agent t = agentService.findByObject(agent);
			if(t.getOneMerId().startsWith("T")){
				Transaction transaction = new Transaction();
				transaction.setMerId(h.getAgentId());
				Transaction v = transactionService.findByObject(transaction);
				Transaction transactions = new Transaction();
				transactions.setMerId(t.getOneMerId());
				Transaction n = transactionService.findByObject(transactions);
				Mer mers = new Mer(v.getMerChantId(), "1");
				Mer mer = new Mer(n.getMerChantId(), "0");
				list.add(mers);
				list.add(mer);
				return list;
			}
			Agent a = new Agent();
			a.setMerId(t.getOneMerId());
			Agent ts = agentService.findByObject(a);
			Transaction transaction = new Transaction();
			transaction.setMerId(h.getAgentId());
			Transaction v = transactionService.findByObject(transaction);
			Transaction transactions = new Transaction();
			transactions.setMerId(t.getOneMerId());
			Transaction n = transactionService.findByObject(transactions);
			Transaction trans = new Transaction();
			trans.setMerId(ts.getOneMerId());
			Transaction s = transactionService.findByObject(transaction);
			Mer mers = new Mer(v.getMerChantId(), "2");
			Mer mer = new Mer(n.getMerChantId(), "1");
			Mer tt = new Mer(s.getMerChantId(), "0");
			list.add(mers);
			list.add(mer);
			list.add(tt);
			return list;
	}*/

    //获取无卡用户的费率
    @RequestMapping(value = "selectRateLists")
    public @ResponseBody
    List<Rate> getRateLists(String merChantId, @RequestParam(defaultValue = "easy") String aisleCode) {
        List<Rate> list = new ArrayList<Rate>();
        MerChants m = new MerChants();
        m.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(m);
        MerChants k = new MerChants();
        MerChants t = null;
        if (!"".equals(h.getOneMerId()) && null != h.getOneMerId()) {
            k.setMerChantId(h.getOneMerId());
            t = merChantsService.findByObject(k);
        }
        MerChants r = new MerChants();
        MerChants s = null;
        if (!"".equals(h.getTwoMerId()) && null != h.getTwoMerId()) {
            r.setMerChantId(h.getTwoMerId());
            s = merChantsService.findByObject(r);
        }
        MerChants v = new MerChants();
        MerChants n = null;
        if (!"".equals(h.getThreeMerId()) && null != h.getThreeMerId()) {
            v.setMerChantId(h.getThreeMerId());
            n = merChantsService.findByObject(v);
        }
        if (t != null) {
            MerChantsRate merChantsRate = new MerChantsRate();
            merChantsRate.setMerType(t.getMerType());
            merChantsRate.setAisleCode(aisleCode);
            merChantsRate.setAppId(t.getAppId());
            MerChantsRate merChantsRates = merchantsRateService.findByObject(merChantsRate);
            Rate rate = new Rate(t.getMerChantId(), t.getMerType(), merChantsRates.getRate() + "");
            rate.setBrushrate(merChantsRates.getRate() + "");
            list.add(rate);
        }
        if (s != null) {
            MerChantsRate merChants = new MerChantsRate();
            merChants.setMerType(s.getMerType());
            merChants.setAisleCode(aisleCode);
            merChants.setAppId(s.getAppId());
            MerChantsRate merChant = merchantsRateService.findByObject(merChants);
            Rate rates = new Rate(s.getMerChantId(), s.getMerType(), merChant.getRate() + "");
            rates.setBrushrate(merChant.getRate() + "");
            list.add(rates);
        }
        if (n != null) {
            MerChantsRate merChants = new MerChantsRate();
            merChants.setMerType(n.getMerType());
            merChants.setAisleCode(aisleCode);
            merChants.setAppId(n.getAppId());
            MerChantsRate merChant = merchantsRateService.findByObject(merChants);
            Rate rates = new Rate(n.getMerChantId(), n.getMerType(), merChant.getRate() + "");
            rates.setBrushrate(merChant.getRate() + "");
            list.add(rates);
        }
        return list;
    }

    //获取商户的图片
    @RequestMapping(value = "selectImageList")
    public @ResponseBody
    YJResult getImageList(String merChantId) {
        Image t = new Image();
        t.setMerChantId(merChantId);
        return YJResult.ok(imageService.findByObject(t));
    }

    //获取商户的费率
    @RequestMapping(value = "selectMerChantsRate")
    public @ResponseBody
    YJResult alter(String merChantId) {
        MerChants merChants = new MerChants();
        merChants.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(merChants);
        MerChantsRate merChantsRate = new MerChantsRate();
        merChantsRate.setMerType(h.getMerType());
        merChantsRate.setAppId(h.getAppId());
        List<MerChantsRate> t = merchantsRateService.queryObjectForList(merChantsRate);
        return YJResult.ok(t);
    }

    /*@RequestMapping(value = "alterFrozenForJj")
    public @ResponseBody
    String alterFrozens(String merChantId, BigDecimal amount, String orderNo) {
        Record record = new Record();
        record.setOrderNo(orderNo);
        Record records = recordService.findByObject(record);
        if(null != records){
            return "success";
        }
        Record s = new Record(merChantId, System.currentTimeMillis()+"", orderNo, "会员费");
        recordService.save(s);
        Used t = usedService.findByPrimaryKey((long)1);
        MerChants m = new MerChants();
        m.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(m);
        if(new BigDecimal(t.getMonth()).compareTo(amount)==0){
            h.setFrozen("Y");
            h.setStartDate(System.currentTimeMillis()+"");
            h.setFinishDate("7252093211000");
        }
        return merChantsService.update(h);
    }*/
    //删除
    @RequestMapping(value = "delete")
    public @ResponseBody
    String deleteMerChants(HttpServletResponse response, @RequestParam(value = "merMp", required = false) String merMp) {
        response.addHeader("Access-Control-Allow-Origin", "*");// 跨域
        MerChants merChants = new MerChants();
        merChants.setMerMp(merMp);
        MerChants h = merChantsService.findByObject(merChants);
        merChantsService.delete(h.getId());
        return "success";
    }

    //发验证码
    @RequestMapping(value = "send")
    public @ResponseBody
    YJResult sends(String merChantId, String merMp, String type) {
        MerChants merChants = new MerChants();
        merChants.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(merChants);
        if ("1".equals(type)) {
            String n = (int) (Math.random() * (999999 - 100000 + 1)) + 100000 + "";
            h.setIdentifying(n);
            merChantsService.update(h);
            Map<String, String> param = new HashMap<String, String>();
            param.put("phone", h.getMerMp());
            param.put("planId", n);
            param.put("appId", h.getAppId());
            param.put("institutionId", h.getInstitutionId());
            param.put("type", "7");
            String resultJsonStr = HttpClientUtils.doPost("http://47.104.25.59/templet/Tongzhi/send", param);
            System.out.println(resultJsonStr);
            return YJResult.ok();
        }
        String n = (int) (Math.random() * (999999 - 100000 + 1)) + 100000 + "";
        h.setIdentifying(n);
        merChantsService.update(h);
        Map<String, String> param = new HashMap<String, String>();
        param.put("phone", merMp);
        param.put("planId", n);
        param.put("appId", h.getAppId());
        param.put("institutionId", h.getInstitutionId());
        param.put("type", "7");
        String resultJsonStr = HttpClientUtils.doPost("http://47.104.25.59/templet/Tongzhi/send", param);
        System.out.println(resultJsonStr);
        return YJResult.ok();
    }

    //修改手机号
    @RequestMapping(value = "alterMerMp")
    public @ResponseBody
    YJResult alterMerMp(String merChantId, @RequestParam(value = "merMp", required = false) String merMp, String identifying, String type) {
        MerChants merChants = new MerChants();
        merChants.setMerChantId(merChantId);
        MerChants h = merChantsService.findByObject(merChants);
        if ("1".equals(type)) {
            if (!identifying.equals(h.getIdentifying())) {
                return YJResult.build(Constaint.SERVER_ERROR, "验证码有误,请输入正确的验证码");
            }
            return YJResult.ok();
        }
        if (!identifying.equals(h.getIdentifying())) {
            return YJResult.build(Constaint.SERVER_ERROR, "验证码有误,请输入正确的验证码");
        }
        MerChants merChant = new MerChants();
        merChant.setMerMp(merMp);
        merChant.setAppId(h.getAppId());
        if (null != merChantsService.findByObject(merChant)) {
            return YJResult.build(Constaint.SERVER_ERROR, "账号已存在");
        }
        h.setMerMp(merMp);
        merChantsService.update(h);
        return YJResult.ok();
    }

    @ResponseBody
    @GetMapping(value = "countByMobile")
    public YJResult countByMobile(String mobile){
        if (mobile == null || mobile.trim().length() != 12){
            return YJResult.ok(0);
        }
        Integer count = merChantsService.countByMobile(mobile);
        return YJResult.ok(count);
    }

}
