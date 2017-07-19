package com.example.contactlist;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.AlteredCharSequence;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;


import com.example.contactlist.db.ContactObject;
import com.example.contactlist.db.DBAdapter;
import com.example.contactlist.utils.Utils;

public class MyFragment extends Fragment
{

	int mCurrentPage;
	DBAdapter db;
	ImageView rl;
	Button btnCall;
	TextView tvName;
	Context context;
	TextView txtnumber;
	Button delete;
	Button edit;
	Activity activity;
	
	String path = "";
	String name = "";
	String number="";
	
	ArrayList<ContactObject> contctList = new ArrayList<ContactObject>();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		context = getActivity().getApplicationContext();
	
		Bundle data = getArguments();
		db = new DBAdapter(getActivity().getApplicationContext());
		contctList = db.getListOfContact();
		Log.d("ok....................................", "Size is " + contctList.size());
		mCurrentPage = data.getInt("current_page", 0);
		Log.e("ok", "current pahe is " + mCurrentPage);
			
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		
		View v = inflater.inflate(R.layout.myfragment_layout, container, false);
		rl = (ImageView) v.findViewById(R.id.showimage_imagevie_im);
		
		btnCall = (Button) v.findViewById(R.id.showcontact_button_call);
		tvName = (TextView) v.findViewById(R.id.showcontact_textview_name);
		txtnumber = (TextView) v.findViewById(R.id.showcontact_textview_name_ring);
		delete = (Button) v.findViewById(R.id.btn_delete);
		edit=(Button)v.findViewById(R.id.btn_edit);
		String path = contctList.get(mCurrentPage - 1).getPath();
		
		String name = contctList.get(mCurrentPage - 1).getName();
		String Number = contctList.get(mCurrentPage - 1).getNumber();
		
		//Bitmap bitmap = BitmapFactory.decodeFile(path);
		//////////////////////
		 //ImageView img=(ImageView)findViewById(R.id.ImageView01);
		try{
		Bitmap bitmaps = BitmapFactory.decodeFile(path);
		int width=800;
		int height=800;
		Bitmap resizedbitmap=Bitmap.createScaledBitmap(bitmaps, width, height, true);
		rl.setImageBitmap(resizedbitmap);
		}catch(Exception e)
		{
			Log.e("Myfragment", "Error"+e);
		}
		///////////////////////
		tvName.setText(name);
		txtnumber.setText(Number);
		
		//rl.setImageBitmap(bitmap);
			
		callListener();
		startDialog();
		editList();
		return v;
		
	}

	private void OnLaudSpeaker()
	{
		AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		audioManager.setMode(AudioManager.MODE_IN_CALL);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,20,0);
        audioManager.setSpeakerphoneOn(true);
	}
	private void finsih()
	{}

	private void callListener()
	{
		btnCall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				String mobileNumber = contctList.get(mCurrentPage - 1).getNumber();
				Uri numberuri = Uri.parse("tel:" + mobileNumber);
				
				Intent intent_call = new Intent(Intent.ACTION_CALL, numberuri);
				
				Utils.setMobileNumber(context, contctList.get(mCurrentPage - 1).getNumber());
				Utils.setName(context, contctList.get(mCurrentPage - 1).getName());
				Utils.setURi(context, contctList.get(mCurrentPage - 1).getPath());
				
				AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
				audioManager.setMode(AudioManager.MODE_IN_CALL);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,20,0);
                audioManager.setSpeakerphoneOn(true);
                
				startActivity(intent_call);

			}
		});
	}
	
	private void deleteList()
	{
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0)
			{
				 String Number = contctList.get(mCurrentPage - 1).getNumber();
				 Log.v("Key", "Show the Number.........  " +  Number);
				 db.deleteContact(Number);
				 getActivity().finish();
				 
			}
		});
	}
	
	
	public void editList()
	{
		edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0)
			{
				String path = contctList.get(mCurrentPage - 1).getPath();
				String name = contctList.get(mCurrentPage - 1).getName();
				String Number = contctList.get(mCurrentPage - 1).getNumber();
				String rowid  = contctList.get(mCurrentPage - 1).getRowId();
				Log.v("Key", "Show the path.........  " +  path);
				Log.v("Key", "Show the name.........  " +  name);
				Log.v("Key", "Show the Number.........  " +  Number);
				Log.v("Key", "Show the Number.........  " +  rowid);
				
				
				Intent i = new Intent(context, EditContact.class);
                Bundle bundle = new Bundle();
                bundle.putString("path", path);
                bundle.putString("name", name);
                bundle.putString("Number", Number);
                bundle.putString("rowid", rowid);
               
                i.putExtras(bundle);
                startActivity(i);
				 
			}
		});
	}
	
	private void startDialog()
	{
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(getActivity());
				myAlertDialog.setTitle(getResources().getString(R.string.Delete_Contact));
				myAlertDialog.setMessage(getResources().getString(R.string.Do_you_want_to_delete_this_contact));
				myAlertDialog.setPositiveButton(getResources().getString(R.string.Yes), new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface arg0, int arg1)
					{
						String Number = contctList.get(mCurrentPage - 1).getNumber();
						 Log.v("Key", "Show the Number.........  " +  Number);
						 db.deleteContact(Number);
						 getActivity().finish();
					}
				
				});
				     myAlertDialog.setNegativeButton(getResources().getString(R.string.No), 
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1)
					{}
				});
				myAlertDialog.show();
				
			}
		});
		
	}
	
}
