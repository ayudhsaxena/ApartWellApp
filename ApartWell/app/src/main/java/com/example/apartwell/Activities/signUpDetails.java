package com.example.apartwell.Activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.apartwell.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link signUpDetails.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link signUpDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class signUpDetails extends Fragment {
    public static String username;
    public static String password;
    private OnFragmentInteractionListener mListener;

    public signUpDetails() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static signUpDetails newInstance() {
        signUpDetails fragment = new signUpDetails();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("hi","Inflate!!!");
        final View fragment = inflater.inflate(R.layout.fragment_sign_up_details, container, false);
        Button nextButton = fragment.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                SignUp activity = (SignUp)getActivity();
                EditText uname = fragment.findViewById(R.id.usernameEditText);
                EditText pwd = fragment.findViewById(R.id.passwordEditText);
                username = uname.getText().toString();
                password = pwd.getText().toString();


                activity.nextPage(1);
            }
        });

        return fragment;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
