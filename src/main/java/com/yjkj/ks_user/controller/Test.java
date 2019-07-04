package com.yjkj.ks_user.controller;

import com.yjkj.ks_user.entity.Area;
import com.yjkj.ks_user.entity.MerChants;
import com.yjkj.ks_user.service.AreaService;
import com.yjkj.ks_user.service.MerChantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("Test")
public class Test extends BaseController {
	
//	String APP_ID = "2018011501888722";
//	String APP_PRIVATE_KEY = "MIIEugIBADANBgkqhkiG9w0BAQEFAASCBKQwggSgAgEAAoIBAQDJcF/2Jh71NQdUcoXEOIYFY2xL/DQmJJ5fmZU8Fta8O43Z9MBfXnWPoPCHs+v/BWGrme8qZmNRRrZWY6ZTzXAGQySRzzsDUKokMD5bkofRpI3arPDbfjJQQDnRjIx05uzjYdpUZ/PPuW1P/rrbx1k4oQOcjvZg2RcrECYDGZZRXv060Qaujh9htRxTqbgk8baK4JpRQTbrHx/qQrSmlVX/sBIkki2Zi1QvyrH6SSYsIQXiV9s3wkXafDfQ0kAHlcyjh/QfQVEc0nYFHgBpGUhRkJs1Tl/QDm3tFkAAJiXnJqCtMqci94XBGRB5WbuGL93ZDwZwIFdg6QAHUG3PjzaNAgMBAAECggEAF9Fet1cYj/u+KyEDagkY56gy1OU0MVJhG4KoX5t/tnAAPMYwvNYJZnssFY+TlurG+qhP3B6RHboTszFDKGOBHGxx9X+gRasTAwZlSSSRRJuFXlbWf81zG4QMkhdhQC1R0yztyM8lx+jv4OaIp5Xxmb2IFMneneLtV10ocXNkD6jK6iQGjJbc58GuAHMkBFpbF7biU1UFGSaQ1HXdVBNr4RaEyPOTlfOraL10jx5VnNr0es7PpMK+YLkHc53vC2s1cPk6srkGWL7VTmjqcqK1BE7SwLWclN0qxkqYHGalXocC3FQbyXdU30zJGe7xX5rvwfLVwiDBuFmQKEBr+n+tzQKBgQDlNfyyetouRx9ciCibT3cI+dWG+BlbF9dE/I5eulTUfIsPDQNfDPxU1BxBz6CJtBEZVhTha4WO17flh7uHHYr+LNCYHMqpcMwYT3Y/waT1eK6eQ4l1f20Nx7XFC46pIxlLe+rUGdB90o3veyBXxY51r8g+WybkCuPaa06XpsVPQwKBgQDg+3LyjwkHDXuw/YRVtNwe+oeWwqm2BqFq94bAkRRWanQBsF9yn76O42ZgKHLeoodGHiQruunJ5G0Ifl0q2HVamUATTD5CY7wqEMM+X3ZzDqvnwBpv7pYWyi8T0MnGy4F582/tR+KI/BEUJrFE4NSzWyzTFZXIzEgP6RhklRf97wKBgDYvhhaXbl7EV4ijc54LsiNAFJd8rVnYj/5yCiIgwI8fBiy/sc1Vn41aO3ffYp9LH0OzEuqbZY54NrQfW1i8xjACXimnnM1jqgwRNO4rlBint/gYFTXesuQwEyuK6OXZM1Ch8cjEdYJZNU6o9+NoA56UuKztFYuRe34WIhU9yqVLAoGAFPteXQC59s5rGraKAPQ9EyaKPqUo7rDvnOo4xvzAyM5kw0OPo0ECpJDQm2cqT1kZBJELDokABLQo9wHR7o0BZNGmCCa0J7zlb4bgrpB4m4JixdpEnOvDU5zHhzGRn9ia2qS5a4eouN3JOffh0Gqna5K1O3pn3bIvhkxW2gqNkkECfz3Evb0Rn1Wj3UOfYL0V3f2fBOosFG1OsWlG4B9NiC2NT08dpgDv522AolNYs/V8ouGcOlO9NreubreU/W04fMV93AP3XfTM53upVqx2z3trBQxcPv98GX6afZVG35bxMRfVZ1JpDSD0zQ5P6nAIMI9ClVscmbT5sEkmqyuuvCw=";
//	String CHARSET = "UTF-8";
//	String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjmqATp1pP89q6pRwNbRQ4D1eXdwBYSfgLb1K0Ydas12XCiFfqFeBOBQQ78lTz6wyYWA+liLo4Z9V0Mt4MGNYg/xLkX+orTFabSSonu7Fqsz3hXp2tH8GHkGAgDZtiRNPqS2qv80bgcy1gF677G8TSIHmZb+xillfPrMM1oYf8xa3BvG9Yo2yKnTGRb8iVp2vWFgjwAM9PiLbtndWYR+C1ibHsYcydI2GZHKnymKfHM9kJIyYrtzinu+xNvx7S3JHhhH1XNS6MIrMM8whEBegxFTWlwfQLR4NfBJz4i3NzMpoXPSkaEwhm7rVN7n4z4xQyASJIyay4PMbL/JrjtLULQIDAQAB";
//	
//	@RequestMapping(value = "test")
//	public @ResponseBody
//	YJResult getCode(String token, String amount) {
//		// Jedis jedis=RedisUtils.getJedis();
//		if(redisUtils.exists(token)){
//			//实例化客户端
//			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
//			//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
//			AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//			//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
//			AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
//			model.setBody("会员费");
//			model.setSubject("会员费");
//			model.setOutTradeNo(System.currentTimeMillis()+"");
//			model.setTimeoutExpress("30m");
//			model.setTotalAmount(""+amount+"");
//			model.setProductCode("QUICK_MSECURITY_PAY");
//			request.setBizModel(model);
//			request.setNotifyUrl("http://www.baidu.com");
//			try {
//			        //这里和普通的接口调用不同，使用的是sdkExecute
//			        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
//			        // RedisUtils.returnResource(jedis);
//					return YJResult.ok(response.getBody());
//			        //System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
//			    } catch (AlipayApiException e) {
//			        e.printStackTrace();
//			}		
//		}
//		// RedisUtils.returnResource(jedis);
//		return YJResult.build(Constaint.INVALID, "登录失效，请重新登录");
//	}
	@Autowired
	  private AreaService areaService;
	  @Autowired
	  private MerChantsService merChantsService;
	  
