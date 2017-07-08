/*
 * Copyright (c) 2017. Truiton (http://www.truiton.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * Mohit Gupt (https://github.com/mohitgupt)
 *
 */

/*

https://stackoverflow.com/questions/1555109/stop-edittext-from-gaining-focus-at-activity-startup




 */


package com.example.vikram.res_recycler;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<DataObject> mDataset;
    private static MyClickListener myClickListener;
    public static String valString="";
    public static int position1;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView label;
        TextView dateTime;
        EditText insertData;
        int position;

        public DataObjectHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.textView);
            insertData = (EditText) itemView.findViewById(R.id.editTextData);
            //dateTime = (TextView) itemView.findViewById(R.id.textView2);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            try {
                myClickListener.onItemClick(getAdapterPosition(), v);
            }
            catch(Exception e){
                Log.d(LOG_TAG,e.toString());

            }
            position = getAdapterPosition();
            position1 = position;
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    //https://stackoverflow.com/questions/6203609/android-percentage-layout-height
    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);




        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {


        holder.label.setText(mDataset.get(position).getmText1());
        //holder.dateTime.setText(mDataset.get(position).getmText2());
        holder.insertData.setText(mDataset.get(position).getmText3());
        final int pos = position;

        holder.insertData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                Log.d("Card2", "logged in"+pos);

            }
        });

        EditText editText2 = holder.insertData;
        editText2.setTag(position);
        MyTextWatcher textWatcher = new MyTextWatcher(editText2);
        editText2.addTextChangedListener(textWatcher);
        //editText2.setOnClickListener();


        /*
        editText2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if(!s.equals("") )
                { //do your work here }
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {
                valString = s.toString();


            }
        });
        */

    }

   // https://stackoverflow.com/questions/37915677/how-to-get-the-edit-text-position-from-recycler-view-adapter-using-text-watcher

    public class MyTextWatcher implements TextWatcher {
        private EditText editText;

        public MyTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int position = (int) editText.getTag();
            position1 = position;
            // Do whatever you want with position
        }

        @Override
        public void afterTextChanged(Editable s) {
            valString = s.toString();
            Log.d("Card2", "logged in"+position1);

        }
    }


    public void addItem(DataObject dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public int getItemClicked() {
        return position1;
    }

    public String getValString() {
        return valString;
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

}