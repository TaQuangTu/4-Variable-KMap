package helloworld.com.taquangtu132gmail.taquangtu.kmapwithintent;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import static helloworld.com.taquangtu132gmail.taquangtu.kmapwithintent.R.drawable.chamthan;

public class loginActivity extends AppCompatActivity {
    private ImageButton btStart;
    private ImageButton btReadMe;
    private ImageButton btExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        map();
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(loginActivity.this,KmapActivity.class);
                startActivity(it);
            }
        });
        btReadMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent it=new Intent(loginActivity.this,ReadMeActivity.class);
                  startActivity(it);
            }
        });
        btExit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder al = new AlertDialog.Builder(loginActivity.this);
                al.setIcon(chamthan);
                al.setTitle("Warning!!!");
                al.setMessage("Do you want to exit?");
                al.setCancelable(true);
                al.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                al.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                al.show();
            }
        });
    }
    private void map()
    {
        this.btExit=(ImageButton) findViewById(R.id.bt_exit_id);
        this.btReadMe=(ImageButton) findViewById(R.id.bt_readme_id);
        this.btStart=(ImageButton) findViewById(R.id.bt_start_id);
    }
    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder al=new AlertDialog.Builder(loginActivity.this);
        al.setTitle("Warning!!!");
        al.setIcon(chamthan);
        al.setMessage("Do you want to exit?");
        al.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        al.setCancelable(false);
        al.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do nothing
            }
        });
        al.show();
        return;
    }
}
