package com.Shuvo.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.Shuvo.myapplication.Class.RequestHandler;
import com.google.firebase.auth.FirebaseAuth;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class PostImageUploadActivity extends AppCompatActivity {


    private String intent_item_id;
    private ImageView pp;
    private Button buttonUpload, select_picture;
    private Bitmap bitmap;
    private Uri filePath;
    private String uid = "",url,imageName,cuurentUser;
    FirebaseAuth auth;
    int id;
    String TAG="PostImageUploadActivity";
    private Bitmap originalImage;

    public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix;
        matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);
        // RECREATE THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_image_upload);


        url=getIntent().getStringExtra("url");
        imageName=getIntent().getStringExtra("name");
        id=getIntent().getExtras().getInt("id",0);
        auth=FirebaseAuth.getInstance();
        cuurentUser=auth.getCurrentUser().getUid();


        Log.d(TAG, "onCreate: " + url);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            intent_item_id = "";
        } else {
            intent_item_id = extras.getString("item_id");
            //Toast.makeText(this, intent_item_id, Toast.LENGTH_SHORT).show();
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ////////////////////////////////////////////////////////////////////////////////////////////
        buttonUpload = (Button) findViewById(R.id.post_submit);
        pp = (ImageView) findViewById(R.id.post_image);
        select_picture = (Button) findViewById(R.id.post_select_picture);
        ////////////////////////////////////////////////////////////////////////////////////////////

        select_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 2);
            }
        });

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPic();
            }
        });


    }
    public void uploadPic() {
        class UploadImage extends AsyncTask<Bitmap, Void, String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();

                if (s.contains("success")) {
                    Toast.makeText(getApplicationContext(), "Picture Uploaded Successfully", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String, String> data = new HashMap<>();
                data.put("pp",uploadImage);
                data.put("id", String.valueOf(id));
                String result = rh.sendPostRequest(url, data);
                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(originalImage);
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 2:
                if (resultCode == RESULT_OK) {
                    filePath = data.getData();

                    if (filePath != null) {
                        bitmap = decodeUri(filePath, 100);
                        originalImage = getResizedBitmap(bitmap, 1000, 700);
                        pp.setVisibility(View.VISIBLE);
                        pp.setImageBitmap(bitmap);
                    }
                    //super.onActivityResult(requestCode, resultCode, data);
                }
                break;

            default:
                break;
        }
    }

    protected Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getBaseContext().getContentResolver().openInputStream(selectedImage), null, o);
            // The new size we want to scale to
            // final int REQUIRED_SIZE =  size;
            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }
            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(getBaseContext().getContentResolver().openInputStream(selectedImage), null, o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}