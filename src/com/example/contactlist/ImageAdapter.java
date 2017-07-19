package com.example.contactlist;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ImageAdapter extends SimpleCursorAdapter 
{

    private Cursor c;
    private Context context;

public ImageAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
    super(context, layout, c, from, to);
    this.c = c;
    this.context = context;
}

public View getView(int pos, View inView, ViewGroup parent) 
{
       View v = inView;
       if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.contact_activity_main, null);
       }
       this.c.moveToPosition(pos);      
       //String firstName = this.c.getString(this.c.getColumnIndex("firstName"));
       
       byte[] image = this.c.getBlob(this.c.getColumnIndex("personImage"));
       ImageView iv = (ImageView) v.findViewById(R.id.full_image_view);
       if (image != null) {
           // If there is no image in the database "NA" is stored instead of a blob 
           // test if there more than 3 chars "NA" + a terminating char if more than
           // there is an image otherwise load the default
           if(image.length > 3)
           {
               iv.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
           }
           else
           {
               iv.setImageResource(R.drawable.blank);
           }
       }
         //  TextView fname = (TextView) v.findViewById(R.id.label);
         //  fname.setText(firstName);

         
       return(v);
}}