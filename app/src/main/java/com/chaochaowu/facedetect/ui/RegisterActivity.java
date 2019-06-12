package com.chaochaowu.facedetect.ui;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chaochaowu.facedetect.R;
import com.chaochaowu.facedetect.bean.PersonInfo;
import com.chaochaowu.facedetect.retrofit.MyDatabaseHelper;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
	final String TAG = "Register";
	private EditText usernameEditText;
	private EditText passwordEditText;
	private EditText configPasswordEditText;
	private String username,password,configPassword;
	private TextView link_login;
	private List<PersonInfo> list=new ArrayList<>();
	//注册按钮
	private Button registerBtn;
	//数据库使用
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		usernameEditText = (EditText)findViewById(R.id.userName);
		passwordEditText = (EditText)findViewById(R.id.password);
		configPasswordEditText = (EditText)findViewById(R.id.configPassword);
		registerBtn = (Button) findViewById(R.id.register);
		//回退事件
		Connector.getDatabase();
		link_login = (TextView)findViewById(R.id.link_login);
		link_login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});


		//点击事件
		registerBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				username = usernameEditText.getText().toString();
				password = passwordEditText.getText().toString();
				configPassword = configPasswordEditText.getText().toString();
				list= DataSupport.where("username = ?",username).find(PersonInfo.class);
				Log.i(TAG, "onClick: "+list.size());
				if(list.size()!=0){
					AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
					dialog.setTitle("提醒");
					dialog.setMessage("该用户已经存在");
					dialog.setCancelable(false);
					dialog.setPositiveButton("确定",null);
					dialog.show();
				}else{
					PersonInfo person = new PersonInfo();
					person.setUsername(username);
					person.setPassword(password);
					person.setScore(0.0);
					person.save();
					//查看是否插入正确
					if (person.isSaved()) {
						AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
						dialog.setTitle("恭喜您");
						dialog.setMessage("注册成功");
						dialog.setCancelable(false);
						dialog.setPositiveButton("确定", null);
						dialog.show();
						//到mainActivity中
						Intent intent = new Intent(RegisterActivity.this, FirstActivity.class);
						startActivity(intent);
					}
				}

//				for (PersonInfo p:list) {
//					if(p.getUsername().equals(username)){
//
//					}else if(!p.getUsername().equals(username)&&password.equals(configPassword)){

//					}else{
//					//提醒用户输入不相同
//					AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);
//					dialog.setTitle("提醒");
//					dialog.setMessage("两次密码输入不相同");
//					dialog.setCancelable(false);
//					dialog.setPositiveButton("确定",null);
//					dialog.show();
//				}


			}
		});

	}
}
