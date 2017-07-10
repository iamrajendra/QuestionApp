package io.github.iamrajendra.questions;

/**
 * Created by rajendra on 10/7/17.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import io.github.iamrajendra.questions.model.Question;

/**
 * Created by gwl on 10/7/17.
 */

public class FirebaseAdapter extends RecyclerView.Adapter<FirebaseAdapter.MyViewHolder> {
    private static final String TAG = "FirebaseAdap";
    Context mContext;
    DatabaseReference mDatabaseReference;
    List<Question> mList=new ArrayList<>();
    String key;
    Question mQuestion;


    public FirebaseAdapter(Context mContext) {
        this.mContext=mContext;
        mDatabaseReference= FirebaseDatabase.getInstance().getReference("Question");
        updateList();
        // this.mList=list;
    }

    private void updateList() {

        ChildEventListener childEventListener=new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                Question users = dataSnapshot.getValue(Question.class);
                mList.add(users);

                notifyItemInserted(getItemCount() - 1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mDatabaseReference.addChildEventListener(childEventListener);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int i) {

        mQuestion = mList.get(i);


        viewHolder.textView_user.setText(mQuestion.getQuestion());
        viewHolder.checkbox_opt1.setText(mQuestion.getOption1());
        viewHolder.checkbox_opt2.setText(mQuestion.getOption2());
        viewHolder.checkbox_opt3.setText(mQuestion.getOption3());
        viewHolder.checkbox_opt4.setText(mQuestion.getOption4());

        viewHolder.checkbox_opt1.setChecked(mQuestion.isValue1());
        viewHolder.checkbox_opt2.setChecked(mQuestion.isValue2());
        viewHolder.checkbox_opt3.setChecked(mQuestion.isValue3());
        viewHolder.checkbox_opt4.setChecked(mQuestion.isValue4());

        // key=mDatabaseReference.getRef().getKey();




        CompoundButton.OnCheckedChangeListener checkedChangeListener=new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                key= String.valueOf(i);
                Log.i(TAG,"onCheckedChanged: "+key);
                switch (buttonView.getId()) {

                    case R.id.option1:
                        mQuestion.setValue1(isChecked);
                        mDatabaseReference.child(key).child("option1").setValue(isChecked);


                        break;
                    case R.id.option2:
                        mQuestion.setValue2(isChecked);
                        mDatabaseReference.child(key).child("value2").setValue(isChecked);
                        break;
                    case R.id.option3:
                        mQuestion.setValue3(isChecked);
                        mDatabaseReference.child(key).child("value3").setValue(isChecked);
                        break;
                    case R.id.option4:
                        mQuestion.setValue4(isChecked);
                        mDatabaseReference.child(key).child("value4").setValue(isChecked);
                        break;

                }
            }
        };

        viewHolder.checkbox_opt1.setOnCheckedChangeListener(checkedChangeListener);
        viewHolder.checkbox_opt2.setOnCheckedChangeListener(checkedChangeListener);
        viewHolder.checkbox_opt3.setOnCheckedChangeListener(checkedChangeListener);
        viewHolder.checkbox_opt4.setOnCheckedChangeListener(checkedChangeListener);
    }

    @Override
    public int getItemCount() {
        return mList==null ? 0 :mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_user;
        public CheckBox checkbox_opt1, checkbox_opt2, checkbox_opt3, checkbox_opt4;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView_user = (TextView) itemView.findViewById(R.id.question);
            checkbox_opt1 = (CheckBox) itemView.findViewById(R.id.option1);
            checkbox_opt2 = (CheckBox) itemView.findViewById(R.id.option2);
            checkbox_opt3 = (CheckBox) itemView.findViewById(R.id.option3);
            checkbox_opt4 = (CheckBox) itemView.findViewById(R.id.option4);
        }
    }
}