package com.example.contactlist;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;


public class ContanctMainActivity extends Activity 
{
 
    SimpleCursorAdapter mAdapter;
	MatrixCursor mMatrixCursor;
	GridView list;
	String mobilePhone=null;
	String name=null;
	Cursor mycursor;
	Button dialers;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_activity_main);
 
        mMatrixCursor = new MatrixCursor(new String[] { "_id","name","photo","details"} );
        
        /** Getting a reference to gridview of the MainActivity layout */
      //  GridView gridView = (GridView) findViewById(R.id.gridview);
 
        /** Create an adapter for the gridview */
        /** This adapter defines the data and the layout for the grid view */
        mAdapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.lv_layout,
                null,
                new String[] { "name","photo","details"},
                new int[] { R.id.tv_name,R.id.iv_photo,R.id.tv_details}, 0);
        
        list = (GridView) findViewById(R.id.gridview);
        list.setAdapter(mAdapter);
        ListViewContactsLoader listViewContactsLoader = new ListViewContactsLoader();
        listViewContactsLoader.execute();
        /** Setting adapter for the gridview */
         
        list.setOnItemClickListener(new OnItemClickListener() {
        
        	public void onItemClick(AdapterView<?> parent, View v, int position, long id)
        	{
        			Toast.makeText(getApplicationContext(), "Image"+(position+1),Toast.LENGTH_SHORT).show();
        			Log.v("Key ", "photoPath main activity click.......... " + id);
        		    
        			byte[] photoByte=null;
        		    String photoPath="";
        		    String jas="";
                  
                 
                    File cacheDirectory = getBaseContext().getCacheDir();
                    File tmpFile = new File(cacheDirectory.getPath() + "/wpta_"+id+".png");
                    Log.v("Key ", "tmpFile name click.......... " + tmpFile);
                   
                    photoPath = tmpFile.getPath();
                    Log.v("Key ", "photoPath.......... " + photoPath);
                
                    try 
                    {
                       mycursor = (Cursor) list.getItemAtPosition(position);
                       String mobilePhone = mycursor.getString(3);
                    } 
                    catch (Exception e) 
                    {
                        e.printStackTrace();
                    }
                    name = mycursor.getString(1);
                    mobilePhone = mycursor.getString(3);
                    jas = mycursor.getString(4);
                    
                    Log.v("Key ", "PHONE NUMBER........... " + mobilePhone);
                    Log.v("Key ", "jas........... " + jas);
                 
	                Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
	                Bundle bundle = new Bundle();
	                bundle.putInt("image", position);
	                bundle.putLong("id", id);
	                bundle.putString("photoPath", photoPath);
	                bundle.putString("mobilePhone", mobilePhone);
	                bundle.putString("name", name);
	                i.putExtras(bundle);
	                startActivityForResult(i, 0);
                
                
            }
        });
        
        
        //==================================
    /*    dialers=(Button)this.findViewById(R.id.button1);
        dialers.setText("Add New Contact");
        dialers.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View view) 
          {try{
        	  Intent i = new Intent(MainActivity.this, PhotoActivity.class);
        	  startActivity(i);
          }
          catch(Exception e){
        	  Log.v("key","================="+e);
          }
          }

         });*/
        
        //===================================
        
    }
    
  
    
    public class ListViewContactsLoader extends AsyncTask<Void, Void, Cursor>{
    	 
        @Override
        protected Cursor doInBackground(Void... params) 
        {
            Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;
 
            // Querying the table ContactsContract.Contacts to retrieve all the contacts
            
            Cursor contactsCursor = getContentResolver().query(contactsUri, null, null, null,
            ContactsContract.Contacts.DISPLAY_NAME + " ASC ");
 
            if(contactsCursor.moveToFirst()){
                do{
                    long contactId = contactsCursor.getLong(contactsCursor.getColumnIndex("_ID"));
                    Log.v("Key ", "contactId name.......... " + contactId);
                    Uri dataUri = ContactsContract.Data.CONTENT_URI;
 
                    // Querying the table ContactsContract.Data to retrieve individual items like
                    // home phone, mobile phone, work email etc corresponding to each contact
                    Cursor dataCursor = getContentResolver().query(dataUri, null,
                                        ContactsContract.Data.CONTACT_ID + "=" + contactId,
                                        null, null);
 
                    String displayName="";
                    String mobilePhone="";
                    String photoPath="" + R.drawable.blank;
                    byte[] photoByte=null;
                    String title="";
                    String jas="";
 
                    if(dataCursor.moveToFirst())
                    {
                        //    Getting Display Name
                        displayName = dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME ));
                        Log.v("Key ", "Display name.......... " + displayName);
                        
//                        if(diplayName.charAt(0)=='j')
//                        {
//                        	
//                        }
                        jas = dataCursor.getString(dataCursor.getColumnIndex("DATA10"));
                        Log.v("Key ", "jasssssssssssssss.......... " + jas);
                        		
                        do
                        {
                            // Getting Phone numbers
                            if(dataCursor.getString(dataCursor.getColumnIndex("mimetype")).equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE))
                            {
                                switch(dataCursor.getInt(dataCursor.getColumnIndex("data2")))
                                {
                                    case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE :
                                        mobilePhone = dataCursor.getString(dataCursor.getColumnIndex("data1"));
                                        break;
                                }
                                Log.v("Key ", "mobilePhone name.......... " + mobilePhone);
                            }
                            
                            
                         
                            
                            
                            // Getting Photo
                            if(dataCursor.getString(dataCursor.getColumnIndex("mimetype")).equals(ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE))
                            {
                                photoByte = dataCursor.getBlob(dataCursor.getColumnIndex("data15"));
 
                                if(photoByte != null) {
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(photoByte, 0, photoByte.length);
 
                                    // Getting Caching directory
                                    File cacheDirectory = getBaseContext().getCacheDir();
 
                                    // Temporary file to store the contact image
                                    File tmpFile = new File(cacheDirectory.getPath() + "/wpta_"+contactId+".png");
                                   
                                    Log.v("Key ", "tmpFile name.......... " + tmpFile);
                                    // The FileOutputStream to the temporary file
                                    try {
                                        FileOutputStream fOutStream = new FileOutputStream(tmpFile);
                                        // Writing the bitmap to the temporary file as png file
                                        bitmap.compress(Bitmap.CompressFormat.PNG,100, fOutStream);
                                        // Flush the FileOutputStream
                                        fOutStream.flush();
                                        //Close the FileOutputStream
                                        fOutStream.close();
 
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    photoPath = tmpFile.getPath();
                                  
                                    
                                    Log.v("Key ", "photoPath.......... " + photoPath);
                                }
                            }
                        }while(dataCursor.moveToNext());
                        String details = "";
 
                        // Concatenating various information to single string
                        if(mobilePhone != null && !mobilePhone.equals("") )
                            details += "MobilePhone : " + mobilePhone + "\n";
                        if(title != null && !title.equals("") )
                            details += "Title : " + title + "\n";
 
                        // Adding id, display name, path to photo and other details to cursor
                        mMatrixCursor.addRow(new Object[]{ Long.toString(contactId),displayName,photoPath,details});
                        
                       
                        
                    }
                }while(contactsCursor.moveToNext());
            }
            return mMatrixCursor;
        }
 
        @Override
        protected void onPostExecute(Cursor result) {
            // Setting the cursor containing contacts to listview
            mAdapter.swapCursor(result);
        }
    }
	
    
 
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    
    public void onbackpressed() {
		finish();
	}
	
 
   
}
