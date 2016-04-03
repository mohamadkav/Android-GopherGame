package io.sharif.prj1.st91110439.st91106019.project1;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private TextView animation;
    private ImageButton arrowUp;
    private ImageButton arrowDown;
    private ImageButton arrowRight;
    private ImageButton arrowLeft;
    private ImageButton infoDetails;
    private ImageView gopher;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        animation = (TextView)findViewById(R.id.gopher_anim);
        arrowUp = (ImageButton)findViewById(R.id.arrow_up);
        arrowDown = (ImageButton)findViewById(R.id.arrow_down);
        arrowRight = (ImageButton)findViewById(R.id.arrow_right);
        arrowLeft = (ImageButton)findViewById(R.id.arrow_left);
        gopher = (ImageView)findViewById(R.id.gopher);
        infoDetails=(ImageButton)findViewById(R.id.info_details);
        final RelativeLayout gopherParent=(RelativeLayout)findViewById(R.id.game_board);
        prefs=getSharedPreferences("com.gopher.app", Context.MODE_PRIVATE);
        arrowUp.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float y = gopher.getY();
                gopher.setY(y-15);
            }
        }));
        arrowDown.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float y = gopher.getY();
                gopher.setY(y + 15);
            }
        }));
        arrowRight.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float x = gopher.getX();
                gopher.setX(x + 15);
            }
        }));
        arrowLeft.setOnTouchListener(new RepeatListener(400, 100, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float x = gopher.getX();
                gopher.setX(x - 15);
            }
        }));
        infoDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.edit().putBoolean("isSaved", true).apply();
                prefs.edit().putFloat("x", gopher.getX()).apply();
                prefs.edit().putFloat("y", gopher.getY()).apply();
                Toast.makeText(v.getContext(), v.getContext().getString(R.string.saved), Toast.LENGTH_SHORT).show();
            }
        });
        setSupportActionBar(toolbar);
        boolean isSaved=prefs.getBoolean("isSaved",false);
        if(isSaved){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gopher.setX(prefs.getFloat("x",0));
                    gopher.setY(prefs.getFloat("y",0));
                    prefs.edit().putBoolean("isSaved", false).apply();
                }
            }, 500);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        showAnimation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        AboutUsDialogFragment dialog = new AboutUsDialogFragment();
        dialog.show(getFragmentManager(), "test");
        return false;
    }

    public void showAnimation()
    {
        Drawable[] drawables=  animation.getCompoundDrawables();
        for(Drawable drawable : drawables)
        {
            if(drawable != null && drawable instanceof Animatable){
                Animatable animatable = (Animatable)drawable;
                animatable.start();
            }
        }

    }
}
