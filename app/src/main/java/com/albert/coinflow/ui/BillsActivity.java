package com.albert.coinflow.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.albert.coinflow.Constants;
import com.albert.coinflow.R;
import com.albert.coinflow.models.Budget;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BillsActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mBudgetReference;
    private ValueEventListener mBudgetReferenceListener;
    @Bind(R.id.expensesEditText) EditText mExpensesText;
    @Bind(R.id.savedMoneyEditText) EditText mSavedMoneyText;
    @Bind(R.id.dayEditText) EditText mDateText;
    @Bind(R.id.addButton) Button mAddButton;
    @Bind(R.id.cancelButton) Button mCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);
        ButterKnife.bind(this);

        mAddButton.setOnClickListener(this);
        mCancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mCancelButton) {
            Intent intent = new Intent(BillsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        if (view == mAddButton) {
            String date = mDateText.getText().toString().trim();
            String expenses = mExpensesText.getText().toString().trim();
            String moneySave = mSavedMoneyText.getText().toString().trim();
            Budget budget = new Budget(date, expenses, moneySave);

            saveBudgetToFirebase(budget); // Method to save data to Firebase

            Intent intent = new Intent(BillsActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mBudgetReference.removeEventListener(mBudgetReferenceListener);
//    }

    private void saveBudgetToFirebase(Budget budget) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference budgetRef = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_BUDGET)
                .child(uid);
        DatabaseReference pushRef = budgetRef.push();

        String pushId = pushRef.getKey();
        budget.setPushId(pushId);
        pushRef.setValue(budget);
    }
}