	   //获取省市县
	  List<Area> getArea(MerChants h) {
	    String s = h.getCertNo().substring(0, 6);
	    Area area = new Area();
	    area.setCode(s);
	    Area t = areaService.findByObject(area);
	    List<Area> list = new ArrayList<Area>();
	    if(t != null){
	      list.add(t);
	      Area k = new Area();
	      k.setId(Long.parseLong(t.getParent_id()));
	      Area n = areaService.findByObject(k);
	      list.add(n);
	      if(n != null){
	        Area b = new Area(); 
	        b.setId(Long.parseLong(n.getParent_id()));
	        Area tt = areaService.findByObject(b);
	        list.add(tt);
	      }
	    }
	    return list;
	    }
	  /*String APP_ID = "2018011501888722";
	  String APP_PRIVATE_KEY = "MIIEugIBADANBgkqhkiG9w0BAQEFAASCBKQwggSgAgEAAoIBAQDJcF/2Jh71NQdUcoXEOIYFY2xL/DQmJJ5fmZU8Fta8O43Z9MBfXnWPoPCHs+v/BWGrme8qZmNRRrZWY6ZTzXAGQySRzzsDUKokMD5bkofRpI3arPDbfjJQQDnRjIx05uzjYdpUZ/PPuW1P/rrbx1k4oQOcjvZg2RcrECYDGZZRXv060Qaujh9htRxTqbgk8baK4JpRQTbrHx/qQrSmlVX/sBIkki2Zi1QvyrH6SSYsIQXiV9s3wkXafDfQ0kAHlcyjh/QfQVEc0nYFHgBpGUhRkJs1Tl/QDm3tFkAAJiXnJqCtMqci94XBGRB5WbuGL93ZDwZwIFdg6QAHUG3PjzaNAgMBAAECggEAF9Fet1cYj/u+KyEDagkY56gy1OU0MVJhG4KoX5t/tnAAPMYwvNYJZnssFY+TlurG+qhP3B6RHboTszFDKGOBHGxx9X+gRasTAwZlSSSRRJuFXlbWf81zG4QMkhdhQC1R0yztyM8lx+jv4OaIp5Xxmb2IFMneneLtV10ocXNkD6jK6iQGjJbc58GuAHMkBFpbF7biU1UFGSaQ1HXdVBNr4RaEyPOTlfOraL10jx5VnNr0es7PpMK+YLkHc53vC2s1cPk6srkGWL7VTmjqcqK1BE7SwLWclN0qxkqYHGalXocC3FQbyXdU30zJGe7xX5rvwfLVwiDBuFmQKEBr+n+tzQKBgQDlNfyyetouRx9ciCibT3cI+dWG+BlbF9dE/I5eulTUfIsPDQNfDPxU1BxBz6CJtBEZVhTha4WO17flh7uHHYr+LNCYHMqpcMwYT3Y/waT1eK6eQ4l1f20Nx7XFC46pIxlLe+rUGdB90o3veyBXxY51r8g+WybkCuPaa06XpsVPQwKBgQDg+3LyjwkHDXuw/YRVtNwe+oeWwqm2BqFq94bAkRRWanQBsF9yn76O42ZgKHLeoodGHiQruunJ5G0Ifl0q2HVamUATTD5CY7wqEMM+X3ZzDqvnwBpv7pYWyi8T0MnGy4F582/tR+KI/BEUJrFE4NSzWyzTFZXIzEgP6RhklRf97wKBgDYvhhaXbl7EV4ijc54LsiNAFJd8rVnYj/5yCiIgwI8fBiy/sc1Vn41aO3ffYp9LH0OzEuqbZY54NrQfW1i8xjACXimnnM1jqgwRNO4rlBint/gYFTXesuQwEyuK6OXZM1Ch8cjEdYJZNU6o9+NoA56UuKztFYuRe34WIhU9yqVLAoGAFPteXQC59s5rGraKAPQ9EyaKPqUo7rDvnOo4xvzAyM5kw0OPo0ECpJDQm2cqT1kZBJELDokABLQo9wHR7o0BZNGmCCa0J7zlb4bgrpB4m4JixdpEnOvDU5zHhzGRn9ia2qS5a4eouN3JOffh0Gqna5K1O3pn3bIvhkxW2gqNkkECfz3Evb0Rn1Wj3UOfYL0V3f2fBOosFG1OsWlG4B9NiC2NT08dpgDv522AolNYs/V8ouGcOlO9NreubreU/W04fMV93AP3XfTM53upVqx2z3trBQxcPv98GX6afZVG35bxMRfVZ1JpDSD0zQ5P6nAIMI9ClVscmbT5sEkmqyuuvCw=";
	  String CHARSET = "UTF-8";
	  String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjmqATp1pP89q6pRwNbRQ4D1eXdwBYSfgLb1K0Ydas12XCiFfqFeBOBQQ78lTz6wyYWA+liLo4Z9V0Mt4MGNYg/xLkX+orTFabSSonu7Fqsz3hXp2tH8GHkGAgDZtiRNPqS2qv80bgcy1gF677G8TSIHmZb+xillfPrMM1oYf8xa3BvG9Yo2yKnTGRb8iVp2vWFgjwAM9PiLbtndWYR+C1ibHsYcydI2GZHKnymKfHM9kJIyYrtzinu+xNvx7S3JHhhH1XNS6MIrMM8whEBegxFTWlwfQLR4NfBJz4i3NzMpoXPSkaEwhm7rVN7n4z4xQyASJIyay4PMbL/JrjtLULQIDAQAB";
	  */
	  @RequestMapping(value = "test")
	  public @ResponseBody
      String getCode() {
	    MerChants merChant = new MerChants();
	    merChant.setMerStat("Y");
	    List<MerChants> h = merChantsService.queryObjectForList(merChant);
	    for(MerChants v : h){
	      List<Area> tList = getArea(v);
	      if(!tList.isEmpty()){
	        for(Area area : tList){
	          Area areas = (Area)area;
	          if(null != areas){
	            if("3".equals(areas.getLevel())){
	              v.setCounty(areas.getCode());
	            }else if("2".equals(areas.getLevel())){
	              v.setCity(areas.getCode());
	            }else if("1".equals(areas.getLevel())){
	              v.setProvince(areas.getCode());
	            }
	          }
	        }
	      }  
	      merChantsService.update(v);
	    }
	  
	  return "s";
	  }
}
