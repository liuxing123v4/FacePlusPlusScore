package com.chaochaowu.facedetect.retrofit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	//创建数据库
//	private static final int VERSION = 1;
//	private static final String DATABASE_NAME = "Person.db";

	public static final String CREATE_CONTACT = "create table person ("
			+ "username varchar(80) primary key, " + "password varchar(80), "
			+ "score Text)";
	private Context mcontext;
	public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//		super(context, DATABASE_NAME, null, VERSION);
		super(context, name, factory, version);
		mcontext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		//创建数据库
//		sqLiteDatabase.execSQL("create table "+PersonTable.NAME + "("
//				+ "_id integer primary key autoincrement,"+PersonTable.Cols.UUID+","+PersonTable.Cols.USERNAME
//				+ ","+PersonTable.Cols.PASSWORD +","+ PersonTable.Cols.SCORE+")");
		sqLiteDatabase.execSQL(CREATE_CONTACT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

	}


}
