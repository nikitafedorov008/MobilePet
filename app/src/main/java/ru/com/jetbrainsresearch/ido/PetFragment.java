package ru.com.jetbrainsresearch.ido;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PetFragment extends Fragment {

    public TextView countTv, countTv2, countTv3, countTv4;
    public ImageButton countBtn;
    public int count1 = 50, count2 = 50, count3 = 50, count4 = 50;

    public PetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet, container, false);
        countTv = (TextView) view.findViewById(R.id.count_tv);
        countTv2 = (TextView) view.findViewById(R.id.count_tv2);
        countTv3 = (TextView) view.findViewById(R.id.count_tv3);
        countTv4 = (TextView) view.findViewById(R.id.count_tv4);

        countTv.setText(String.valueOf(count1));
        countTv2.setText(String.valueOf(count2));
        countTv3.setText(String.valueOf(count3));
        countTv4.setText(String.valueOf(count4));
        countBtn = (ImageButton) view.findViewById(R.id.count_btn);
        countBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseCount();
            }
        });
        return view;
    }

    private void increaseCount() {
        int current = Integer.parseInt((String) countTv.getText());
        countTv.setText(String.valueOf(count1 +=20));
        countTv2.setText(String.valueOf(count2 -= 2));
        countTv3.setText(String.valueOf(count3 -= 5));
        countTv4.setText(String.valueOf(count4 += 2));

        if (count1 == 0){
            countBtn.setImageResource(R.drawable.zombiecat);
        }else if (count2 == 0){
            countBtn.setImageResource(R.drawable.zombiecat);
        }else if (count3 == 0){
            countBtn.setImageResource(R.drawable.zombiecat);
        }else if (count4 == 0){
            countBtn.setImageResource(R.drawable.zombiecat);
        }
        //pause
    }

}
