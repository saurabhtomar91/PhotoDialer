package com.example.contactlist;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.contactlist.db.ContactObject;
import com.example.contactlist.db.DBAdapter;

public class AddContact extends Activity implements OnClickListener
{

	EditText etNumber, etName;
	Button btnSave, btnCancel;
	ImageView ivPhoto;
	private final int GALLERY = 1;
	private final int CAMERA = 2;
	Bitmap mBitmap;
	public static final int THUMBNAIL_HEIGHT = 200;
	public static final int THUMBNAIL_WIDTH =400;
	DBAdapter db;
	String path = "";
	Context context; 
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_contact);
		db = new DBAdapter(getApplicationContext());
		etName = (EditText) findViewById(R.id.addcontact_edittex_contactt);
		etNumber = (EditText) findViewById(R.id.addcontact_edittext_number);
		btnCancel = (Button) findViewById(R.id.addcontact_button_cacel);
		btnSave = (Button) findViewById(R.id.addcontact_button_Save_);
		ivPhoto = (ImageView) findViewById(R.id.addcontact_imageview_contact);

		btnSave.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		ivPhoto.setOnClickListener(this);
	}

	
	
	
	@Override
	public void onClick(View v)
	{
		
		switch (v.getId()) {
			case R.id.addcontact_button_cacel:
				finish();
				break;
			case R.id.addcontact_button_Save_:
				if (db.getCount() < 20)
				{
					//if (etName.getText() != null && etNumber.getText() != null && mBitmap != null)
					{
						try
						{
							String name = etName.getText().toString();
							String number = etNumber.getText().toString();
							
							if (number.trim().equals("")) 
							{
							    Toast.makeText(this, getResources().getString(R.string.Contact_Number_cannot_Left_blank), Toast.LENGTH_SHORT).show();
							    return;
							}
							
							if (name.trim().equals("")) 
							{
							    Toast.makeText(this, getResources().getString(R.string.Contact_Name_cannot_Left_blank), Toast.LENGTH_SHORT).show();
							    return;
							}
							if (null==ivPhoto.getDrawable()) {
							    Toast.makeText(this, getResources().getString(R.string.photo_cannot_Left_blank), Toast.LENGTH_SHORT).show();
							    return;
							}
							
							ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
							ByteArrayOutputStream stream = new ByteArrayOutputStream();
							int rawContactID = ops.size();
							String jass = "jas";	
							ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null).withValue(RawContacts.ACCOUNT_NAME, null).build());
							ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID).withValue(ContactsContract.Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE).withValue(StructuredName.DISPLAY_NAME, etName.getText().toString()).build());
							ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID).withValue(ContactsContract.Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE).withValue(Phone.NUMBER, etNumber.getText().toString()).withValue(Phone.TYPE, CommonDataKinds.Phone.TYPE_MOBILE).build());
							ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID).withValue(ContactsContract.Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE).build());
							ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID).withValue(ContactsContract.Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE).withValue(StructuredName.DATA10, jass.toString()).build());
							mBitmap.compress(Bitmap.CompressFormat.PNG, 80, stream);
							ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID).withValue(ContactsContract.Data.IS_SUPER_PRIMARY, 1).withValue(ContactsContract.Data.MIMETYPE, Photo.CONTENT_ITEM_TYPE).withValue(ContactsContract.CommonDataKinds.Photo.PHOTO, stream.toByteArray()).build());
							stream.flush();
							getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
							//Toast.makeText(getBaseContext(), getResources().getString(R.string.Contactissuccessfullyadded), Toast.LENGTH_SHORT).show();
							ContactObject contact = new ContactObject();
							contact.setName(name);
							contact.setNumber(number);
							saveToInternalSorage(mBitmap);
							contact.setPath(path);
							
							String phonenumber = db.Exist(number);
							
							if(number.equals(phonenumber))
							{
								Toast.makeText(getApplicationContext(), getResources().getString(R.string.phonenumberexist), Toast.LENGTH_SHORT).show();
							}
							else
							{
								boolean check = db.insertRetailerDetails(contact);
								Toast.makeText(getBaseContext(), getResources().getString(R.string.Contactissuccessfullyadded), Toast.LENGTH_SHORT).show();
								finish();
							}
							
							
						} catch (Exception e)
						{
							e.printStackTrace();
						}

					}

//					else
//					{
//
//					}
				}
				break;
			case R.id.addcontact_imageview_contact:
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
		myAlertDialog.setPositiveButton(getResources().getString(R.string.Gallery), new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface arg0, int arg1)
			{
				Intent pictureActionIntent = new Intent(Intent.ACTION_GET_CONTENT, null);
				pictureActionIntent.setType("image/*");
				pictureActionIntent.putExtra("return-data", true);
				startActivityForResult(pictureActionIntent, GALLERY);
			}
		});

		     myAlertDialog.setNegativeButton(getResources().getString(R.string.Camera), 
				new DialogInterface.OnClickListener() {
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
			
			//Toast.makeText(getBaseContext(), "Contact is successfully addedinto database=" + path + CSV.getAbsolutePath(), Toast.LENGTH_SHORT).show();
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
					} catch (FileNotFoundException e)
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
						//mBitmap.setPixel(100, 100, 00);
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
						//mBitmap.createScaledBitmap(mBitmap, 150, 300, false);
						ivPhoto.setImageBitmap(mBitmap);
					} catch (Exception e)
					{
						e.printStackTrace();
					}

				}
		}
	}
}
