package com.summer.app.main;

import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.summer.app.databinding.ActivityMainBinding;
import com.summer.x.base.ui.UI;
import com.summer.x.base.ui.DA;
import com.summer.x.base.ui.XActivity;
import com.summer.x.base.ui.XFragment;

public class MainUI extends UI<ActivityMainBinding,MainDA> {

    MainUIInitInf mainUIInitInf = new MainUIInitInf() {
        @Override
        public void ModuleAInit() {
            Toast.makeText(getXActivity(),getXActivity().getClass().getName(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void ModuleBInit(String data) {
            Toast.makeText(getXActivity(),data,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void ModuleCInit() {

        }
    };
    MainUIConfigInf mainUIConfigInf = new MainUIConfigInf() {
        @Override
        public void ModuleAConfig() {

        }

        @Override
        public void ModuleBConfig() {

        }

        @Override
        public void ModuleCConfig() {

        }
    };
    MainUIChatImp mainUIChatImp;

    public MainUI(@NonNull XActivity xActivity, @Nullable XFragment xFragment, @Nullable ViewGroup viewGroup) {
        super(xActivity, xFragment, viewGroup);
    }

}
