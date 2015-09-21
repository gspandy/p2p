package com.platform.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class PlaceUtil {
	public static final String[] prov = {"北京市","天津市","安徽省","澳门","香港","福建省","甘肃省","广东省","广西","贵州省","海南省","河北省","河南省","黑龙江省","湖北省","湖南省","吉林省","江苏省","江西省","辽宁省","内蒙古","宁夏","青海省","山东省","山西省","陕西省","四川省","西藏","新疆","云南省","浙江省","重庆市","上海市","台湾省"};
	public static final String[] bj = {"北京市"};
	public static final String[] tj = {"天津市"};
	public static final String[] ah = {"安庆市","蚌埠市","亳州市","巢湖市","池州市","滁州市","阜阳市","合肥市","淮北市","淮南市","黄山市","六安市","马鞍山市","宿州市","铜陵市","芜湖市","宣城市"};
	public static final String[] am = {"澳门"};
	public static final String[] xg = {"香港"};
	public static final String[] fj = {"福州市","龙岩市","南平市","宁德市","莆田市","泉州市","厦门市","漳州市"};
	public static final String[] gs = {"白银市","定西市","甘南藏族自治州","嘉峪关市","金昌市","酒泉市","兰州市","临夏回族自治州","陇南市","平凉市","庆阳市","天水市","武威市","张掖市"};
	public static final String[] gd = {"潮州市","东莞市","佛山市","广州市","河源市","惠州市","江门市","揭阳市","茂名市","梅州市","清远市","汕头市","汕尾市","韶关市","深圳市","阳江市","云浮市","湛江市","肇庆市","中山市","珠海市"};
	public static final String[] gx = {"百色市","北海市","崇左市","防城港市","贵港市","桂林市","河池市","贺州市","来宾市","柳州市","南宁市","钦州市","梧州市","玉林市"};
	public static final String[] gz = {"安顺市","毕节地区","贵阳市","六盘水市","黔东南苗族侗族自治州","黔南布依族苗族自治州","黔西南布依族苗族自治州","铜仁地区","遵义市"};
	public static final String[] han = {"海口市","三亚市","省直辖县级行政区划"};
	public static final String[] heb = {"保定市","沧州市","承德市","邯郸市","衡水市","廊坊市","秦皇岛市","石家庄市","唐山市","邢台市","张家口市"};
	public static final String[] hen = {"安阳市","鹤壁市","焦作市","开封市","洛阳市","漯河市","南阳市","平顶山市","濮阳市","三门峡市","商丘市","新乡市","信阳市","许昌市","郑州市","周口市","驻马店市"};
	public static final String[] hlj = {"大庆市","大兴安岭地区","哈尔滨市","鹤岗市","黑河市","鸡西市","佳木斯市","牡丹江市","七台河市","齐齐哈尔市","双鸭山市","绥化市","伊春市"};
	public static final String[] hub = {"鄂州市","恩施土家族苗族自治州","黄冈市","黄石市","荆门市","荆州市","十堰市","随州市","武汉市","咸宁市","襄樊市","孝感市","宜昌市"};
	public static final String[] hun = {"长沙市","常德市","郴州市","衡阳市","怀化市","娄底市","邵阳市","湘潭市","湘西土家族苗族自治州","益阳市","永州市","岳阳市","张家界市","株洲市"};
	public static final String[] jl = {"白城市","白山市","长春市","吉林市","辽源市","四平市","松原市","通化市","延边朝鲜族自治州"};
	public static final String[] js = {"常州市","淮安市","连云港市","南京市","南通市","苏州市","宿迁市","泰州市","无锡市","徐州市","盐城市","扬州市","镇江市"};
	public static final String[] jx = {"抚州市","赣州市","吉安市","景德镇市","九江市","南昌市","萍乡市","上饶市","新余市","宜春市","鹰潭市"};
	public static final String[] ln = {"鞍山市","本溪市","朝阳市","大连市","丹东市","抚顺市","阜新市","葫芦岛市","锦州市","辽阳市","盘锦市","沈阳市","铁岭市","营口市"};
	public static final String[] nmg = {"阿拉善盟","巴彦淖尔市","包头市","赤峰市","鄂尔多斯市","呼和浩特市","呼伦贝尔市","通辽市","乌海市","乌兰察布市","锡林郭勒盟","兴安盟"};
	public static final String[] nx = {"固原市","石嘴山市","吴忠市","银川市","中卫市"};
	public static final String[] qh = {"果洛藏族自治州","海北藏族自治州","海东地区","海南藏族自治州","海西蒙古族藏族自治州","黄南藏族自治州","西宁市","玉树藏族自治州"};
	public static final String[] sd = {"滨州市","德州市","东营市","菏泽市","济南市","济宁市","莱芜市","聊城市","临沂市","青岛市","日照市","泰安市","威海市","潍坊市","烟台市","枣庄市","淄博市"};
	public static final String[] sx = {"长治市","大同市","晋城市","晋中市","临汾市","吕梁市","朔州市","太原市","忻州市","阳泉市","运城市"};
	public static final String[] shx = {"安康市","宝鸡市","汉中市","商洛市","铜川市","渭南市","西安市","咸阳市","延安市","榆林市"};
	public static final String[] sc = {"阿坝藏族羌族自治州","巴中市","成都市","达州市","德阳市","甘孜藏族自治州","广安市","广元市","乐山市","凉山彝族自治州","泸州市","眉山市","绵阳市","内江市","南充市","攀枝花市","遂宁市","雅安市","宜宾市","资阳市","自贡市"};
	public static final String[] xz = {"阿里地区","昌都地区","拉萨市","林芝地区","那曲地区","日喀则地区","山南地区"};
	public static final String[] xj = {"阿克苏地区","阿勒泰地区","巴音郭楞蒙古自治州","博尔塔拉蒙古自治州","昌吉回族自治州","哈密地区","和田地区","喀什地区","克拉玛依市","克孜勒苏柯尔克孜自治州","塔城地区","吐鲁番地区","乌鲁木齐市","伊犁哈萨克自治州","自治区直辖县级行政区划"};
	public static final String[] yn = {"保山市","楚雄彝族自治州","大理白族自治州","德宏傣族景颇族自治州","迪庆藏族自治州","红河哈尼族彝族自治州","昆明市","丽江市","临沧市","怒江僳僳族自治州","普洱市","曲靖市","文山壮族苗族自治州","西双版纳傣族自治州","玉溪市","昭通市"};
	public static final String[] zj = {"杭州市","湖州市","嘉兴市","金华市","丽水市","宁波市","衢州市","绍兴市","台州市","温州市","舟山市"};
	public static final String[] cq = {"重庆市"};
	public static final String[] sh = {"上海市"};
	public static final String[] tw = {"台北市","高雄市","基隆市","台中市","台南市","新竹市","嘉义市"};
	
	public final static Map<String,Object> citymap = new HashMap<String,Object>();  
	static {  
		citymap.put("北京市", bj);  
		citymap.put("天津市", tj); 
		citymap.put("安徽省", ah);  
		citymap.put("澳门", am); 
		citymap.put("香港", xg); 
		citymap.put("福建省", fj); 
		citymap.put("甘肃省", gs); 
		citymap.put("广东省", gd); 
		citymap.put("广西", gx); 
		citymap.put("贵州省", gz); 
		citymap.put("海南省", han); 
		citymap.put("河北省", heb); 
		citymap.put("河南省", hen); 
		citymap.put("黑龙江省",hlj); 
		citymap.put("湖北省", hub); 
		citymap.put("湖南省", hun); 
		citymap.put("吉林省", jl); 
		citymap.put("江苏省", js); 
		citymap.put("江西省", jx); 
		citymap.put("辽宁省", ln);
		citymap.put("内蒙古", nmg);
		citymap.put("宁夏", nx);
		citymap.put("青海省", qh);
		citymap.put("山东省", sd);
		citymap.put("山西省", sx);
		citymap.put("陕西省", shx);
		citymap.put("四川省", sc);
		citymap.put("西藏", xz);
		citymap.put("新疆", xj);
		citymap.put("云南省", yn);
		citymap.put("浙江省", zj);
		citymap.put("重庆市", cq);
		citymap.put("上海市", sh);
		citymap.put("台湾省", tw);
		
	}  

              public static void main(String[] args) {
                  System.out.println(PlaceUtil.prov.length);
            	  System.out.println(JSONArray.toJSON(PlaceUtil.prov));// 
                  System.out.println(JSONArray.toJSON(PlaceUtil.citymap.get("安徽省")));//  这里显示json串
                
                  
//                  System.out.println(JSONArray.parse(JSONArray.toJSON(list).toString()));//把json串转了一下

			} 
              
              public List<Map<String, Object>> getProvince(){
            	  
            	  List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
                  List<String> list = (List) JSONArray.toJSON(PlaceUtil.prov);
                  for(String s : list){
                	  Map<String, Object> map = new HashMap<String, Object>();
                	  map.put("name", s);
                	  maplist.add(map);
                  }
				return maplist;
              }
              
              
              public List<Map<String, Object>> getcity(String Province){
            	  
            	  List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
                  List<String> list = (List) JSONArray.toJSON(PlaceUtil.citymap.get(Province));
                  for(String s : list){
                	  Map<String, Object> map = new HashMap<String, Object>();
                	  map.put("name", s);
                	  maplist.add(map);
                  }
				return maplist;
              }
  
}  



