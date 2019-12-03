package com.example.jose.novaprimeserver.Common;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.example.jose.novaprimeserver.Model.Request;
import com.example.jose.novaprimeserver.Model.User;
import com.example.jose.novaprimeserver.Remote.IGeoCoordinates;
import com.example.jose.novaprimeserver.Remote.RetrofitClient;

public class Common {

    public static final String UPDATE = "update" ;
    public static final String DELETE = "delete" ;
    public static final int PICK_IMAGE_REQUEST = 71 ;

    public static final String baseURL = "https://maps.googleapis.com";


    public static User currentUser;
    public static Request currentRequest;

    public static String convertToStatus(String status) {
        if(status.equals("0"))
            return "Placed";
        else if(status.equals("1"))
            return "On my way";
        else
            return "shipped";
    }

    public static IGeoCoordinates getGeoCodeService(){
        return RetrofitClient.getClient(baseURL).create(IGeoCoordinates.class);
    }

    public  static Bitmap scaleBitmap(Bitmap bitmap, int newWidth, int newHeight){
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

        float scaleX = newWidth / (float)bitmap.getWidth();
        float scaleY = newHeight / (float)bitmap.getHeight();
        float pivotX=0;
        float pivotY=0;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scaleX, scaleY, pivotX, pivotY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, 0,0,new Paint(Paint.FILTER_BITMAP_FLAG));
        return scaledBitmap;
    }
}
