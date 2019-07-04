//package com.yjkj.ks_user.util;
//
//import org.apache.commons.collections.map.HashedMap;
//
//import java.util.Map;
//
///**
// * @Author: Create By DaDa
// * @Date: 2019/3/25 17:38
// */
//public class test {
//    public static void main(String[] args) {
//        String msg = "【卡神】尊敬的石桐旭先生，您尾号为1234的招商银行的信用卡已经出账单了哦，可以来制定计划了，我们将为您打造完美的账单。退订回T";
//        Map<String,String> mmap = new HashedMap();
//        mmap.put("merchantId", "M48659187453946675226241");
//        mmap.put("mobile", "13522337211");
//        mmap.put("institutionId", "T00000009");
//        mmap.put("msg", msg);
//        mmap.put("type", "dx");
//        System.out.println(HttpClientUtils.doPost("http://47.104.4.155:1172/account/remind", mmap));
////        Map<String,String> map = new HashedMap();
////        map.put("type", "dx");
////        map.put("appId", "0000");
////        String url = "http://47.105.150.17/yj-mer/Products/select.shtml";
////        String result = new HttpClientUtils().doPost(url, map);
////        System.out.println(result);
//    }
//
//}
