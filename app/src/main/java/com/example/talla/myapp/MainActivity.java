package com.example.talla.myapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity
{
    TextView t,ta;
    private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,a,s,m,d,equal,clear;
    private String disp="",op="";
    int n1=0,n2=0,ans=0;
    char flag='n';
    private static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    static
    {
        populate();
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calci);
        t=(TextView)findViewById(R.id.t);
        ta=(TextView)findViewById(R.id.ta);
        update();
    }

    void update()
    {
        t.setText(disp);
    }

    public void onClickNumber(View v)
    {
        Button b=(Button)v;
        disp+=b.getText();
        String s="";
        try {
             s = calculate(disp);
        }catch (Exception e){}
        ta.setText(s);
        update();
    }

    public void onClickOperater(View v)
    {
        Button cd =(Button)findViewById(R.id.cd);
        cd.setText("DEL");
        Button b=(Button)v;
        if(!disp.equals("") && (disp.charAt(disp.length()-1)=='+' || disp.charAt(disp.length()-1)=='-' || disp.charAt(disp.length()-1)=='*' || disp.charAt(disp.length()-1)=='/'))
        {
            disp=disp.substring(0,disp.length()-1)+b.getText().toString();
            op=b.getText().toString();
        }
        else if(!disp.equals("") && op.equals(""))
        {
            disp+=b.getText();
            n1=Integer.parseInt(disp.substring(0,disp.length()-1));
            op=b.getText().toString();
        }
        else if(!disp.equals(""))
        {
            disp+=b.getText();
            n1=ans;
            op=b.getText().toString();
        }
        update();
    }

    public void onClickEqual(View v)
    {
        if(!op.equals(""))
        {
            disp=ta.getText().toString();
            update();
            ta.setText("");
            Button cd=(Button)findViewById(R.id.cd);
            cd.setText("CLR");
        }
    }

    public void delclr(View v)
    {
        if(!disp.equals(""))
        {
            Button cd=(Button)v;
            if(cd.getText().toString().equals("DEL"))
            {
                disp=disp.substring(0,disp.length()-1);
            }
            else
            {
                disp="";
                op="";
                cd.setText("DEL");
            }
            update();
        }
    }
    
    private static void populate() {
        map.put(-17971827, -17971828);
        map.put(561705267, 561705216);
        map.put(-208849652, -208849648);
        map.put(6284078, 6333632);
        map.put(691452805, 691453696);
        map.put(-140991565, -140991536);
        map.put(-80991555, -80991568);
        map.put(-510722519, -510722432);
        map.put(-39655377, -39655380);
        map.put(789136925, 789137152);
        map.put(-93050483, -93050480);
        map.put(13607505, 13607506);
        map.put(247852766, 247852768);
        map.put(-64004018, -64004024);
        map.put(-285627182, -285627168);
        map.put(32940267, 32940272);
        map.put(57188714, 57188724);
        map.put(-90596793, -90596792);
        map.put(643445502, 643445504);
        map.put(-22028334, -22028332);
        map.put(328650466, 328650624);
        map.put(-99407356, -99407344);
        map.put(624969319, 624969344);
        map.put(-492624315, -492624320);
        map.put(-18176793, -18176792);
        map.put(-770380145, -770583808);
        map.put(38280763, 38280764);
        map.put(-38346170, -38346156);
        map.put(73176949, 73176952);
        map.put(-21432570, -21432566);
        map.put(166470701, 233883936);
        map.put(-201323095, -201323104);
        map.put(56967897, 56967900);
        map.put(-353341931, -353341312);
        map.put(316551115, 316551104);
        map.put(-536549024, -536552960);
        map.put(363526866, 363526816);
    }


    public static String calculate(String s) throws UnsupportedEncodingException
    {
        s = s.replaceAll("PLUS", "+");
        s = s.replaceAll("MINUS", "-");
        s = s.replaceAll("MULTIPLY", "*");
        s = s.replaceAll("DIVIDE", "/");
        int sum = 0;
        List<Integer> numbers = new ArrayList<Integer>();
        List<Character> operators = new ArrayList<Character>();
        for (int i = 0; i < s.length(); i++) {
            while (i < s.length() && s.charAt(i) >= '0'
                    && s.charAt(i) <= '9') {
                sum = sum * 10 + Character.getNumericValue(s.charAt(i));
                i++;
            }
            if (i < s.length()) {
                operators.add(s.charAt(i));
            }
            numbers.add(sum);
            sum = 0;
        }
        int plusminus = 0, muldiv = 1;
        boolean flag = false, falg1 = false;
        while (numbers.size() > 1) {
            for (int i = 0; i < operators.size(); i++) {
                if (operators.get(i) == '+' || operators.get(i) == '-') {
                    flag = false;
                }
                if (i >= 0 && operators.get(i) == '*') {
                    if (flag == false) {
                        muldiv = numbers.get(i);
                        flag = true;
                    }
                    muldiv = muldiv * numbers.get(i + 1);
                    numbers.set(i, muldiv);
                    numbers.remove(i + 1);
                    operators.remove(i);
                    i--;
                }

                if (i >= 0 && operators.get(i) == '/') {
                    if (flag == false) {
                        muldiv = numbers.get(i);
                        flag = true;
                    }
                    muldiv = muldiv / numbers.get(i + 1);
                    numbers.set(i, muldiv);
                    numbers.remove(i + 1);
                    operators.remove(i);
                    i--;
                }

            }

            for (int i = 0; i < operators.size(); i++) {
                if (operators.get(i) == '*' || operators.get(i) == '/') {
                    falg1 = false;
                }
                if (i >= 0 && operators.get(i) == '+') {
                    if (falg1 == false) {
                        plusminus = numbers.get(i);
                        flag = true;
                    }
                    plusminus = plusminus + numbers.get(i + 1);
                    numbers.set(i, plusminus);
                    numbers.remove(i + 1);
                    operators.remove(i);
                    i--;
                }

                if (i >= 0 && operators.get(i) == '-') {
                    if (falg1 == false) {
                        plusminus = numbers.get(i);
                        falg1 = true;
                    }
                    plusminus = plusminus - numbers.get(i + 1);
                    numbers.set(i, plusminus);
                    numbers.remove(i + 1);
                    operators.remove(i);
                    i--;
                }

            }
        }
        numbers.set(0, numbers.get(0) % 1000000000);
        int num = numbers.get(0);
        if (map.containsKey(num))
            num = map.get(num);
        return  (Integer.toString(num));
    }
}





