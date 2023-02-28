package com.textonphoto.addtext.editphoto.Sticker;

import android.graphics.Matrix;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MyMatrix extends Matrix implements Parcelable, Serializable {
    public static final Creator CREATOR = new Creator() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new MyMatrix[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new MyMatrix(parcel);
        }
    };

    public int describeContents() {
        return 0;
    }

    public MyMatrix() {
        super();
    }

    public MyMatrix(Matrix matrix) {
        super(matrix);
    }

    public MyMatrix(Parcel parcel) {
        float[] fArr = new float[9];
        parcel.readFloatArray(fArr);
        super.setValues(fArr);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        super.setValues((float[]) objectInputStream.readObject());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Object obj = new float[9];
        super.getValues((float[]) obj);
        objectOutputStream.writeObject(obj);
    }

    public void writeToParcel(Parcel parcel, int i) {
        float[] jj = new float[9];
        super.getValues(jj);
        parcel.writeFloatArray(jj);
    }
}
