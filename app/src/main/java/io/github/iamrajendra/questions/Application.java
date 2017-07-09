package io.github.iamrajendra.questions;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by rajendra on 8/7/17.
 */

public class Application extends android.app.Application {
    private DatabaseReference mDatabase;

    public DatabaseReference getFirebaseInstance() {

        mDatabase = FirebaseDatabase.getInstance().getReference();

        return mDatabase;
    }

}
