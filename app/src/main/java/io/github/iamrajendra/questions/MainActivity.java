package io.github.iamrajendra.questions;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;

import com.firebase.ui.database.FirebaseIndexRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

import io.github.iamrajendra.questions.holder.QuestionHolder;
import io.github.iamrajendra.questions.model.Question;

public class MainActivity extends AppCompatActivity {
private DatabaseReference mDatabaseReference;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FirebaseRecyclerAdapter<Question,QuestionHolder> adapter =
                new FirebaseRecyclerAdapter<Question, QuestionHolder>(Question.class,R.layout.row,QuestionHolder.class,mDatabaseReference) {
                    @Override
                    protected void populateViewHolder(final QuestionHolder viewHolder, final Question model, final int position) {
                        viewHolder.mTextView.setText(model.getQuestion());
                        viewHolder.mCheckBox1.setText(model.getOption1());
                        viewHolder.mCheckBox2.setText(model.getOption2());
                        viewHolder.mCheckBox3.setText(model.getOption3());
                        viewHolder.mCheckBox4.setText(model.getOption4());
                        viewHolder.mCheckBox1.setChecked(model.isValue1());
                        viewHolder.mCheckBox2.setChecked(model.isValue2());
                        viewHolder.mCheckBox3.setChecked(model.isValue3());
                        viewHolder.mCheckBox4.setChecked(model.isValue4());


                        CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener(){
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                                switch (compoundButton.getId())
                                {
                                    case R.id.option1:

                                        model.setValue1(b);

                                        break;
                                    case R.id.option2:
                                        model.setValue2(b);
                                        break;
                                    case R.id.option3:
                                        model.setValue3(b);
                                        break;
                                    case R.id.option4:
                                        model.setValue4(b);
                                        break;
                                }
                               DatabaseReference reference  = getRef(position);
                                reference.setValue(model);
                            }
                        };
                        viewHolder.mCheckBox1.setOnCheckedChangeListener(checkedChangeListener);
                        viewHolder.mCheckBox2.setOnCheckedChangeListener(checkedChangeListener);
                        viewHolder.mCheckBox3.setOnCheckedChangeListener(checkedChangeListener);
                        viewHolder.mCheckBox4.setOnCheckedChangeListener(checkedChangeListener);

                    }
                };

        RecyclerView   recyclerView  = (RecyclerView)findViewById(R.id.quetion_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
