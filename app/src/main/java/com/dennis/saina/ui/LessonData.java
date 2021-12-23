package com.dennis.saina.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class LessonData {


    public LessonData(Context context) {

    }


    public static void EventChangeListener(Context context, RecyclerView.Adapter adapter, ArrayList<Lesson> lessonArrayList) {

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Fetching Data ...");
        progressDialog.show();


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("ZSL").orderBy("Name", Query.Direction.ASCENDING)
                .addSnapshotListener((value, e) -> {
                    if (e != null) {
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        Log.e("Firestore error", e.getMessage());
                        return;
                    }

                    assert value != null;
                    for (DocumentChange dc : value.getDocumentChanges()) {

                        if (dc.getType() == DocumentChange.Type.ADDED) {
                            //if data is added, populate our arrayList

                            lessonArrayList.add(dc.getDocument().toObject(Lesson.class));

                        }

                        adapter.notifyDataSetChanged();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                    }
                });
    }
}
