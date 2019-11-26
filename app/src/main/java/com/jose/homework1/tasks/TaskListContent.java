package com.jose.homework1.tasks;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class TaskListContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Task> ITEMS = new ArrayList<Task>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Task> ITEM_MAP = new HashMap<String, Task>();

    public static void addItem(Task item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.name, item);
    }

    public static void removeItem(int position) {
        String itemId = ITEMS.get(position).name;
        ITEMS.remove(position);
        ITEM_MAP.remove(itemId);
    }


    public static class Task implements Parcelable {
        public final String name;
        public final String username;
        public final String surname;
        public final String sound;
        public final String bday;
        public final String telephone;
        public int n;

        public Task(String id, String name, String surname, String sound, String bday, String telephone) {
            this.name = id;
            this.username = name;
            this.surname = surname;
            this.sound = sound;
            this.bday = bday;
            this.telephone = telephone;
            Random rand = new Random();
            this.n = rand.nextInt(6);
            this.n+=1;
        }

        public Task(String id, String name, String surname, String sound) {
            this.name = id;
            this.username = name;
            this.surname = surname;
            this.sound = sound;
            this.bday = "Not specified";
            this.telephone = "Not specified";
            Random rand = new Random();
            this.n = rand.nextInt(6);
            this.n+=1;
        }

        public Task(String id, String name, String surname) {
            this.name = id;
            this.username = name;
            this.surname = surname;
            this.sound = "Not specified";
            this.bday = "Not specified";
            this.telephone = "Not specified";
            Random rand = new Random();
            this.n = rand.nextInt(6);
            this.n+=1;
        }

        protected Task(Parcel in) {
            name = in.readString();
            username = in.readString();
            surname = in.readString();
            sound = in.readString();
            bday = in.readString();
            telephone = in.readString();
            n = in.readInt();
        }

        public static final Creator<Task> CREATOR = new Creator<Task>() {
            @Override
            public Task createFromParcel(Parcel in) {
                return new Task(in);
            }

            @Override
            public Task[] newArray(int size) {
                return new Task[size];
            }
        };

        public int getN(){
            return this.n;
        }


        @Override
        public String toString() {
            return username;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name);
            parcel.writeString(username);
            parcel.writeString(surname);
            parcel.writeString(sound);
            parcel.writeString(bday);
            parcel.writeString(telephone);
            parcel.writeInt(n);
        }
    }
}
