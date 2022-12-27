package com.example.runway_project;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;

public class TestOut {
    private Database db;
    private DatabaseReference dbU;

    public TestOut(){
        this.db = new Database();
        this.dbU = db.getDBU();
    }

    public void searchDB(String plainP){

//        Boolean checkP = BCrypt.checkpw(plainP, "$2a$11$UhrUS5GNs1AxY7isLwotUeWrfzMISLKGGNc2z9HaAGfwFGISeQCnO");
//        System.out.println(BCrypt.checkpw(plainP, "$2a$11$UhrUS5GNs1AxY7isLwotUeWrfzMISLKGGNc2z9HaAGfwFGISeQCnO"));

        Query q = dbU.orderByChild("email");
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot searchEml : snapshot.getChildren()) {
                        for(int i = 0; i < snapshot.getChildrenCount(); i++){
                            String emailVal = searchEml.child("email").getValue(String.class);
                            if(emailVal != null){
                                try {
                                    if (BCrypt.checkpw(plainP, emailVal)) {
                                        String hkCheck = searchEml.child("hk").getValue(String.class);
                                        String vltId = searchEml.child("vaultID").getValue(String.class);
                                        System.out.println("hk : " + hkCheck);
                                        System.out.println("vlt : " + vltId);
                                        dbU.orderByChild("email").equalTo(emailVal).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                for(DataSnapshot searchHk : snapshot.getChildren()){
                                                    for(int j = 0; j < snapshot.getChildrenCount(); j++){
                                                        ArrayList<Object> vals = new ArrayList<>();
                                                        vals.add(searchHk.getValue());
                                                        System.out.println(vals.get(j));

                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                        System.out.println("Email = " + emailVal);
//                                        System.out.println("dbU.getParent().getKey() = " + k);
                                        break;
                                    }
                                }catch (IllegalArgumentException e){
                                    System.out.println("Whoops!");
                                }
                            }


                        }

//                        if(snapshot.child("email").getValue(String.class) == BCrypt.checkpw(plainP, snapshot.child("email").getValue()))
//                        // do something with the individual "issues"
////                        if(snapshot.equals(BCrypt.checkpw(plainP, (String) snapshot.getValue()))){
////                            System.out.println(issue);
////                        }
////                        System.out.println("Is it working?");
////                        System.out.println(issue);
//                        System.out.println(snapshot.getValue());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error){

                    }
                });
            }
        }

// Below is fine to check if email exists
//    public void searchDB(String plainP){
//
////        Boolean checkP = BCrypt.checkpw(plainP, "$2a$11$UhrUS5GNs1AxY7isLwotUeWrfzMISLKGGNc2z9HaAGfwFGISeQCnO");
////        System.out.println(BCrypt.checkpw(plainP, "$2a$11$UhrUS5GNs1AxY7isLwotUeWrfzMISLKGGNc2z9HaAGfwFGISeQCnO"));
//
//        Query q = dbU.orderByChild("email");
//        q.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    // dataSnapshot is the "issue" node with all children with id 0
//                    for (DataSnapshot issue : snapshot.getChildren()) {
//                        for(int i = 0; i < snapshot.getChildrenCount(); i++){
//                            String emailVal = issue.child("email").getValue(String.class);
//                            if(emailVal != null){
//                                try {
//                                    if (BCrypt.checkpw(plainP, emailVal)) {
//                                        break;
//                                    }
//                                }catch (IllegalArgumentException e){
//                                    System.out.println("Whoops!");
//                                }
//                            }
//
//
//                        }
//
////                        if(snapshot.child("email").getValue(String.class) == BCrypt.checkpw(plainP, snapshot.child("email").getValue()))
////                        // do something with the individual "issues"
//////                        if(snapshot.equals(BCrypt.checkpw(plainP, (String) snapshot.getValue()))){
//////                            System.out.println(issue);
//////                        }
//////                        System.out.println("Is it working?");
//////                        System.out.println(issue);
////                        System.out.println(snapshot.getValue());
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error){
//
//            }
//        });
//    }
//}

