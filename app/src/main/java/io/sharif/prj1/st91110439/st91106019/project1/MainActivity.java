package io.sharif.prj1.st91110439.st91106019.project1;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView animation;
    private ImageButton arrowUp;
    private ImageButton arrowDown;
    private ImageButton arrowRight;
    private ImageButton arrowLeft;
    private ImageView gopher;

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
        arrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float y = gopher.getY();
                gopher.setY(y-10);
            }
        });
        arrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float y = gopher.getY();
                gopher.setY(y+10);
            }
        });
        arrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x = gopher.getX();
                gopher.setX(x-10);
            }
        });
        arrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x = gopher.getX();
                gopher.setX(x+10);
            }
        });
        setSupportActionBar(toolbar);
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

        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AboutUsDialogFragment dialog = new AboutUsDialogFragment();
                dialog.show(getFragmentManager(), "test");
                return false;
            }
        });
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
