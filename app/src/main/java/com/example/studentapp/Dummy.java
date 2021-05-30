//package com.example.login_screen;
//
//import android.app.Activity;
//import android.app.ActivityOptions;
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.net.ConnectivityManager;
//import android.provider.Settings;
//import android.util.Pair;
//import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.*;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.arch.core.executor.TaskExecutor;
//import androidx.core.view.GravityCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.chaos.view.PinView;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.gms.tasks.TaskExecutors;
//import com.google.android.material.navigation.NavigationView;
//import com.google.android.material.textfield.TextInputLayout;
//import com.google.firebase.FirebaseException;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.PhoneAuthCredential;
//import com.google.firebase.auth.PhoneAuthOptions;
//import com.google.firebase.auth.PhoneAuthProvider;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.concurrent.Executor;
//import java.util.concurrent.TimeUnit;
//import java.util.logging.Handler;
//import java.util.logging.LogRecord;
//
//public class Dummy extends Activity implements NavigationView.OnNavigationItemSelectedListener {
//
//    ImageView backbtn;
//    Button nextBtn;
//    TextView Title;
//    RadioButton radioButton;
//    RadioButton selectedAge;
//    RadioGroup radioGroup;
//    PinView pinFromUser;
//    String _phone_No;
//    String getfName;
//    String getEmail;
//    String getHAddress;
//    String getIAddress;
//    String getPassword;
//    String getAge;
//    String getGender;
//    RelativeLayout progressBar;
//    CheckBox rememberMe;
//
//    DatePicker datePicker;
//    String codeBySystem;
//
//    Animation topAnim;
//    Animation bottomAnim;
//    ImageView image;
//    TextView logo, slogan;
//
//    DrawerLayout drawerLayout;
//    NavigationView navView;
//    androidx.appcompat.widget.Toolbar toolBar;
//    RecyclerView rv;
//
//    private static int SPASH_SCREEN = 5000;
//
//    TextInputLayout fullName, email, hAddress, iAddress, password;
//
//    ChildAdapterClass adapter;
//
//    public void callLOginScreen(View View) {
//
//        rv.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new ChildAdapterClass(dataQueue());
//        rv.setAdapter(adapter);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.navogation_drawer_open, R.string.navogation_drawer_closed);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//
//        logo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                logo.getContext().startActivity(new Intent(logo.getContext(), DashBoard.class));
//            }
//        });
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//        }
//
//        navView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
//
//
//        ProgressDialog mProgressDialog = new ProgressDialog(this);
//
//        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
//        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
//        image = findViewById(R.id.imageView);
//        logo = findViewById(R.id.textView1);
//        slogan = findViewById(R.id.textView2);
//
//        mProgressDialog.setMessage("Work ...");
//        mProgressDialog.show();
//        Intent intent = new Intent(getApplicationContext(), Login.class);
//        Pair[] pairs = new Pair[1];
//        pairs[0] = new Pair<android.view.View, String>(findViewById(R.id.letTheUserLogIn), "transition_Login");
//
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
//
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //Write whatever to want to do after delay specified (1 sec)
//
//            }
//        }, 1000);
//
//        final Runnable r = new Runnable() {
//            public void run() {
//                handler.postDelayed(this, 1000);
//            }
//        };
//
//        new android.os.Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(getApplicationContext(), Login.class);
//
//            }
//        }, SPASH_SCREEN);
//
//        if (rememberMe.isChecked()) {
//            SessionManager sessionManager = new SessionManager(Dummy.this);
//            HashMap<String, String> userDetails = sessionManager.getUserDetailFromSession();
//            String fullName = userDetails.get(SessionManager.KEY_NAME);
//
//        }
//
//
//    }
//
//    public ArrayList<ChildModel> dataQueue() {
//        ArrayList<ChildModel> holder = new ArrayList<>();
//        ChildModel cm = new ChildModel();
//        cm.setImg(R.drawable.profile);
//
//        cm.setHeader("  jjhj");
//        cm.setDesc("dded");
//        holder.add(cm);
//        return  holder;
//
//    }
//
//
//    public void callNextScreen(View view) {
//        if (!isConnected(this)) {
//            showCustomDialog();
//        }
//
//        if (!validateFields()) {
//            return;
//        }
//
//
//        progressBar.setVisibility(view.VISIBLE);
//        progressBar.setVisibility(view.GONE);
//
//        String _email = email.getEditText().getText().toString().trim();
//        String _password = password.getEditText().getText().toString().trim();
//
//        //DatabaseQuery
//        Query checkUser = FirebaseDatabase.getInstance().getReference("Students").orderByChild("phone_No")
//                .equalTo(_email);
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//
//                if (snapshot.exists()) {
//                    String systemPassword = snapshot.child("phone_No").getValue(String.class);
//                    email.setError(null);
//                    email.isErrorEnabled();
//                    password.isErrorEnabled();
//                    password.setError(null);
//                    ;
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//                // Toast.makeText(, error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//    }
//
//
////    private boolean isConnected(Dummy dummy) {
////        ConnectivityManager connectivityManager = dummy.getSystemService
////    }
//
//    private void showCustomDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(Dummy.this);
//        builder.setMessage("Please Connect to the Internet to Proceed Further!!!").setCancelable(false)
//                .setPositiveButton(" Connect", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//
//    }
//
//
//    private boolean validateFields() {
//        String _email = email.getEditText().getText().toString().trim();
//        String _password = password.getEditText().getText().toString().trim();
//        if (_email.isEmpty()) {
//            email.setError("Email can not be empty!");
//            email.requestFocus();
//            return false;
//        } else if (_password.isEmpty()) {
//            password.setError("Password can not be empty!");
//            password.requestFocus();
//            return false;
//        } else {
//            email.setError(null);
//            email.setErrorEnabled(false);
//            password.setError(null);
//            password.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//    private void sendVerificationCodeToUser(String phone_no) {
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//
//        PhoneAuthOptions options =
//                PhoneAuthOptions.newBuilder(firebaseAuth)
//                        .setPhoneNumber(phone_no)       // Phone number to verify
//                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//                        .setActivity((Activity) TaskExecutors.MAIN_THREAD)                 // Activity (for callback binding)
//                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
//                        .build();
//        PhoneAuthProvider.verifyPhoneNumber(options);
//
//    }
//
//    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new
//            PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                @Override
//                public void onCodeSent(@NonNull @NotNull String s, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                    super.onCodeSent(s, forceResendingToken);
//                    codeBySystem = s;
//                }
//
//                @Override
//                public void onVerificationCompleted(@NonNull @NotNull PhoneAuthCredential phoneAuthCredential) {
//                    String code = phoneAuthCredential.getSmsCode();
//                    if (code != null) {
//                        pinFromUser.setText(code);
//                        verfyCode(code);
//                    }
//                }
//
//                @Override
//                public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {
//                    Toast.makeText(Verify_OTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            };
//
//    private void verfyCode(String code) {
//        PhoneAuthCredential credentials = PhoneAuthProvider.getCredential(codeBySystem, code);
//        signInUsingCredentials(credentials);
//    }
//
//    private void signInUsingCredentials(PhoneAuthCredential credential) {
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//
//        firebaseAuth.signInWithCredential(credential)
//                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(Verify_OTP.this, "Verification Completed.", Toast.LENGTH_SHORT).show();
//                            storeNewUserData();
//
//                        } else {
//                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
//                                Toast.makeText(Verify_OTP.this, "Verification not Completed! Try Again.", Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    }
//                });
//    }
//
//    private void storeNewUserData() {
//        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
//        DatabaseReference reference = rootNode.getReference("Users");
//        DummyHelperClass addNewUser = new DummyHelperClass(getfName, getEmail, getHAddress, getIAddress, getPassword, getAge, getGender, _phone_No);
//        reference.child("Users").push().setValue(addNewUser);
//
//
//    }
//
//
//    private boolean validateGender() {
//        if (radioGroup.getCheckedRadioButtonId() == -1) {
//            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    private boolean validateAge() {
//        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
//        int userAge = datePicker.getYear();
//        int isAgeValid = currentYear - userAge;
//
//        radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
//        String gender = radioButton.getText().toString();
//
//        int day = datePicker.getDayOfMonth();
//        int month = datePicker.getMonth();
//        int year = datePicker.getYear();
//        String date = day + "/" + month + "/" + year;
//
//        if (isAgeValid < 14) {
//            Toast.makeText(this, "You are not eligible to apply", Toast.LENGTH_SHORT).show();
//            return false;
//        } else
//            return true;
//    }
//
//    private boolean validatePhoneNumber() {
//        String val = phoneNumber.getEditText().getText().toString().trim();
//        String checkspaces = "Aw{1,20}z";
//        if (val.isEmpty()) {
//            phoneNumber.setError("Enter valid phone number");
//            return false;
//        } else if (!val.matches(checkspaces)) {
//            phoneNumber.setError("No White spaces are allowed!");
//            return false;
//        } else {
//            phoneNumber.setError(null);
//            phoneNumber.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//    private boolean validateEmail() {
//
//        if (!validateFullName() | !validateUsername() | !validateEmail() | !validatePassword()) {
//            return;
//        }
//        String val = email.getEditText().getText().toString().trim();
//        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
//
//        if (val.isEmpty()) {
//            email.setError("Field can not be empty");
//            return false;
//        } else if (!val.matches(checkEmail)) {
//            email.setError("Invalid Email!");
//            return false;
//        } else {
//            email.setError(null);
//            email.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//    private boolean validateFullName() {
//        String val = fullName.getEditText().getText().toString().trim();
//        if (val.isEmpty()) {
//            fullName.setError("Field can not be empty");
//            return false;
//        } else {
//            fullName.setError(null);
//            fullName.setErrorEnabled(false);
//            return true;
//        }
//    }
//
//
//    //DatabaseQuery
//    val checkUser:Query =FirebaseDatabase.getInstance().
//
//    getReference("Students")
//        checkUser.addValueEventListener(object :
//
//    ValueEventListener {
//        override fun onDataChange(snapshot:DataSnapshot){
//            for (studentDS in snapshot.children) {
//                var obj = studentDS.getValue(StudentDBHelperClass:: class.java)
//                println(obj !!.password);
//                if (_phone.equals(obj !!.phone_No) &&_password == obj !!.password){
//                    _id++
//                    var uid = FirebaseAuth.getInstance().currentUser
//                    var userId = uid ?.getUid()
//
//                    println(uid)
//                    progressBar !!.visibility = View.VISIBLE
//                    phone_No !!.error = null
//                    phone_No !!.isErrorEnabled
//                    password !!.isErrorEnabled
//                    password !!.error = null
//                    var name:String ? = obj !!.fullName
//                    Toast.makeText(this @Login,"Welocome " + name, Toast.LENGTH_SHORT).show();
//                    break
//                }
//            }
//            if (_id != 1) {
//                progressBar !!.visibility = View.GONE
//                Toast.makeText(this @Login,"Wrong Credentials", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        override fun onCancelled(error:DatabaseError){
//            progressBar !!.visibility = View.GONE
//            Toast.makeText(this @Login,error.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    })
//
//
//}
