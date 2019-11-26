package com.jose.homework1;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jose.homework1.TaskFragment.OnListFragmentInteractionListener;
import com.jose.homework1.tasks.TaskListContent.Task;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Task} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTaskRecyclerViewAdapter extends RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder> {

    private final List<Task> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyTaskRecyclerViewAdapter(List<Task> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Task task = mValues.get(position);
        String sound = task.sound;
        holder.mItem = task;
        holder.mContentView.setText(task.username);

        int n = task.getN();

        Context context = holder.mView.getContext();
        Drawable taskDrawable;

        switch (n){
            case 1:
                taskDrawable = context.getResources().getDrawable(R.drawable.pic1);
                break;
            case 2:
                taskDrawable = context.getResources().getDrawable(R.drawable.pic2);
                break;
            case 3:
                taskDrawable = context.getResources().getDrawable(R.drawable.pic3);
                break;
            case 4:
                taskDrawable = context.getResources().getDrawable(R.drawable.pic4);
                break;
            case 5:
                taskDrawable = context.getResources().getDrawable(R.drawable.pic5);
                break;
            case 6:
                taskDrawable = context.getResources().getDrawable(R.drawable.pic6);
                break;
            default:
                taskDrawable = context.getResources().getDrawable(R.drawable.pic1);

        }
        holder.mItemImageView.setImageDrawable(taskDrawable);


        holder.mView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(null!=mListener){
                    mListener.onListFragmentClickInteraction(holder.mItem, position);
                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v){
                    mListener.onListFragmentLongClickInteraction(position);
                    return false;
                }
            });

        holder.mImageButtonView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(null!=mListener){
                    mListener.onListDeleteClick(position);
                }
            }
        });


    }




    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mItemImageView;
        public final TextView mContentView;
        public final ImageButton mImageButtonView;
        public Task mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mItemImageView =  view.findViewById(R.id.item_image);
            mContentView = view.findViewById(R.id.content);
            mImageButtonView = view.findViewById(R.id.imagebutton);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
