package com.example.collegeappmanager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;

import android.content.DialogInterface;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.view.Gravity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.Toast;
import android.widget.RadioButton;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button DOB;
    private DatePickerDialog.OnDateSetListener DOBSetListener;

    private Button Majors;
    String major = "";
    String[] listMajors;
    boolean[] checkedMajors;
    ArrayList<Integer> userMajors = new ArrayList<>();

    private Button Colleges;
    String college = "";
    String[] listColleges;
    boolean[] checkedColleges;
    ArrayList<Integer> userColleges = new ArrayList<>();

    private Button Login;
    private Button CreateProfile;

    private EditText LoginUsername;
    private EditText LoginPassword;
    private EditText Username;
    private EditText Password;
    private EditText ConfirmPassword;
    private EditText FirstName;
    private EditText LastName;
    private EditText HighSchool;
    private EditText GPA;
    private EditText City;
    private EditText State;
    private EditText Address;
    private EditText Email;
    private EditText SATScore;
    private EditText ACTScore;
    private EditText SATSubjectTestScores;
    private EditText APTestScores;

    private RadioButton SATYes;
    private RadioButton SATNo;
    private RadioButton ACTYes;
    private RadioButton ACTNo;

    LinkedHashMap<String, String> ProfileInfo = new LinkedHashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create DOB selector dialog on button click

        DOB = (Button) findViewById(R.id.ButtonDOB);

        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light,
                        DOBSetListener,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();
            }
        });

        DOBSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                DOB.setText(date);
                DOB.setBackgroundColor(Color.TRANSPARENT);

            }
        };

        //Create majors selector on button click

        Majors = (Button) findViewById(R.id.ButtonMajors);

        listMajors = getResources().getStringArray(R.array.majors);
        checkedMajors = new boolean[listMajors.length];

        Majors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder =
                        new AlertDialog.Builder(
                                MainActivity.this);
                mBuilder.setTitle("Possible Majors");
                mBuilder.setMultiChoiceItems(
                        listMajors, checkedMajors,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position,
                                                boolean isChecked) {
                                if (isChecked) {
                                    if (!userMajors.contains(position)) {
                                        userMajors.add(position);
                                    }
                                } else if (userMajors.contains(position)) {
                                    userMajors.remove(position);
                                }
                            }
                        }
                );

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < userMajors.size(); i++) {
                            major = major + listMajors[userMajors.get(i)];
                            if (i != userMajors.size() - 1) {
                                major = major + ", ";
                            }
                        }

                        //major is a string containing selected majors separated by comma

                    }
                });

                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                mBuilder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkedMajors.length; i++) {
                            checkedMajors[i] = false;
                            userMajors.clear();
                        }
                    }
                });

                AlertDialog Dialog = mBuilder.create();
                Dialog.show();
            }
        });

        /*Create colleges selector on button click (selector will eventually only contain
        names populated by representatives from colleges
         */

        Colleges = (Button) findViewById(R.id.ButtonColleges);

        listColleges = getResources().getStringArray(R.array.colleges);
        checkedColleges = new boolean[listColleges.length];

        Colleges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder2 =
                        new AlertDialog.Builder(
                                MainActivity.this);
                mBuilder2.setTitle("Possible Colleges");
                mBuilder2.setMultiChoiceItems(
                        listColleges, checkedColleges,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int position,
                                                boolean isChecked) {
                                if (isChecked) {
                                    if (!userColleges.contains(position)) {
                                        userColleges.add(position);
                                    }
                                } else if (userColleges.contains(position)) {
                                    userColleges.remove(position);
                                }
                            }
                        }
                );

                mBuilder2.setCancelable(false);
                mBuilder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        for (int i = 0; i < userColleges.size(); i++) {
                            college = college + listColleges[userColleges.get(i)];
                            if (i != userColleges.size() - 1) {
                                college = college + ", ";
                            }
                        }

                        //college is a string containing selected colleges separated by comma

                    }
                });

                mBuilder2.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                mBuilder2.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkedColleges.length; i++) {
                            checkedColleges[i] = false;
                            userColleges.clear();
                        }
                    }
                });

                AlertDialog Dialog2 = mBuilder2.create();
                Dialog2.show();
            }
        });

        Username = (EditText) findViewById(R.id.EditTextUsername);
        Password = (EditText) findViewById(R.id.EditTextPassword);
        ConfirmPassword = (EditText) findViewById(R.id.EditTextConfirmPassword);
        FirstName = (EditText) findViewById(R.id.EditTextFirstName);
        LastName = (EditText) findViewById(R.id.EditTextLastName);
        HighSchool = (EditText) findViewById(R.id.EditTextHighSchool);
        GPA = (EditText) findViewById(R.id.EditTextGPA);
        City = (EditText) findViewById(R.id.EditTextCity);
        State = (EditText) findViewById(R.id.EditTextState);
        Address = (EditText) findViewById(R.id.EditTextAddress);
        Email = (EditText) findViewById(R.id.EditTextEmail);
        SATScore = (EditText) findViewById(R.id.EditTextSATScore);
        ACTScore = (EditText) findViewById(R.id.EditTextACTScore);
        SATSubjectTestScores = (EditText) findViewById(R.id.EditTextSATSubjectTestScores);
        APTestScores = (EditText) findViewById(R.id.EditTextAPTestScores);

        SATYes = (RadioButton) findViewById(R.id.RadioButtonSATYes);
        SATNo = (RadioButton) findViewById(R.id.RadioButtonSATNo);
        ACTYes = (RadioButton) findViewById(R.id.RadioButtonACTYes);
        ACTNo = (RadioButton) findViewById(R.id.RadioButtonACTNo);

        CreateProfile = (Button) findViewById(R.id.ButtonCreateProfile);

        CreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Form Validation (required fields have hint text with asterisk, SAT/ACT score
                fields become required based on selected radio button for each)
                 */

                if (Username.getText() == null ||
                        Username.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please enter username",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (Password.getText() == null ||
                        Password.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please enter password",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (ConfirmPassword.getText() == null ||
                        ConfirmPassword.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please confirm password",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (!(ConfirmPassword.getText().toString().
                        equals(Password.getText().toString()))) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Password and confirm password must match",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (FirstName.getText() == null ||
                        FirstName.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please enter first name",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (LastName.getText() == null ||
                        LastName.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please enter last name",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (DOB.getText() == null ||
                        DOB.getText().toString().trim().toUpperCase().contains("SELECT")) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please select date of birth",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (major.equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please select at least one major",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (HighSchool.getText() == null ||
                        HighSchool.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please enter high school",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (college.equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please select at least one college",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (GPA.getText() == null ||
                        GPA.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please enter GPA",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (City.getText() == null ||
                        City.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please enter city",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (State.getText() == null ||
                        State.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please enter state",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (Address.getText() == null ||
                        Address.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please enter address",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (Email.getText() == null ||
                        Email.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please enter email",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (!(Email.getText().toString().contains("@") &&
                        Email.getText().toString().contains("."))) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please enter a valid email",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (SATYes.isChecked() == false && SATNo.isChecked() == false) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please indicate whether you've taken the SAT",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (ACTYes.isChecked() == false &&
                        ACTNo.isChecked() == false) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please indicate whether you've taken the ACT",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (SATYes.isChecked() == true &&
                        SATScore.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please enter SAT score",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (ACTYes.isChecked() == true &&
                        ACTScore.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please enter ACT score",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {

                    //Alert that profile has been created successfully

                    Toast toast = Toast.makeText(MainActivity.this,
                            "Profile created successfully",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                    //Populate Hashmap containing profile info
                    ProfileInfo.clear();
                    ProfileInfo.put("Username", Username.getText().toString());
                    ProfileInfo.put("Password", Password.getText().toString());
                    ProfileInfo.put("FirstName", FirstName.getText().toString());
                    ProfileInfo.put("LastName", LastName.getText().toString());
                    ProfileInfo.put("DOB", DOB.getText().toString());
                    ProfileInfo.put("Majors", major);
                    ProfileInfo.put("HighSchool", HighSchool.getText().toString());
                    ProfileInfo.put("Colleges", college);
                    ProfileInfo.put("GPA", GPA.getText().toString());
                    ProfileInfo.put("City", City.getText().toString());
                    ProfileInfo.put("State", State.getText().toString());
                    ProfileInfo.put("Address", Address.getText().toString());
                    ProfileInfo.put("Email", Email.getText().toString());

                    if (SATYes.isChecked() == true) {
                        ProfileInfo.put("TakenSAT", "Yes");
                    } else {
                        ProfileInfo.put("TakenSAT", "No");
                    }

                    if (ACTYes.isChecked() == true) {
                        ProfileInfo.put("TakenACT", "Yes");
                    } else {
                        ProfileInfo.put("TakenACT", "No");
                    }

                    ProfileInfo.put("SATScore", SATScore.getText().toString());
                    ProfileInfo.put("ACTScore", ACTScore.getText().toString());
                    ProfileInfo.put("SATSubjectTestScores",
                            SATSubjectTestScores.getText().toString());
                    ProfileInfo.put("APTestScores",
                            APTestScores.getText().toString());

                    for (String field : ProfileInfo.keySet()) {
                        System.out.println(field + ": " + ProfileInfo.get(field));
                    }


                }

            }
        });

        LoginUsername = (EditText) findViewById(R.id.EditTextLoginUsername);
        LoginPassword = (EditText) findViewById(R.id.EditTextLoginPassword);

        Login = (Button) findViewById(R.id.ButtonLogin);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String savedUsername = ProfileInfo.get("Username");
                String savedPassword = ProfileInfo.get("Password");

                //Form Validation - username and password needed to login

                if (LoginUsername.getText() == null ||
                        LoginUsername.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please enter username",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (LoginPassword.getText() == null ||
                        LoginPassword.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Please enter password",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (!(LoginUsername.getText().toString().equals(savedUsername)
                        && LoginPassword.getText().toString().equals(savedPassword))) {

                    Toast toast = Toast.makeText(MainActivity.this,
                            "Your login credentials are invalid. Please check " +
                                    "your username and password or create a profile " +
                                    "if you haven't already.",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Login Successful",
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }

            }
        });

    }


}
