//package com.yjkj.ks_user.controller;
//
//import org.apache.http.HttpResponse;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * @Author: Create By DaDa
// * @Date: 2019/4/29 15:27
// */
//@Controller
//@RequestMapping("html")
//public class HttpController  extends HttpServlet {
//    @RequestMapping("test")
//    public void getHtml(HttpServletResponse response) throws IOException {
//        response.setContentType("text/html; charset=UTF-8");
//        String s ="<html>\n" +
//                "<head>\n" +
//                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
//                "<title>Gateway-Response</title>\n" +
//                "</head>\n" +
//                "<body onload=\"OnLoadSubmit();\">\n" +
//                "<form id=\"gatewayform\" action=\"https://cashier.95516.com/b2c/api/Activate.action\" method=\"post\">\n" +
//                "<input type=\"hidden\" name=\"txnType\" id=\"txnType\" value=\"79\"/>\n" +
//                "<input type=\"hidden\" name=\"payTimeout\" id=\"payTimeout\" value=\"20190429160637\"/>\n" +
//                "<input type=\"hidden\" name=\"frontUrl\" id=\"frontUrl\" value=\"http://apitest2.tfb8.com/cgi-bin/v2.0/api_zyqpay_page_cb.cgi?page_url=MTI3LjAuMC4x&listid=6021800044038190429000103462&bank_channel=UNIONQRQPAY&spid=1800044038&request_source=\"/>\n" +
//                "<input type=\"hidden\" name=\"channelType\" id=\"channelType\" value=\"07\"/>\n" +
//                "<input type=\"hidden\" name=\"merId\" id=\"merId\" value=\"940332048168888\"/>\n" +
//                "<input type=\"hidden\" name=\"tokenPayData\" id=\"tokenPayData\" value=\"{trId=62000001274&tokenType=01}\"/>\n" +
//                "<input type=\"hidden\" name=\"txnSubType\" id=\"txnSubType\" value=\"00\"/>\n" +
//                "<input type=\"hidden\" name=\"customerInfo\" id=\"customerInfo\" value=\"e2NlcnRpZklkPTIzMTAwNTE5OTYwNDI4MDUxNCZjZXJ0aWZUcD0wMSZjdXN0b21lck5tPeeOi+S/iui+viZlbmNyeXB0ZWRJbmZvPUs1VVBqcHRHUE5qMVd2d0JzekRPQzkySEh3NEhqV1Mwank4SFR2MTdjWnMwQlNZcW4yb0NRU054YXZXSmYrS0lzYzE5UGV1T3FkcW01ZFRSai84V3BSQ2V0M0JJOUxxeVcrUWFNYWNRWXExbU1qKzVHMmZWOHdwampVMHI1azVMVkM4T1ZyaVR4U1RQM0tGclZ6eUNoWDhwYk1tbFRwNC9vcThESkVJU2Y0TnAyeGhTeHdYbnUyMlQvcDl2V1BLR1M1TWVGd1lPbEgvZWE4NGt3OURBNEhOR0dpcUlZcUFzajg1UjQrZkduS3hZN1g1ZmtzVEJQMm9CcGFzS2MyT3Jic05YQzBPamZlMy9NSVkzMjk2U2ZtTDM4RGRhRXdEY1UvS2E4bzZrU1ZkR0pTVjc1K1JtSEtYMVpvY29seXExK1FaK0thWGNvenMxYWUyU3pSNFJ2QT09fQ==\"/>\n" +
//                "<input type=\"hidden\" name=\"version\" id=\"version\" value=\"5.1.0\"/>\n" +
//                "<input type=\"hidden\" name=\"accType\" id=\"accType\" value=\"01\"/>\n" +
//                "<input type=\"hidden\" name=\"signMethod\" id=\"signMethod\" value=\"01\"/>\n" +
//                "<input type=\"hidden\" name=\"accNo\" id=\"accNo\" value=\"erITDFO81fATMWkQhWA77t/u4HYCpvikcO81OpBSsS/kqruN6c+hIrqxO8RFlVnSrR5IAIFqLTewYEJFTkz8pn+RVnw15VtfjPI8F3KdBRXowt3/l3t7t3VWNf5LaSOtnvuWZRP2dC6JPA8cHKRXzfrE2agUXFnsEp040ZU1+Q6KK5tPY3tnS4DwT8bsDs83HuzYgC26BALTmnuiQ8ftNSQgI/iYrJYL0m9A6rrgZTKaxJkPdOLG2ZQML+qmewxp6za6UviOinzjGrCz/F9MtyosrOQvtYyili8vyItQhYTdFiQof38LRGoMTTQ6vLPu93N4OKqil0ipI8j0AFwCWQ==\"/>\n" +
//                "<input type=\"hidden\" name=\"backUrl\" id=\"backUrl\" value=\"http://apitest2.tfb8.com/cgi-bin/v2.0/api_unionqrqsign_callback.cgi?listid=6021800044038190429000103462&bind_serialno=c0880964bb82a0af9f50d55eadf7c404&orderId=20190429150637949\"/>\n" +
//                "<input type=\"hidden\" name=\"certId\" id=\"certId\" value=\"232361\"/>\n" +
//                "<input type=\"hidden\" name=\"internal.merReferer\" id=\"internal.merReferer\" value=\"null\"/>\n" +
//                "<input type=\"hidden\" name=\"internal.origReqInfo\" id=\"internal.origReqInfo\" value=\"|07|7900\"/>\n" +
//                "<input type=\"hidden\" name=\"encoding\" id=\"encoding\" value=\"UTF-8\"/>\n" +
//                "<input type=\"hidden\" name=\"bizType\" id=\"bizType\" value=\"000902\"/>\n" +
//                "<input type=\"hidden\" name=\"encryptCertId\" id=\"encryptCertId\" value=\"69042905377\"/>\n" +
//                "<input type=\"hidden\" name=\"internal.logId\" id=\"internal.logId\" value=\"AC10190429150637cd2e0115485020\"/>\n" +
//                "<input type=\"hidden\" name=\"signature\" id=\"signature\" value=\"EOGDvmK6523lDolouOpFa79FBnLFn4OQhRm6jzJC8H6qKX09tHVZt7ZtIjUBluot1pskfsqcIkW54P1kZvs1Q1PxAJni+Fh465pdFj4p9l1jZX946SRtCfab697cb1CnZBHGaXrzqQVyi2YIrSwOJhpxmZRYJSymH/Kvg9UGwZDTL5lfM0ohXpGqWFut1e9j554115AUC+M1UZ4R39U5BbsyHVh1CyCg6P+FGT2Je0CyHq6yIjmBM1kI5hH1SkX7RFqydk2cbcQDJRwpJJIpjON6FkjUnhdBe7gVX4RqPymZ3nWxjb83xwibC94deaS3LbKOfW9Y0CRonscm+PPH+A==\"/>\n" +
//                "<input type=\"hidden\" name=\"orderId\" id=\"orderId\" value=\"20190429150637949\"/>\n" +
//                "<input type=\"hidden\" name=\"txnTime\" id=\"txnTime\" value=\"20190429150637\"/>\n" +
//                "<input type=\"hidden\" name=\"accessType\" id=\"accessType\" value=\"0\"/>\n" +
//                "</form>\n" +
//                "<script type=\"text/javascript\">\n" +
//                "<!--\n" +
//                "function OnLoadSubmit()\n" +
//                "{\n" +
//                "document.getElementById(\"gatewayform\").submit();\n" +
//                "}\n" +
//                "//-->\n" +
//                "</script>\n" +
//                "</body>\n" +
//                "</html>";
//        PrintWriter out = response.getWriter();
//        out.print(s);
//    }
//}
