package com.example.fivemealsmobileproject.account;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fivemealsmobileproject.R;
import com.example.fivemealsmobileproject.database.AppDataBase;
import com.example.fivemealsmobileproject.database.User;
import com.example.fivemealsmobileproject.login.LoginManager;
import com.example.fivemealsmobileproject.login.SessionManager;


public class ChangePasswordFragment extends Fragment {



    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView submit = view.findViewById(R.id.textViewChangePasswordSubmit);
        EditText oldPassword = view.findViewById(R.id.editTextChangePasswordOldPassword);
        EditText newPassword = view.findViewById(R.id.editTextChangePasswordNewPassword);
        EditText newPasswordConfirmation = view.findViewById(R.id.editTextChangePasswordTypeAgain);
        Context context = view.getContext();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int newPasswordData = newPassword.getText().toString().hashCode();
                int oldPasswordData = oldPassword.getText().toString().hashCode();
                int newPasswordConfirmationData = newPasswordConfirmation.getText().toString().hashCode();

                String activeSession = SessionManager.getActiveSession(context);
                User user = AppDataBase.getInstance(context).getUserDAO().getUserByUsername(activeSession);

                if (oldPasswordData == user.getPassword()){
                    if(oldPasswordData == newPasswordData){
                        newPassword.setError("New Password can't be equal to the old password");

                    }else if(newPasswordData == newPasswordConfirmationData){
                        user.setPassword(newPasswordData);
                        AppDataBase.getInstance(context).getUserDAO().update(user);

                        NavDirections action = (NavDirections) ChangePasswordFragmentDirections.actionChangePasswordFragment2ToAccountFragment();
                        Navigation.findNavController(view).navigate(action);
                    }else{
                        newPasswordConfirmation.setError("Password are different");
                    }
                }else{
                    oldPassword.setError("Password is Wrong");
                }
            }
        });
    }
}