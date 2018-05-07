package com.prasant.fragmentactivitytransaction.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prasant.fragmentactivitytransaction.R;
import com.prasant.fragmentactivitytransaction.interfacefile.DeleteUser;
import com.prasant.fragmentactivitytransaction.model.StudentModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context mContext;
    private List<StudentModel> mstudentList;
    DeleteUser deleteUser;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, email, phone;
        public ImageView img_edit, img_delete;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.txtName);
            email = (TextView) view.findViewById(R.id.txtemail);
            phone = (TextView) view.findViewById(R.id.txtPhone);
            img_delete = (ImageView) view.findViewById(R.id.imgDelete);
            img_edit = (ImageView) view.findViewById(R.id.imgDelete);
        }
    }


    public UserAdapter(Context mContext, List<StudentModel> studentList) {
        this.mContext = mContext;
        this.mstudentList = studentList;
        deleteUser = (DeleteUser) mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_details, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final StudentModel studentModel = mstudentList.get(position);
        holder.name.setText(studentModel.getStd_name());
        holder.email.setText(studentModel.getStd_email());
        holder.phone.setText(studentModel.getStd_phone());
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = studentModel.getStd_Id();
                getEmpIdAndRemoveEmp(id);

            }
        });


    }

    private void getEmpIdAndRemoveEmp(final int id) {

        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(mContext)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setIcon(R.drawable.ic_delete_forever_black_24dp)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        deleteUser.deleteUser(id);
                        dialog.dismiss();
                    }

                })


                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        myQuittingDialogBox.show();
    }


    @Override
    public int getItemCount() {
        return mstudentList.size();
    }

}
