package com.xiaoyuan.jsoupdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        //子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //获取Document对象
                    Document doc = Jsoup.connect("http://neihanshequ.com/").get();
                    //获取class为content-wrapper的内容，因为页面不仅一个div叫这个名字，所以解析出来是一个数组
                    Elements select = doc.select("div.content-wrapper");
                    for (int i = 0; i < select.size(); i++) {
                        //这句话的意思说获取每个div下面的每个p里面的内容，取一个元素里面的内容可以用.text
                        //如果想取像a标签里面的href内容就得用.attr
//                        Log.d("MainActivity", select.get(i).select("p").text()+"\n");
//                        Log.d("MainActivity", select.get(i).select("a").attr("href")+"\n");
                        Log.d("MainActivity", select.get(i).select("a").attr("href")+"\n");
                    }

//                    // 取点赞、踩数、收藏数
                    Elements select1 = doc.select("div.options");
                    for (int i = 0; i < select1.size(); i++) {
                        //取ul里面的内容名为digg-wrapper的li标签下面的span的文本
                        Log.d("MainActivity", "点赞数"+select1.get(i).select("ul").select("li.digg-wrapper").select("span").text());
                        Log.d("MainActivity", "踩数"+select1.get(i).select("ul").select("li.bury-wrapper").select("span").text());
                        Log.d("MainActivity", "收藏数"+select1.get(i).select("ul").select("li.repin-wrapper").select("span").text());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
