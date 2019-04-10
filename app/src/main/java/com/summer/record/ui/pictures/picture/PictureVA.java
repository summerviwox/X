package com.summer.record.ui.pictures.picture;

import com.summer.record.data.model.PictureB;
import com.summer.x.base.ui.VA;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PictureVA extends VA {

    private PictureB picture;

    private boolean uploading = false;
}
