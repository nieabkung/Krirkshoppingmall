package labtest.pattrawut.androidthai.in.th.krirkshoppingmall.ulitity;

/**
 * Created by home on 3/27/2018.
 */

public class MyConstant {

//    about url

    private String urlAddUserString =  "http://androidthai.in.th/kir/addDataUng.php";
    private String urlGetAllUserString = "http://androidthai.in.th/kir/addDataUng.php";

//    about array

    private String[] columnUser = new String[]{"id", "Name", "User", "Password", "Mode"};


    public String[] getColumnUser() {



        return columnUser;
    }

    public String getUrlGetAllUserString() {



        return urlGetAllUserString;
    }

    public String getUrlAddUserString() {



        return urlAddUserString;
    }
} // main class
