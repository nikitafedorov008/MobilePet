package ru.com.jetbrainsresearch.ido.helpers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import ru.com.jetbrainsresearch.ido.R;


public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.XViewHolder> {
    private ArrayList<CartItem> data;
    private final Context ctx;

    public CartItemAdapter(ArrayList<CartItem> data, Context _ctx) {
        this.data = data;
        this.ctx = _ctx;
    }

    @NonNull
    @Override
    public XViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item, parent, false);
        return new XViewHolder(itemView, ctx);
    }

    @Override
    public void onBindViewHolder(@NonNull XViewHolder holder, int position) {
        CartItem x = data.get(position);
        holder.checkBox.setText(x.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class XViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        XViewHolder(View view, Context ctx) {
            super(view);
            checkBox = (CheckBox) view.findViewById(R.id.cb_item);
            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.rvi_fab);
            EditText et = (EditText) view.findViewById(R.id.rvi_et);
            et.setFocusableInTouchMode(true);
            et.requestFocus();
            InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            /*et.setOnKeyListener((view1, i, event) -> {
                if( event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    checkBox.setText(et.getText());
                    checkBox.setVisibility(View.VISIBLE);
                    fab.setVisibility(View.GONE);
                    et.setVisibility(View.GONE);
                }
                return true;
            });*/
            fab.setOnClickListener((View v) -> {
                checkBox.setText(et.getText());
                checkBox.setVisibility(View.VISIBLE);
                fab.setVisibility(View.GONE);
                et.setVisibility(View.GONE);
            });
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }
    }

    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }


    public void addItem(CartItem dataObj) {
        data.add(dataObj);
        notifyItemInserted(data.size());
    }

}