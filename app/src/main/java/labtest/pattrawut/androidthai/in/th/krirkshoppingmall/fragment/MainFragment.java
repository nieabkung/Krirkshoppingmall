package labtest.pattrawut.androidthai.in.th.krirkshoppingmall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import labtest.pattrawut.androidthai.in.th.krirkshoppingmall.R;

/**
 * Created by Pattrawut on 3/6/2018.
 */

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;

    }
}
