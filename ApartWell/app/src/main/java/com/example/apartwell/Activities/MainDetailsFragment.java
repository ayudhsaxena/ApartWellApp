package com.example.apartwell.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.apartwell.R;
import com.example.apartwell.database.DatabaseHelper;
import com.example.apartwell.models.User;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainDetailsFragment extends Fragment {
    private static final String TAG = MainDetailsFragment.class.getSimpleName();
    public TextView empty;
    public static final String USERNAME_FIELD = "username";
    public static final String PASSWORD_FIELD = "password";
    public static final String FNAME_FIELD = "fname";
    public static final String LNAME_FIELD = "lname";
    public static final String EMAIL_FIELD = "email";
    public static final String CONTACT_FIELD = "contact";
    public static String fname;
    public static String lname;
    public static String email;
    public static String hall;
    public static String contact;

    //private CollectionReference collRef = FirebaseFirestore.getInstance().collection("users");

    private OnFragmentInteractionListener mListener;

    public MainDetailsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MainDetailsFragment newInstance() {
        MainDetailsFragment fragment = new MainDetailsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_main_details, container, false);


        Button back = v.findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SignUp activity = (SignUp)getActivity();
                activity.nextPage(0);
            }
        });

        final EditText f = v.findViewById(R.id.firstNameEditText);
        final EditText l = v.findViewById(R.id.lastNameEditText);
        final EditText c = v.findViewById(R.id.contactEditText);
        final EditText e = v.findViewById(R.id.emailEditText);
        empty = v.findViewById(R.id.empty_error);
        empty.setVisibility(View.INVISIBLE);

        Button done = v.findViewById(R.id.doneButton);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fname = f.getText().toString();
                lname = l.getText().toString();
                email = e.getText().toString();
                contact = c.getText().toString();
                if(fname.length() == 0 || lname.length() == 0 || email.length() == 0 || contact.length() == 0 || signUpDetails.password.length() == 0 || signUpDetails.username.length() == 0 )
                    empty.setVisibility(View.VISIBLE);
                else{
                    DatabaseHelper db = DatabaseHelper.getInstance(v.getContext());
                    User u = new User();
                    u.setUsername(signUpDetails.username);
                    u.setPassword(signUpDetails.password);
                    u.setFirstName(fname);
                    u.setSecondName(lname);
                    u.setEmail(email);
                    u.setMobileNo(contact);
                    db.addUser(u);

                    Intent intent = new Intent(v.getContext(),LoginActivity.class);
                    intent.putExtra("from","signUp");
                    startActivity(intent);
                }
            }
        });

        return v;


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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        empty.setVisibility(View.INVISIBLE);
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
