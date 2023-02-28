package com.textonphoto.addtext.editphoto.Sticker;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class StickerData implements Parcelable, Serializable {
    public static final Creator CREATOR = new Creator() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new StickerData[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new StickerData(parcel);
        }
    };
    private static final String f = "StickerData";
    public MyMatrix a;
    public MyMatrix b;
    public int c;
    public float d;
    public float e;

    public int describeContents() {
        return 0;
    }

    public StickerData(int i) {
        this.a = new MyMatrix();
        this.a.reset();
        this.c = i;
    }

    public StickerData(Parcel parcel) {
        this.d = parcel.readFloat();
        this.e = parcel.readFloat();
        this.a = (MyMatrix) parcel.readParcelable(MyMatrix.class.getClassLoader());
        this.b = (MyMatrix) parcel.readParcelable(MyMatrix.class.getClassLoader());
        this.c = parcel.readInt();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.d = objectInputStream.readFloat();
        this.e = objectInputStream.readFloat();
        this.a = (MyMatrix) objectInputStream.readObject();
        this.b = (MyMatrix) objectInputStream.readObject();
        this.c = objectInputStream.readInt();
    }

    public static StickerData[] a(Parcelable[] parcelableArr) {
        if (parcelableArr == null) {
            return null;
        }
        Object obj = new StickerData[parcelableArr.length];
        System.arraycopy(parcelableArr, 0, obj, 0, parcelableArr.length);
        return (StickerData[]) obj;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeFloat(this.d);
        objectOutputStream.writeFloat(this.e);
        objectOutputStream.writeObject(this.a);
        objectOutputStream.writeObject(this.b);
        objectOutputStream.writeInt(this.c);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.e);
        parcel.writeParcelable(this.a, i);
        parcel.writeParcelable(this.b, i);
        parcel.writeInt(this.c);
    }
}
