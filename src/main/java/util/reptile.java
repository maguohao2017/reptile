package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dto.Movie;

import java.util.List;

/**
 * @ClassName getJson
 * @Description 分析豆瓣电影网站
 * @Author maguohao
 * @Date 2020/2/23
 * @Version 1.0
 **/
public class reptile {

    public static  void  main(String [] args) {

        int start;//每页多少条
        int total = 0;//记录数
        int end = 9979;//总共9979条数据
        for (start  = 0; start <= end; start += 20)  {
            try {

                String address = "https://Movie.douban.com/j/new_search_subjects?sort=U&range=0,10&tags=&start=" + start;

                JSONObject dayLine = new GetJson().getHttpJson(address, 1);

                System.out.println("start:" + start);
                JSONArray json = dayLine.getJSONArray("data");
                if(json != null){
                    List<Movie> list = JSON.parseArray(json.toString(), Movie.class);
                    if (start <= end){
                        System.out.println("已经爬取到底了");
                    }
                    total += list.size();
                    System.out.println("正在爬取中---共抓取:" + total + "条数据");
                    System.out.println("正在抓取json:" + json.toJSONString());
                }else{
                    continue;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
