package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/*public class SyllabusAdapter extends ArrayAdapter<SyllabusList> {
    public SyllabusAdapter(@NonNull Context context,ArrayList<SyllabusList> dataArrayAdapter) {
        super(context, R.layout.syllabus_contents,dataArrayAdapter);
    }




    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        SyllabusList listSyllabus = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.syllabus_contents, parent,false);

        }

        ImageView listImage = view.findViewById(R.id.ImageViewSyllabus);
        TextView listSubject =view.findViewById(R.id.SubjectOnSyllabus);
        TextView listTitle =view.findViewById(R.id.TitleOnSyllabus);
        TextView listSubTitle =view.findViewById(R.id.SubTitleOnSyllabus);
        TextView listClass =view .findViewById(R.id.classStengthNumberSyllabus);


        listImage.setImageResource(listSyllabus.SyllabusImage);
        listSubject.setText(listSyllabus.SubjectOnSyllabus);
        listTitle.setText(listSyllabus.SyllabusTitleOnSyllabus);
        listSubTitle.setText(listSyllabus.SyllabusSubTitleOnSyllabus);
        listClass.setText(listSyllabus.SyllabusClassLength);

        return view;
    }
}*/
