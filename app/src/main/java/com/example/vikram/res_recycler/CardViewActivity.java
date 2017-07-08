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

package com.example.vikram.res_recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


public class CardViewActivity extends android.support.v4.app.DialogFragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v1 = inflater.inflate(R.layout.activity_card_view, container, false);
        //View v1 = inflater.inflate(R.layout.card_view_row, container, false);
        mRecyclerView =  (RecyclerView) v1.findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);


        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                Log.i(LOG_TAG, " Clicked on Item " + position + "  ");


            }

        });
        Button startButton = (Button) v1.findViewById(R.id.buttonAddItem);
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int j1 =  ((MyRecyclerViewAdapter) mAdapter).getItemCount();

                DataObject obj = new DataObject("Some Primary Text " + j1,
                        "Secondary " + j1,"Third " + j1);
                ((MyRecyclerViewAdapter)mAdapter).addItem(obj,j1);
                Log.i(LOG_TAG, " Add Item " );

            }
        });

        Button okButton = (Button) v1.findViewById(R.id.buttonOk);
        okButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {

                                               String st3 = ((MyRecyclerViewAdapter) mAdapter).getValString();
                                               int j3 = ((MyRecyclerViewAdapter) mAdapter).getItemClicked();


                                               Log.i(LOG_TAG, j3+ " Get Text "+st3 );




                                           }
                                       });
        //v1.setOnClickListener(this);

        return v1;
    }


    //pg. 647 for a bind ; pg. 769 here's where you update UI on new add item
    //https://stackoverflow.com/questions/28379302/add-clicklistner-for-button-inside-a-cardview-populated-using-a-recyclerview

    public void onResume() {
        super.onResume();



    }
/*
    public void onClick(View view){

        Log.i(LOG_TAG, " Clicked on Item " );

    }
*/

    private ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<DataObject>();
        for (int index = 0; index < 7; index++) {
            DataObject obj = new DataObject("Some Primary Text " + index,
                    "Secondary " + index,"Third " + index);
            results.add(index, obj);
        }
        return results;
    }
}
