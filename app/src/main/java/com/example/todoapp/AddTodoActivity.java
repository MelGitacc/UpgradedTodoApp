package com.example.todoapp;

import android.content.ContentValues;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.Toast;


        import com.example.todoapp.Data.TaskContract;


    public class AddTodoActivity extends AppCompatActivity {

    // Declare a variable to keep track of a task's selected PriorityTracker
    private int PriorityTracker;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        // Initialize to highest Tracker by default (PriorityTracker = 1)
        ((RadioButton) findViewById(R.id.radButton1)).setChecked(true);
        PriorityTracker = 1;
    }


    /**
     * onClickAddTask is called when the "ADD" button is clicked.
     * It retrieves user input and inserts that new task data into the underlying database.
     */
    public void onClickAddTask(View view) {
        // Check if EditText is empty, if not then retrieve input and store it in a ContentValues object
        // If the EditText input is empty -> don't create an entry and toast message will appear
        String input = ((EditText) findViewById(R.id.editTextTaskDescription)).getText().toString();
        if (input.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Enter a Task", Toast.LENGTH_LONG).show();
            return;
        }

        // Insert new task data through the ContentResolver
        // Create new empty ContentValues object
        ContentValues contentValues = new ContentValues();

        // Put the task description and selected PriorityTracker into the ContentValues
        contentValues.put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, input);
        contentValues.put(TaskContract.TaskEntry.COLUMN_PRIORITY, PriorityTracker);

        // Insert the content values through a ContentResolver
        Uri uri = getContentResolver().insert(TaskContract.TaskEntry.CONTENT_URI, contentValues);

        // Display the URI that's returned with a Toast
        // [Hint] Don't forget to call finish() to return to MainActivity after this insert is complete
        if(uri != null) {
            Toast.makeText(getApplicationContext(),"Task Added into your List", Toast.LENGTH_LONG).show();
        }

        // Finish activity is called which returns back to MainActivity
        finish();

    }


    /**
     * onPrioritySelected is called whenever a priority button is clicked.
     * the value of PriorityTracker changes based on the button selected.
     */
    public void onPrioritySelected(View view) {
        if (((RadioButton) findViewById(R.id.radButton1)).isChecked()) {
            PriorityTracker = 1;
        } else if (((RadioButton) findViewById(R.id.radButton2)).isChecked()) {
            PriorityTracker = 2;
        } else if (((RadioButton) findViewById(R.id.radButton3)).isChecked()) {
            PriorityTracker = 3;
        }
    }
}