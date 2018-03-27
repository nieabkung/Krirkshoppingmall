package labtest.pattrawut.androidthai.in.th.krirkshoppingmall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import labtest.pattrawut.androidthai.in.th.krirkshoppingmall.MainActivity;
import labtest.pattrawut.androidthai.in.th.krirkshoppingmall.R;
import labtest.pattrawut.androidthai.in.th.krirkshoppingmall.ServiceActivity;
import labtest.pattrawut.androidthai.in.th.krirkshoppingmall.ulitity.AddNewUserToServer;
import labtest.pattrawut.androidthai.in.th.krirkshoppingmall.ulitity.GetAlldataByURL;
import labtest.pattrawut.androidthai.in.th.krirkshoppingmall.ulitity.MyAlert;
import labtest.pattrawut.androidthai.in.th.krirkshoppingmall.ulitity.MyConstant;

/**
 * Created by Pattrawut on 3/6/2018.
 */

public class RegisterFragment extends Fragment {

//    Explicit

    private String nameString, UserString, PasswordString, modeString;
    private boolean aBoolean = true;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create toolbar
        createToolbar();

//        Radio Controller
        radioController();
    }

    private void loginController() {
        Button button = getView().findViewById(R.id.btnlogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText userEdittext = getView().findViewById(R.id.edtUser);
                EditText passwordEdittext = getView().findViewById(R.id.edtPassWord);

                String userString = userEdittext.getText().toString().trim();
                String passwordString = passwordEdittext.getText().toString().trim();

                if (userString.isEmpty() || passwordString.isEmpty()) {

                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog(getString(R.string.title_have_space),
                            getString(R.string.message_have_space));

                } else {

                    try {

                        MyConstant myConstant = new MyConstant();
                        GetAlldataByURL getAlldataByURL = new GetAlldataByURL(getActivity());

                        String jsonString = getAlldataByURL.get();

                        Log.d("27March", "Json ==>" + jsonString);

                        JSONArray jsonArray = new JSONArray(jsonString);

                        String[] columnString = myConstant.getColumnUser();
                        String[] loginString = new String[columnString.length];

                        for (int i=0; i<jsonArray.length(); i+=1) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (userString.equals(jsonObject.getJSONObject(columnString[2]))) {

                                aBoolean = false;
                                for (int i1 = 0; i1 < columnString.length; i1 += 1)

                                    loginString[i1] = jsonObject.getString(columnString[i1]);


                            }

                        }

                        if (aBoolean) {

                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.myDialog("User false", "No this user in my database");

                        } else if (passwordString.equals(loginString[3])) {

                            Toast.makeText(getActivity(),"Welcome" + loginString[1],Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getActivity(), ServiceActivity.class);
                            intent.putExtra("Login", loginString);
                            startActivity(intent);
                            getActivity().finish();

                        } else{

                            MyAlert myAlert = new MyAlert(getActivity());
                            myAlert.myDialog("Password false", "Please Try Again Passworf false");

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });

    }


 // Main Method




    private void radioController() {
        RadioGroup radioGroup = getView().findViewById(R.id.ragMode);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                aBoolean = false;

                switch (i) {
                    case R.id.ragOwner:
                        modeString = "OwnerShop";
                        break;
                    case R.id.ragCustomer:
                        modeString = "Customer";
                        break;

                }

            }
        });
    }

        public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() ==  R.id.itemUpload) {
            uploadToserver();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void uploadToserver() {

//        get Value from Edittext

        EditText NameeditText = getView().findViewById(R.id.edtName);
        EditText UsereditText = getView().findViewById(R.id.edtUser);
        EditText PassWordeditText = getView().findViewById(R.id.edtPassWord);

        nameString = NameeditText.getText().toString().trim();
        UserString = UsereditText.getText().toString().trim();
        PasswordString = PassWordeditText.getText().toString().trim();

//        Check Space

        if (nameString.isEmpty() || UserString.isEmpty() || PasswordString.isEmpty()) {
//            Have Space

            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.myDialog(getString(R.string.title_have_space),
                    getString(R.string.message_have_space));

        } else if (aBoolean) {
//            Non Choose Mode
            MyAlert myAlert = new MyAlert(getActivity());
            myAlert.myDialog("Non Choose Mode", "Please Choose Mode");

        } else {
//            Chooes Mode OK

            try {

                MyConstant myConstant = new MyConstant();
                AddNewUserToServer addNewUserToServer = new AddNewUserToServer(getActivity());
                addNewUserToServer.execute(nameString,UserString,PasswordString,
                        modeString, myConstant.getUrlAddUserString());

                String result = addNewUserToServer.get();
                if (Boolean.parseBoolean(result)) {
                    getActivity().getSupportFragmentManager().popBackStack();
                } else {

                    Toast.makeText(getActivity(),"Press try Aging Cannot Add User", Toast.LENGTH_LONG).show();
                }



            } catch (Exception e) {
                e.printStackTrace();
            }



        } // if

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_register, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void createToolbar() {

        setHasOptionsMenu(true);

        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.register));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.message_have_space));

        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }

        });

}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;

    }

}
