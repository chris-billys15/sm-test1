package suitmedia.test.intern.ui.guest;

import java.util.ArrayList;

import suitmedia.test.intern.data.GuestModel;

interface GuestView {
    public abstract void onSucceedSetView(ArrayList<GuestModel> guests);

    public abstract void viewToast(String str);
}
