package com.leejimin.android.layoutbasic_01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class DynamicGrid extends AppCompatActivity {

    GridLayout grid;
    int idx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_grid);

        Button button = (Button) findViewById(R.id.button);
        grid = (GridLayout) findViewById(R.id.gridView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Button newbutton = new Button(DynamicGrid.this);
                idx++;
                newbutton.setText(""+idx);
                newbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //grid.removeView(v);
                        grid.removeView(newbutton);

                    }
                });


                grid.addView(newbutton);

            }
        });



    }
}
