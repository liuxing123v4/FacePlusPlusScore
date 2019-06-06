package com.chaochaowu.facedetect.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaochaowu.facedetect.R;
import com.chaochaowu.facedetect.bean.FaceppBean;
import com.chaochaowu.facedetect.eventbus.FaceEvent;
import com.chaochaowu.facedetect.retrofit.MyDatabaseHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 用于展示面部识别的详情信息
 * @author chaochaowu
 */
public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.sex)
    TextView tvSex;
    @BindView(R.id.age)
    TextView tvAge;
    @BindView(R.id.tv_beauty)
    TextView tvBeautyTip;
    final String TAG = "DetailActivity";
    @BindView(R.id.beauty)
    TextView tvBeauty;
    @BindView(R.id.emotion)
    TextView tvEmotion;
	@BindView(R.id.button_up)
    Button button_up;
    private  String username;
    private float face_score;
    private MyDatabaseHelper dbhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        Intent intent1 = getIntent();
        face_score = intent1.getFloatExtra("face_score", Float.parseFloat("0.0f"));
        dbhelper = new MyDatabaseHelper(this,"Person.db",null,1);
    }

    public void setDbhelper(MyDatabaseHelper dbhelper) {
        this.dbhelper = dbhelper;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(FaceEvent event) {
        FaceppBean.FacesBean face = event.getFace();
        Bitmap photo = event.getBitmap();
        Bitmap faceMarkedPhoto = markFaceInThePhoto(photo, face);
        imageView.setImageBitmap(faceMarkedPhoto);
        displayFaceInfo(face);
    }

    private Bitmap markFaceInThePhoto(Bitmap bitmap, FaceppBean.FacesBean face) {
        Bitmap tempBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(tempBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        FaceppBean.FacesBean.FaceRectangleBean faceRectangle = face.getFace_rectangle();
        int top = faceRectangle.getTop();
        int left = faceRectangle.getLeft();
        int height = faceRectangle.getHeight();
        int width = faceRectangle.getWidth();
        canvas.drawRect(left, top, left + width, top + height, paint);
        return tempBitmap;
    }

    public void displayFaceInfo(FaceppBean.FacesBean face) {

        String s = "";
        FaceppBean.FacesBean.AttributesBean attributes = face.getAttributes();
        FaceppBean.FacesBean.AttributesBean.AgeBean age = attributes.getAge();
        FaceppBean.FacesBean.AttributesBean.GenderBean gender = attributes.getGender();
        FaceppBean.FacesBean.AttributesBean.BeautyBean beauty = attributes.getBeauty();
        tvAge.setText(String.format("年龄：%s", age.getValue()));
        tvSex.setText(String.format("性别：%s", "Male".equals(gender.getValue()) ? "男" : "女"));
        double maleScore = beauty.getMale_score();
	    Log.i(TAG, "displayFaceInfo: "+maleScore+"......................................");
        double femaleScore = beauty.getFemale_score();
        tvBeauty.setText(String.format("%1.2f", "Male".equals(gender.getValue()) ? maleScore : femaleScore));
        FaceppBean.FacesBean.AttributesBean.EmotionBean emotion = attributes.getEmotion();
        s = new StringBuilder()
                .append("愤怒 ").append(emotion.getAnger()).append("%")
                .append("        厌恶 ").append(emotion.getDisgust()).append("%")
                .append("\n恐惧 ").append(emotion.getFear()).append("%")
                .append("        高兴 ").append(emotion.getHappiness()).append("%")
                .append("\n平静 ").append(emotion.getNeutral()).append("%")
                .append("        伤心 ").append(emotion.getSadness()).append("%")
                .append("\n惊讶 ").append(emotion.getSurprise()).append("%\n\n")
                .toString();
        tvEmotion.setText(s);
        final Float face_score = Float.parseFloat(String.format("%1.2f", "Male".equals(gender.getValue()) ? maleScore : femaleScore));
        Log.i(TAG, "displayFaceInfo: "+face_score);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        button_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db = dbhelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("score",face_score);
                //查看是否插入正确
                db.update("person", values,"username = ?",new String[]{username});
                Log.i(TAG, "displayFaceInfo: "+"更新成功。。。。。。。。");
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        onBackPressed();
        return true;
    }


}
