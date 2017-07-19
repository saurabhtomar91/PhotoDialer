package com.example.contactlist;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import com.example.contactlist.db.ContactObject;
import com.example.contactlist.db.DBAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class EditContact extends Activity implements OnClickListener
{

	MyFragment fragment = new MyFragment();
	EditText etNumber, etName;
	Button btnSave, btnCancel;
	ImageView ivPhoto;
	private final int GALLERY = 1;
	private final int CAMERA = 2;
	Bitmap mBitmap;
	public static final int THUMBNAIL_HEIGHT =200;
	public static final int THUMBNAIL_WIDTH = 400;
	DBAdapter db;
	int mCurrentPage;
	String path="";
	String Number;
	String name;
	String rowid;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		db = new DBAdapter(getApplicationContext());
		etName = (EditText) findViewById(R.id.editcontact_edittex_contactt);
		etNumber = (EditText) findViewById(R.id.editcontact_edittext_number);
		btnCancel = (Button) findViewById(R.id.editcontact_button_cacel);
		btnSave = (Button) findViewById(R.id.editcontact_button_Save_);
		ivPhoto = (ImageView) findViewById(R.id.editcontact_imageview_contact);
		
		btnSave.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		ivPhoto.setOnClickListener(this);
		
		Bundle bundle = this.getIntent().getExtras();
		path = bundle.getString("path");
		Number = bundle.getString("Number");
	    name = bundle.getString("name");
	    rowid = bundle.getString("rowid");
	    
		etName.setText(name);
		etNumber.setText(Number);

		FileInputStream fd = null;
        try
        {
	        fd = new FileInputStream(path);
        } catch (FileNotFoundException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		 try
        {
	        mBitmap = BitmapFactory.decodeFileDescriptor(fd.getFD());
        } catch (IOException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		Float width = new Float(mBitmap.getWidth());
		Float height = new Float(mBitmap.getHeight());
		Float ratio = width / height;
		mBitmap = Bitmap.createScaledBitmap(mBitmap, (int) (THUMBNAIL_HEIGHT * ratio), THUMBNAIL_HEIGHT, false);

		int padding = (THUMBNAIL_WIDTH - mBitmap.getWidth()) / 2;
		ivPhoto.setImageBitmap(mBitmap);
		
		//ivPhoto.setImageURI(Uri.parse(path));
	}

	@Override
	public void onClick(View v)
	{

		switch (v.getId()) {
			case R.id.editcontact_button_cacel:
				finish();
				break;
			case R.id.editcontact_button_Save_:
				if (db.getCount() < 20)
				{
					
						try
						{
							String names = etName.getText().toString();
							String numbers = etNumber.getText().toString();

							if (numbers.trim().equals(""))
							{
								Toast.makeText(this, getResources().getString(R.string.Contact_Number_cannot_Left_blank), Toast.LENGTH_SHORT).show();
								return;
							}

							if (names.trim().equals(""))
							{
								Toast.makeText(this, getResources().getString(R.string.Contact_Name_cannot_Left_blank), Toast.LENGTH_SHORT).show();
								return;
							}

							ContactObject contact = new ContactObject();
							contact.setName(names);
							contact.setNumber(numbers);
							contact.setRowId(rowid);
							saveToInternalSorage(mBitmap);
							contact.setPath(path);
							db.updateContact(contact);
							Intent i = new Intent(getApplicationContext(), MainActivity.class);
							startActivity(i);

						} catch (Exception e)
						{
							e.printStackTrace();
						}
						
						finish();

				}
				break;
			case R.id.editcontact_imageview_contact:
				startDialog();
				break;

			default:
				break;
		}
	}

	

	private void startDialog()
	{
		AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);

		myAlertDialog.setTitle(getResources().getString(R.string.UploadPicturesOption));
		myAlertDialog.setMessage(getResources().getString(R.string.Howdoyouwanttosetyourpicture));
		myAlertDialog.setPositiveButton(getResources().getString(R.string.Gallery), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1)
			{
				Intent pictureActionIntent = new Intent(Intent.ACTION_GET_CONTENT, null);
				pictureActionIntent.setType("image/*");
				pictureActionIntent.putExtra("return-data", true);
				startActivityForResult(pictureActionIntent, GALLERY);
			}
		});

		myAlertDialog.setNegativeButton(getResources().getString(R.string.Camera), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1)
			{
				Intent pictureActionIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(pictureActionIntent, CAMERA);

			}
		});
		myAlertDialog.show();
	}

	private String saveToInternalSorage(Bitmap bitmapImage)
	{
		try
		{
			File sdcard = Environment.getExternalStorageDirectory();
			File direct = new File(sdcard + "/PhotoDialer");
			Date currentDate = new Date();
			long lCurrentDate = currentDate.getTime();
			if (!direct.exists())
			{
				direct.mkdir();
			}
			File CSV = new File(sdcard + "/PhotoDialer/image" + lCurrentDate + ".png");
			if (!CSV.exists())
			{
				CSV.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(CSV);
			bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
			path = CSV.getAbsolutePath();
			Log.e("Path....", path + CSV.getAbsolutePath());
			Log.e("Path....", path + CSV.getAbsolutePath());
			Log.e("Path....", path + CSV.getAbsolutePath());
			Log.e("Path....", path + CSV.getAbsolutePath());

			// Toast.makeText(getBaseContext(),
			// "Contact is successfully addedinto database=" + path +
			// CSV.getAbsolutePath(), Toast.LENGTH_SHORT).show();
			fos.close();
			return path;
		} catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case GALLERY:
				if (resultCode == RESULT_OK)
				{
					// Getting the uri of the picked photo
					Uri selectedImage = data.getData();
					InputStream imageStream = null;
					try
					{
						// Getting InputStream of the selected image
						imageStream = getContentResolver().openInputStream(selectedImage);
					} 
					catch (FileNotFoundException e)
					{
						e.printStackTrace();
					}
					try
					{
						mBitmap = BitmapFactory.decodeStream(imageStream);
						Float width = new Float(mBitmap.getWidth());
						Float height = new Float(mBitmap.getHeight());
						Float ratio = width / height;
						mBitmap = Bitmap.createScaledBitmap(mBitmap, (int) (THUMBNAIL_HEIGHT * ratio), THUMBNAIL_HEIGHT, false);

						int padding = (THUMBNAIL_WIDTH - mBitmap.getWidth()) / 2;
						// ImageButton ibPhoto = (ImageButton)
						// findViewById(R.id.ib_photo);

						ivPhoto.setImageBitmap(mBitmap);

					} catch (StackOverflowError e)
					{
						e.printStackTrace();
					}

				}
				break;
			case CAMERA:
				if (resultCode == RESULT_OK)
				{
					try
					{
						mBitmap = (Bitmap) data.getExtras().get("data");
						ivPhoto.setImageBitmap(mBitmap);
					} catch (Exception e)
					{
						e.printStackTrace();
					}

				}
		}
	}
}
