package com.chaochaowu.facedetect.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chaochaowu.facedetect.R;
import com.chaochaowu.facedetect.bean.PersonInfo;
import com.chaochaowu.facedetect.retrofit.MyDatabaseHelper;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MDLoginActivity extends AppCompatActivity {
	//登录
	final String TAG = "MDLogin";
	private List<PersonInfo> list=new ArrayList<>();
	private EditText usernameEditText;
	private  EditText passwordEditText;
	private  String username,password;
	private Button loginBtn;
	private TextView register;
	//数据库使用
	private MyDatabaseHelper dbhelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mdlogin);
		dbhelper = new MyDatabaseHelper(this,"Person.db",null,1);
		usernameEditText = (EditText)findViewById(R.id.username);
		passwordEditText = (EditText)findViewById(R.id.password);
		loginBtn = (Button)findViewById(R.id.login);
		register = (TextView)findViewById(R.id.register);

		register.setOnClickListener(new View.OnClickListener() {
			@Override
			//注册
			public void onClick(View view) {
				Intent intent = new Intent(MDLoginActivity.this,RegisterActivity.class);
				startActivity(intent);
			}
		});

		loginBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				//进行数据库的查询
				Log.i(TAG, "onClick: 点击成功");
//				PersonInfo person = new PersonInfo();
//				person.setUsername("Liuxing");
//				person.save();
				username = usernameEditText.getText().toString();
				password = passwordEditText.getText().toString();
				list= DataSupport.findAll(PersonInfo.class);
				for (PersonInfo p:list) {
					Log.d(TAG, "onClick: "+"name"+p.getUsername());
					if(p.getUsername().equals(username)&&p.getPassword().equals(password))
					{
						Log.i(TAG, "onClick: 登录一下");
						Intent intent = new Intent(MDLoginActivity.this, FirstActivity.class);
						Intent intent1 = new Intent(MDLoginActivity.this, DetailActivity.class);
						intent.putExtra("username",username);
						startActivity(intent);
						Toast.makeText(MDLoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
						break;
					} else {
						Log.i(TAG, "onClick: 报个错");
						Toast.makeText(MDLoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
//						AlertDialog.Builder dialog = new AlertDialog.Builder(MDLoginActivity.this);
//						dialog.setTitle("提醒");
//						dialog.setMessage("该用户不存在");
//						dialog.setCancelable(false);
//						dialog.setPositiveButton("确定", null);
//						dialog.show();
					}
				}
			}
		});
	}
}
