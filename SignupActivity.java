package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.Year;
import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {
    ArrayList<DeptItem> Department ;
    DeptAdapter adapter;
    ArrayList<Section> Section ;
    SectionAdapter sec_Adapter;
    ArrayList<YearItem> year_lists ;
    YearAdapter year_adapter;
    EditText name_txt,roll_txt,phone_txt;
    Spinner spinner_Dept,spinner_Year,spinner_Sec;
    Button next_btn;
    String SectionItemName,YearItemName,deptItemName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name_txt =findViewById(R.id.name_con);
        roll_txt =findViewById(R.id.roll_con);
        phone_txt =findViewById(R.id.phone_con);
        next_btn =findViewById(R.id.next_btn);
        spinner_Dept = findViewById(R.id.dept_con);
        spinner_Year = findViewById(R.id.year_con);
        spinner_Sec = findViewById(R.id.section_con);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NAME = name_txt.getText().toString().trim();
                String ROLL = roll_txt.getText().toString().trim();
                String PHONE = phone_txt.getText().toString().trim();
                String DEPARTMENT = spinner_Dept.getSelectedItem().toString();
                String YEAR = spinner_Year.getSelectedItem().toString();
                String SECTION = spinner_Sec.getSelectedItem().toString();
                if (!validateName() || !validatePhoneNo() || !validateRoll()) {
                    return ;
                } else {
                    Intent i = new Intent(SignupActivity.this, Signup2Activity.class);
                    i.putExtra("NAME", NAME);
                    i.putExtra("ROLL", ROLL);
                    i.putExtra("PHONE", PHONE);
                    i.putExtra("DEPT", deptItemName);
                    i.putExtra("YEAR", YearItemName);
                    i.putExtra("SECTION", SectionItemName);
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    startActivity(i);

                }
            }
        });











        SpinnerContentsUser();


    }

    private void SpinnerContentsUser() {


        //Dept Spinner content
        initList();


        // we pass our item list and context to our Adapter.



        adapter = new DeptAdapter(this, Department);
        spinner_Dept.setAdapter(adapter);
        spinner_Dept.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        // It returns the clicked item.
                        DeptItem clickedItem = (DeptItem)
                                parent.getItemAtPosition(position);
                        deptItemName = clickedItem.getDeptItemName();
                        Toast.makeText(getApplicationContext(), deptItemName + " selected", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });





        ///


        YearContent();
        Spinner spinner_Year = findViewById(R.id.year_con);

        // we pass our item list and context to our Adapter.
        year_adapter = new YearAdapter(this, year_lists);
        spinner_Year.setAdapter(year_adapter);

        spinner_Year.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        // It returns the clicked item.
                        YearItem clickedItem = (YearItem)
                                parent.getItemAtPosition(position);
                        YearItemName = clickedItem.getYearItemName();
                        Toast.makeText(getApplicationContext(), YearItemName + " selected", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });

        /////////////////////////

        SectionContent();


        // we pass our item list and context to our Adapter.
        sec_Adapter = new SectionAdapter(this, Section);
        spinner_Sec.setAdapter(sec_Adapter);

        spinner_Sec.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id)
                    {

                        // It returns the clicked item.
                        Section clickedItem = (Section)
                                parent.getItemAtPosition(position);
                        SectionItemName = clickedItem.getSectionName();
                        Toast.makeText(getApplicationContext(), SectionItemName + " selected", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {
                    }
                });
    }

    // It is used to set the Department names to our array list.
    private void initList()
    {
        Department = new ArrayList<>();
        Department.add(new DeptItem("Select The Department"));
        Department.add(new DeptItem("BA"));
        Department.add(new DeptItem("B.SC(CS)"));
        Department.add(new DeptItem("B.SC(IT)"));
        Department.add(new DeptItem("BCA"));
        Department.add(new DeptItem("BBA"));
        Department.add(new DeptItem("B.COM"));
    }
    private void YearContent()
    {
        year_lists = new ArrayList<>();
        year_lists.add(new YearItem("Select The Your Studying Year"));
        year_lists.add(new YearItem("I"));
        year_lists.add(new YearItem("II"));
        year_lists.add(new YearItem("III"));
    }


    private void SectionContent()
    {
        Section = new ArrayList<>();
        Section.add(new Section("Select The Your Section A or B"));
        Section.add(new Section("A"));
        Section.add(new Section("B"));
    }
    private Boolean validatePhoneNo() {
        String val = phone_txt.getText().toString();

        if (val.isEmpty()) {
            phone_txt.setError("Field cannot be empty");
            return false;
        } else if (val.length()>10) {
            phone_txt.setError("Does not Phone Number");
            return false;

        } else {
            phone_txt.setError(null);
            return true;
        }
    }
    private Boolean validateName() {
        String val = name_txt.getText().toString();

        if (val.isEmpty()) {
            name_txt.setError("Field cannot be empty");
            return false;
        } else {
            name_txt.setError(null);
            return true;
        }
    }
    private Boolean validateRoll() {
        String val = roll_txt.getText().toString();



        if (val.isEmpty()) {
            roll_txt.setError("Field cannot be empty");
            return false;
        } else {
            roll_txt.setError(null);
            return true;
        }
    }


}
