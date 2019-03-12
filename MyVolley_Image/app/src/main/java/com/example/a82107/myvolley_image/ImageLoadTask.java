package com.example.a82107.myvolley_image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
    private String urlStr;
    private ImageView imageView;

    //이미지가 버튼을 누를때마다 가져와지고 메모리에 저장되는데
    // 여러번 누르면 out of memory가 발생해 예외가 발생할 수 있음 그걸 처리하기위한 변수임
    private static HashMap<String, Bitmap> bitmapHash = new HashMap<String, Bitmap>();

    public ImageLoadTask(String urlStr, ImageView imageView){
        this.urlStr = urlStr;
        this.imageView = imageView;

    }
    @Override
    protected void onPreExecute() { //가장먼저실행
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {//2번쨰실행
        Bitmap bitmap = null;
        try {
            if(bitmapHash.containsKey(urlStr)){ //이미지를 불러오기전에 이미 불렀던 URL인지 확인함
                Bitmap oldBitmap = bitmapHash.remove(urlStr);
                if(oldBitmap != null){
                    oldBitmap.recycle(); //oldBitmap을 메모리에서 없에주는거임
                    oldBitmap = null; //큰 의미는없음 recycle만해줘도됨
                }
            }
            URL url = new URL(urlStr);
            //해당주소로 접속을 해서 스트림을 받는데 이게 이미지면 이미지 스트림 그대로 넘어오고 이것을
            //decodeStream이라고 해서 비트맵으로 바꿔주는 것이다.
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            bitmapHash.put(urlStr, bitmap); //hashMap에 넣어줌으로써 중복된건지 나중에 확인가능함.

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onProgressUpdate(Void... values) {//3번쨰실행
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {//마지막실행
        super.onPostExecute(bitmap);

        imageView.setImageBitmap(bitmap); //해당비트맵을 이미지뷰에 세팅함
        imageView.invalidate(); //다시 그려주는 역할을 함(일반적으로 UI객체는 invalidate를 직접 해줄필요는없음)
    }




}
