package com.example.kangjunhu.myapplication4;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

            private EditText mInputMessage; private Button mSendMessage; private LinearLayout mMessageLog;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_chat);
                mInputMessage = (EditText) findViewById(R.id. input_message );
                mSendMessage = (Button) findViewById(R.id. send_message );
                mMessageLog = (LinearLayout) findViewById(R.id. message_log );
                mSendMessage.setOnClickListener(this);
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }
    // dsfjksljdkf
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mSendMessage)) {
            String inputText = mInputMessage.getText().toString();
            String answer="";
            if(inputText.contains("안녕")) { answer = "안녕하세요."; }
            else if(inputText.contains("졸려")) { answer = "한숨 푹 주무세요..."; }
            else if(inputText.contains("어때") || inputText.contains("괜찮")) { answer = "그거 괜찮네요!"; }
            else if(inputText.contains("몇시") || inputText.contains("몇 시")) {
                Calendar cal = Calendar.getInstance();
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DATE);
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                answer = "지금은 " + month + "월 " + day + "일 " + hour + "시 " + minute + "분입니다.";
            }
            else if(inputText.contains("확률은?")) { double random = Math. random () * 100;
                answer = inputText.replace('?', ' ') + (int) random + "% 입니다.";
            }

            TextView userMessage = new TextView(this);
            userMessage.setBackgroundResource(R.drawable. user_message );

            userMessage.setText(inputText);
            final int marginSize = getResources().getDimensionPixelSize(R.dimen. message_margin );
            LinearLayout.LayoutParams userMessageLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams. WRAP_CONTENT , LinearLayout.LayoutParams. WRAP_CONTENT );
            userMessageLayout.gravity = Gravity. END ; userMessageLayout.setMargins(0, marginSize, 0, marginSize);

            mMessageLog.addView(userMessage, 0, userMessageLayout);
            final TextView aiMessage = new TextView(this);
            aiMessage.setBackgroundResource ( R.drawable.ai_message );
            int aiMessageColor = getResources().getColor(R.color. colorAiMessage );
            aiMessage.setTextColor(aiMessageColor);
            aiMessage.setText(answer);
            aiMessage.setGravity(Gravity. START );



            mInputMessage.setText("");
            TranslateAnimation userMessageAnimation = new TranslateAnimation(mMessageLog.getWidth(), 0, 0, 0);
            userMessageAnimation.setDuration(1000);
            userMessage.startAnimation(userMessageAnimation);

            userMessageAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override public void onAnimationStart(Animation animation) {
                }
                @Override public void onAnimationEnd(Animation animation) {
                    LinearLayout.LayoutParams aiMessageLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams. WRAP_CONTENT , LinearLayout.LayoutParams. WRAP_CONTENT );
                    aiMessageLayout.gravity = Gravity. START ;
                    aiMessageLayout.setMargins(0, marginSize, 0, marginSize);
                    mMessageLog.addView(aiMessage, 0, aiMessageLayout);
                    TranslateAnimation aiMessageAnimation = new TranslateAnimation(-mMessageLog.getWidth(), 0, 0, 0);
                    aiMessageAnimation.setDuration(1000); aiMessage.startAnimation(aiMessageAnimation);
                }

                @Override public void onAnimationRepeat(Animation animation) {
                }

            });



        }
    }
}
