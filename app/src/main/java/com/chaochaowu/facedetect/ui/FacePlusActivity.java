package com.chaochaowu.facedetect.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.chaochaowu.facedetect.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class FacePlusActivity extends AppCompatActivity implements Runnable{
	private final String TAG = "FacePlus";
	Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_face_plus);
		Thread t = new Thread(this);
		t.start();

		handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				if(msg.what==5){
					Bundle bdl = (Bundle)msg.obj;
					dollarRate = bdl.getFloat("dollar_rate");
					euroRate = bdl.getFloat("euro_rate");
					wonRate = bdl.getFloat("won_rate");

					//保存更新的日期
					SharedPreferences sharedPreferences = getSharedPreferences("my_rate", Activity.MODE_PRIVATE);
					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putString("updata_date",todayStr);
					editor.apply();

					Log.i(TAG, "handleMessage: dollarRate:"+dollarRate);
					Log.i(TAG, "handleMessage: euroRate:"+euroRate);
					Log.i(TAG, "handleMessage: wonRate:"+wonRate);
					Toast.makeText(RateActivity.this,"汇率已更新",Toast.LENGTH_SHORT).show();
				}
				super.handleMessage(msg);
			}
		};
	}

	public void run(){
		Log.i(TAG, "run: and run ");
		for(int i=1;i<6;i++){
			Log.i(TAG, "run:"+i);
			try{
				Thread.sleep(200);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		//用于保存获取的汇率
		Bundle bundle;
		bundle = getFromUsdCny();

		//获取网络数据
//        URL url = null;
//        try {
//            url = new URL("http://www.usd-cny.com/icbc.htm");
//            HttpURLConnection http = (HttpURLConnection) url.openConnection();
//            InputStream in = http.getInputStream();
//            String html = inputStream2String(in);
//            Log.i(TAG, "run: =" + html);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        bundle = getFromBOC();



		//获取Msg对象 用于返回主线程
		Message msg = handler.obtainMessage(5);
//        //msg.what = 5;
//        msg.obj = "Hello from run()";
		msg.obj =bundle;
		handler.sendMessage(msg);

	}
	//    获取工商银行数据
	private Bundle getFromBOC() {
		Document doc = null;
		Bundle bundle = new Bundle();
		try{
			doc = Jsoup.connect("https://beauty.pclady.com.cn/qudou/").get();
			Log.i(TAG, "run: "+doc.title());
			Elements tables = doc.getElementsByTag("table");
//            int i = 1;

//            for(Element table : tables){
//                Log.i(TAG, "run: table["+i+"] = "+table);
//                i++;
//            }
			Element table = tables.get(0);
			Log.i(TAG, "run: table="+table);
			Elements tds = table.getElementsByTag("td");
			for(int i= 0; i<tds.size();i+=5){
				Element td1 = tds.get(i);
				Element td2 = tds.get(i+3);
				Log.i(TAG, "run: "+td1.text()+"==>"+td2.text());
				if("美元".equals(td1.text())){
					bundle.putFloat("dollar_rate",100f/Float.parseFloat(td2.text()));
				}else if("欧元".equals(td1.text())){
					bundle.putFloat("euro_rate",100f/Float.parseFloat(td2.text()));
				}else if("韩国元".equals(td1.text())){
					bundle.putFloat("won_rate",100f/Float.parseFloat(td2.text()));
				}

			}
//            for(Element td:tds){
//                Log.i(TAG, "run: td="+td);
//                Log.i(TAG, "run: text= "+td.text());
//                Log.i(TAG, "run: html="+td.html());
//            }
		}catch (IOException e){
			e.printStackTrace();
		}
		return bundle;
	}
}
