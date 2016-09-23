package com.example.android.techquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Author : Abhishek Nainawat
 * Email : elite.abhishek@gmail.com
 *
 * This app displays a tech quiz to test your knowledge based on your answers for the questions
 * displayed. Results are displayed in a Toast.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the View Result button is clicked.
     */
    public void showResult(View view) {

        EditText candidateNameEditText = (EditText) findViewById(R.id.candidate_name);
        String candidateName = candidateNameEditText.getText().toString();

        EditText candidateAgeEditText = (EditText) findViewById(R.id.candidate_age);
        String candidateAge = candidateAgeEditText.getText().toString();

        EditText answer7EditText = (EditText) findViewById(R.id.answer7);
        String answer7Text = answer7EditText.getText().toString();

        RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.radio_group_1);
        short answer1 = evaluateRadioAnswer(radioGroup1);

        RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.radio_group_2);
        short answer2 = evaluateRadioAnswer(radioGroup2);

        LinearLayout checkBoxGroup3 = (LinearLayout) findViewById(R.id.checkbox_group_3);
        short answer3 = evaluateCheckBoxAnswer(checkBoxGroup3);

        LinearLayout checkBoxGroup4 = (LinearLayout) findViewById(R.id.checkbox_group_4);
        short answer4 = evaluateCheckBoxAnswer(checkBoxGroup4);

        RadioGroup radioGroup5 = (RadioGroup) findViewById(R.id.radio_group_5);
        short answer5 = evaluateRadioAnswer(radioGroup5);

        RadioGroup radioGroup6 = (RadioGroup) findViewById(R.id.radio_group_6);
        short answer6 = evaluateRadioAnswer(radioGroup6);

        short answer7 = 1;        //Opinion based question. No incorrect answer.

        short result = (short) (answer1 + answer2 + answer3 + answer4 + answer5 + answer6 + answer7);

        //Get empty responses, if any
        String emptyResponseWarningText = checkEmptyResponses(candidateName, candidateAge, radioGroup1,
                radioGroup2, checkBoxGroup3, checkBoxGroup4, radioGroup5, radioGroup6, answer7Text);

        if (emptyResponseWarningText.matches("")) {        // No empty responses

            String resultToastText = "";

            if (result >= 4) {
                resultToastText += getString(R.string.candidate_name_and_age_good, candidateName, candidateAge) + "\n" +
                        getString(R.string.result_score, result) + "\n" +
                        getString(R.string.answer_7, answer7Text);
            } else {
                resultToastText += getString(R.string.candidate_name_and_age_fair, candidateName, candidateAge) + "\n" +
                        getString(R.string.result_score, result) + "\n" +
                        getString(R.string.answer_7, answer7Text) + "\n" +
                        getString(R.string.keep_learning);
            }

            Toast resultToast = Toast.makeText(this, resultToastText, Toast.LENGTH_LONG);
            resultToast.show();
        } else {        // Show empty responses warning text
            Toast emptyResponseWarning = Toast.makeText(this, emptyResponseWarningText, Toast.LENGTH_LONG);
            emptyResponseWarning.show();
        }
    }

    /**
     * Checks for empty responses and generate warning text accordingly.
     *
     * @param candidateName  is the candidate name entered.
     * @param candidateAge   is the age of the candidate as entered.
     * @param radioGroup1    is the question 1 radio buttons group.
     * @param radioGroup2    is the question 2 radio buttons group.
     * @param checkBoxGroup3 is the question 3 checkboxes group.
     * @param checkBoxGroup4 is the question 4 checkboxes group.
     * @param radioGroup5    is the question 5 radio buttons group.
     * @param radioGroup6    is the question 6 radio buttons group.
     * @param answer7Text    is the favourite tech magazine/blog/vlog the candidate entered.
     * @return the final empty response warning text.
     */
    private String checkEmptyResponses(String candidateName, String candidateAge, RadioGroup radioGroup1,
                                       RadioGroup radioGroup2, LinearLayout checkBoxGroup3,
                                       LinearLayout checkBoxGroup4, RadioGroup radioGroup5,
                                       RadioGroup radioGroup6, String answer7Text) {

        // Check if Name empty
        boolean isNameEmpty = (candidateName.matches(""));

        // Check if Age empty
        boolean isAgeEmpty = (candidateAge.matches(""));

        // Check if Answer 1 empty
        boolean isAnswer1Empty = (radioGroup1.getCheckedRadioButtonId() == -1);

        // Check if Answer 2 empty
        boolean isAnswer2Empty = (radioGroup2.getCheckedRadioButtonId() == -1);

        // Check if Answer 3 empty
        boolean isAnswer3Empty =
                (!((CheckBox) checkBoxGroup3.findViewById(R.id.checkbox3_1)).isChecked()) &&
                        (!((CheckBox) checkBoxGroup3.findViewById(R.id.checkbox3_2)).isChecked()) &&
                        (!((CheckBox) checkBoxGroup3.findViewById(R.id.checkbox3_3)).isChecked()) &&
                        (!((CheckBox) checkBoxGroup3.findViewById(R.id.checkbox3_4)).isChecked());

        // Check if Answer 4 empty
        boolean isAnswer4Empty =
                (!((CheckBox) checkBoxGroup4.findViewById(R.id.checkbox4_1)).isChecked()) &&
                        (!((CheckBox) checkBoxGroup4.findViewById(R.id.checkbox4_2)).isChecked()) &&
                        (!((CheckBox) checkBoxGroup4.findViewById(R.id.checkbox4_3)).isChecked()) &&
                        (!((CheckBox) checkBoxGroup4.findViewById(R.id.checkbox4_4)).isChecked());

        // Check if Answer 5 empty
        boolean isAnswer5Empty = (radioGroup5.getCheckedRadioButtonId() == -1);

        // Check if Answer 6 empty
        boolean isAnswer6Empty = (radioGroup6.getCheckedRadioButtonId() == -1);

        // Check if Answer 7 empty
        boolean isAnswer7Empty = (answer7Text.matches(""));

        String emptyResponseWarningText = "";

        if (isNameEmpty) {
            emptyResponseWarningText += getString(R.string.empty_name) + "\n";
        }

        if (isAgeEmpty) {
            emptyResponseWarningText += getString(R.string.empty_age) + "\n";
        }

        if (isAnswer1Empty) {
            emptyResponseWarningText += getString(R.string.empty_answer_1) + "\n";
        }

        if (isAnswer2Empty) {
            emptyResponseWarningText += getString(R.string.empty_answer_2) + "\n";
        }

        if (isAnswer3Empty) {
            emptyResponseWarningText += getString(R.string.empty_answer_3) + "\n";
        }

        if (isAnswer4Empty) {
            emptyResponseWarningText += getString(R.string.empty_answer_4) + "\n";
        }

        if (isAnswer5Empty) {
            emptyResponseWarningText += getString(R.string.empty_answer_5) + "\n";
        }

        if (isAnswer6Empty) {
            emptyResponseWarningText += getString(R.string.empty_answer_6) + "\n";
        }

        if (isAnswer7Empty) {
            emptyResponseWarningText += getString(R.string.empty_answer_7);
        }

        return emptyResponseWarningText;
    }

    /**
     * Evaluates whether the answer is correct for radio group type questions.
     *
     * @param radioGroup is the radio group type question being evaluated.
     * @return 0 for incorrect answer and 1 for correct answer.
     */
    private short evaluateRadioAnswer(RadioGroup radioGroup) {
        short answer = 0;

        if (radioGroup == findViewById(R.id.radio_group_1)) {

            RadioButton correctRadioButton = (RadioButton) findViewById(R.id.radio1_3);

            if (correctRadioButton.isChecked()) {
                answer = 1;
            } else answer = 0;
        }

        if (radioGroup == findViewById(R.id.radio_group_2)) {

            RadioButton correctRadioButton = (RadioButton) findViewById(R.id.radio2_4);

            if (correctRadioButton.isChecked()) {
                answer = 1;
            } else answer = 0;
        }

        if (radioGroup == findViewById(R.id.radio_group_5)) {

            RadioButton correctRadioButton = (RadioButton) findViewById(R.id.radio5_2);

            if (correctRadioButton.isChecked()) {
                answer = 1;
            } else answer = 0;
        }

        if (radioGroup == findViewById(R.id.radio_group_6)) {

            RadioButton correctRadioButton = (RadioButton) findViewById(R.id.radio6_3);

            if (correctRadioButton.isChecked()) {
                answer = 1;
            } else answer = 0;
        }

        return answer;
    }

    /**
     * Evaluates whether the answer is correct for checkbox type questions.
     *
     * @param checkBoxGroup is the checkboxes type question being evaluated.
     * @return 0 for incorrect answer and 1 for correct answer.
     */
    private short evaluateCheckBoxAnswer(LinearLayout checkBoxGroup) {
        short answer = 0;

        if (checkBoxGroup == findViewById(R.id.checkbox_group_3)) {

            CheckBox correctCheckBox1 = (CheckBox) findViewById(R.id.checkbox3_1);
            CheckBox correctCheckBox2 = (CheckBox) findViewById(R.id.checkbox3_4);
            CheckBox wrongCheckBox1 = (CheckBox) findViewById(R.id.checkbox3_2);
            CheckBox wrongCheckBox2 = (CheckBox) findViewById(R.id.checkbox3_3);

            if (correctCheckBox1.isChecked()
                    && correctCheckBox2.isChecked()
                    && !wrongCheckBox1.isChecked()
                    && !wrongCheckBox2.isChecked()) {
                answer = 1;
            } else answer = 0;
        }

        if (checkBoxGroup == findViewById(R.id.checkbox_group_4)) {

            CheckBox correctCheckBox1 = (CheckBox) findViewById(R.id.checkbox4_1);
            CheckBox correctCheckBox2 = (CheckBox) findViewById(R.id.checkbox4_3);
            CheckBox wrongCheckBox1 = (CheckBox) findViewById(R.id.checkbox4_2);
            CheckBox wrongCheckBox2 = (CheckBox) findViewById(R.id.checkbox4_4);

            if (correctCheckBox1.isChecked()
                    && correctCheckBox2.isChecked()
                    && !wrongCheckBox1.isChecked()
                    && !wrongCheckBox2.isChecked()) {
                answer = 1;
            } else answer = 0;
        }

        return answer;
    }

    /**
     * This method is called when the reset button is clicked.
     * It sets all text fields to empty and all responses to unanswered.
     */
    public void reset(View view) {

        EditText nameEditText = (EditText) findViewById(R.id.candidate_name);
        EditText ageEditText = (EditText) findViewById(R.id.candidate_age);

        RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.radio_group_1);
        RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.radio_group_2);
        RadioGroup radioGroup5 = (RadioGroup) findViewById(R.id.radio_group_5);
        RadioGroup radioGroup6 = (RadioGroup) findViewById(R.id.radio_group_6);

        CheckBox CheckBox3_1 = (CheckBox) findViewById(R.id.checkbox3_1);
        CheckBox CheckBox3_2 = (CheckBox) findViewById(R.id.checkbox3_4);
        CheckBox CheckBox3_3 = (CheckBox) findViewById(R.id.checkbox3_2);
        CheckBox CheckBox3_4 = (CheckBox) findViewById(R.id.checkbox3_3);

        CheckBox CheckBox4_1 = (CheckBox) findViewById(R.id.checkbox4_1);
        CheckBox CheckBox4_2 = (CheckBox) findViewById(R.id.checkbox4_3);
        CheckBox CheckBox4_3 = (CheckBox) findViewById(R.id.checkbox4_2);
        CheckBox CheckBox4_4 = (CheckBox) findViewById(R.id.checkbox4_4);

        EditText answer7EditText = (EditText) findViewById(R.id.answer7);

        nameEditText.setText("");

        ageEditText.setText("");

        radioGroup1.clearCheck();

        radioGroup2.clearCheck();

        CheckBox3_1.setChecked(false);
        CheckBox3_2.setChecked(false);
        CheckBox3_3.setChecked(false);
        CheckBox3_4.setChecked(false);

        CheckBox4_1.setChecked(false);
        CheckBox4_2.setChecked(false);
        CheckBox4_3.setChecked(false);
        CheckBox4_4.setChecked(false);

        radioGroup5.clearCheck();

        radioGroup6.clearCheck();

        answer7EditText.setText("");
    }

}
