package com.example.pccuong.appbook.View.Login.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pccuong.appbook.Presenter.TrangChu.XulyMenu.registor.ProsentorRegistor;
import com.example.pccuong.appbook.R;
import com.example.pccuong.appbook.View.Login.ViewRegistor;
import com.example.pccuong.appbook.model.ObjectClass.Users;

/**
 * Created by PCCuong on 12/31/2016.
 */

public class FragmantRegister extends Fragment implements ViewRegistor, View.OnClickListener, View.OnFocusChangeListener {
    ProsentorRegistor prosentorRegistor;
    Button btnDangKy;
    EditText edHoTen, edMatKhau, edNhapLaiMatKhau, edDiaChiEmail;
    SwitchCompat sEmailDocQuyen;
    TextInputLayout input_edHoTen;
    TextInputLayout input_edMatKhau;
    TextInputLayout input_edNhapLaiMatKhau;
    TextInputLayout input_edDiaChiEmail;
    Boolean kiemtrathongtin = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragmant_regisetor, container, false);

        btnDangKy = (Button) view.findViewById(R.id.btnDangKy);
        edHoTen = (EditText) view.findViewById(R.id.edHoTenDK);
        edMatKhau = (EditText) view.findViewById(R.id.edMatKhauDK);
        edNhapLaiMatKhau = (EditText) view.findViewById(R.id.edNhapLaiMatKhauDK);
        edDiaChiEmail = (EditText) view.findViewById(R.id.edDiaChiEmailDK);
        sEmailDocQuyen = (SwitchCompat) view.findViewById(R.id.sEmailDocQuyen);
        input_edHoTen = (TextInputLayout) view.findViewById(R.id.input_edHoTenDK);
        input_edMatKhau = (TextInputLayout) view.findViewById(R.id.input_edMatKhauDK);
        input_edNhapLaiMatKhau = (TextInputLayout) view.findViewById(R.id.input_edNhapLaiMatKhauDK);
        input_edDiaChiEmail = (TextInputLayout) view.findViewById(R.id.input_edDiaChiEmailDK);

        prosentorRegistor = new ProsentorRegistor(this);

        btnDangKy.setOnClickListener(this);
        edHoTen.setOnFocusChangeListener(this);
        edNhapLaiMatKhau.setOnFocusChangeListener(this);
        edDiaChiEmail.setOnFocusChangeListener(this);
        return view;
    }

    @Override
    public void DangKyThangCong() {
        Toast.makeText(getActivity(), "Đăng ký thành công !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DangKyThatBai() {
        Toast.makeText(getActivity(), "Đăng ký That Bai!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.btnDangKy:
                btnDangKy();

                break;
        }
    }

    String emaildocquyen = "";

    private void btnDangKy() {
        String name = edHoTen.getText().toString();
        String address = edDiaChiEmail.getText().toString();
        String encrypted_password = edMatKhau.getText().toString();
        String nhaplaimatkhau = edNhapLaiMatKhau.getText().toString();

        sEmailDocQuyen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                emaildocquyen = b + "";
            }
        });

        if (kiemtrathongtin) {
            Users users = new Users();
            users.setName(name);
            users.setAddress(address);
            users.setEncrypted_password(encrypted_password);
            users.setEmailDocQuyen(emaildocquyen);
            prosentorRegistor.ThucHienDangKy(users);
        } else {
            Log.d("kiemtra", "Dang ky that bai ");
        }


    }

    @Override
    public void onFocusChange(View v, boolean b) {
        int id = v.getId();
        switch (id) {
            case R.id.edHoTenDK:
                if (!b) {
                    String chuoi = ((EditText) v).getText().toString();
                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_edHoTen.setErrorEnabled(true);
                        input_edHoTen.setError("Bạn chưa nhận mục này !");
                        kiemtrathongtin = false;
                    } else {
                        input_edHoTen.setErrorEnabled(false);
                        input_edHoTen.setError("");
                        kiemtrathongtin = true;
                    }
                }
                ;
                break;

            case R.id.edDiaChiEmailDK:
                if (!b) {

                    String chuoi = ((EditText) v).getText().toString();

                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_edDiaChiEmail.setErrorEnabled(true);
                        input_edDiaChiEmail.setError("Bạn chưa nhận mục này !");
                        kiemtrathongtin = false;
                    } else {
                        Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                        if (!kiemtraemail) {
                            input_edDiaChiEmail.setErrorEnabled(true);
                            input_edDiaChiEmail.setError("Đây không phải là địa chỉ Email !");
                            kiemtrathongtin = false;
                        } else {
                            input_edDiaChiEmail.setErrorEnabled(false);
                            input_edDiaChiEmail.setError("");
                            kiemtrathongtin = true;
                        }
                    }
                }
                ;
                break;

            case R.id.edMatKhauDK:
                ;
                break;

            case R.id.edNhapLaiMatKhauDK:
                if (!b) {
                    String chuoi = ((EditText) v).getText().toString();
                    String matkhau = edMatKhau.getText().toString();
                    if (!chuoi.equals(matkhau)) {
                        input_edNhapLaiMatKhau.setErrorEnabled(true);
                        input_edNhapLaiMatKhau.setError("Mật khẩu không trùng khớp !");
                        kiemtrathongtin = false;
                    } else {
                        input_edNhapLaiMatKhau.setErrorEnabled(false);
                        input_edNhapLaiMatKhau.setError("");
                        kiemtrathongtin = true;
                    }
                }

                ;
                break;

        }
    }
}
