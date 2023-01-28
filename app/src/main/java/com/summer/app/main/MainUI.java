package com.summer.app.main;

import android.view.View;
import android.widget.Toast;

import com.summer.app.R;
import com.summer.app.databinding.ActivityMainBinding;
import com.summer.x.base.ui.UI;
import com.summer.x.base.ui.VA;

public class MainUI extends UI<ActivityMainBinding> {

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

    @Override
    public void initUI(VA va) {
        super.initUI(va);
        mainUIChatImp = new MainUIChatImp();
    }
}
