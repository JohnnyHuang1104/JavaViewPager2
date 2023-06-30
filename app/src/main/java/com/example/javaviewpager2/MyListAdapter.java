package com.example.javaviewpager2;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends ListAdapter<MyPerson, MyListAdapter.MyViewHolder> {


    public MyListAdapter(@NonNull List<MyPerson> myList) {
        super(new DiffUtil.ItemCallback<MyPerson>() {
            @Override
            public boolean areItemsTheSame(@NonNull MyPerson oldItem, @NonNull MyPerson newItem) {
                return oldItem.name.equals(newItem.name) && oldItem.gender == newItem.gender
                        && oldItem.birthday == newItem.birthday;
            }

            @SuppressLint("DiffUtilEquals")
            @Override
            public boolean areContentsTheSame(@NonNull MyPerson oldItem, @NonNull MyPerson newItem) {
                return oldItem.equals(newItem);
            }
        });
        submitList(myList);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.page, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyPerson person = getItem(position);
        holder.nameTextView.setText(person.getName());
        holder.genderTextView.setText(String.valueOf(person.getGender()));
        holder.birthdayTextView.setText(String.valueOf(person.getBirthday()));
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView genderTextView;
        public TextView birthdayTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_textview);
            genderTextView = itemView.findViewById(R.id.gender_textview);
            birthdayTextView = itemView.findViewById(R.id.birthday_textview);
        }

    }

}

