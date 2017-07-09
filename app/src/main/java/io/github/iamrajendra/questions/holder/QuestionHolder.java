package io.github.iamrajendra.questions.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import io.github.iamrajendra.questions.R;

/**
 * Created by rajendra on 9/7/17.
 */

public class QuestionHolder  extends RecyclerView.ViewHolder{
    public TextView mTextView;
    public CheckBox mCheckBox1;
    public CheckBox mCheckBox2;
    public CheckBox mCheckBox3;
    public CheckBox mCheckBox4;
    public QuestionHolder(View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.question);
        mCheckBox1 =itemView.findViewById(R.id.option1);
        mCheckBox2 =itemView.findViewById(R.id.option2);
        mCheckBox3 =itemView.findViewById(R.id.option3);
        mCheckBox4 =itemView.findViewById(R.id.option4);

    }
}
