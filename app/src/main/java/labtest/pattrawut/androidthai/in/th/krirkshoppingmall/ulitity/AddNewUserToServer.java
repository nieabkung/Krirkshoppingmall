package labtest.pattrawut.androidthai.in.th.krirkshoppingmall.ulitity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.widget.ActionBarContextView;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by home on 3/27/2018.
 */

public class AddNewUserToServer extends AsyncTask<String,Void,String> {

    private Context context;

    public AddNewUserToServer(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd","true")
                    .add("Name",strings[0])
                    .add("User",strings[1])
                    .add("Password", strings[2])
                    .add("Mode", strings[3])
                    .build();

            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[4]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
