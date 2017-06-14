package com.example.talla.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedHashSet;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity
{
    TextView t,ta;
    private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,a,s,m,d,equal,clear;
    private String disp="",op="";
    int n1=0,n2=0,ans=0;
    char flag='n';

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

    String  calculate(String s)
    {
        LinkedList L=new LinkedList();
        int x=0;
        for(int j=0;j<s.length();j++)
        {
            if(s.charAt(j)=='0' || s.charAt(j)=='1' || s.charAt(j)=='2' || s.charAt(j)=='3' || s.charAt(j)=='4' || s.charAt(j)=='5' || s.charAt(j)=='6' || s.charAt(j)=='7' || s.charAt(j)=='8' || s.charAt(j)=='9')
            {
                x=x*10+(s.charAt(j))-48;
            }
            else if(s.charAt(j)=='+')
            {
                L.add(x);
                x=0;
                L.add("+");
            }
            else if(s.charAt(j)=='/')
            {
                L.add(x);
                x=0;
                L.add("/");
            }
            else if(s.charAt(j)=='-')
            {
                L.add(x);
                x=0;
                L.add("-");
            }
            else if(s.charAt(j)=='*')
            {
                L.add(x);
                x=0;
                L.add("*");
            }
            if(j==s.length()-1)
            {
                L.add(x);
                x=0;
            }
        }
        while(L.size()!=1)
        {
            for(int i=0;i<L.size();i++)
            {
                int a,b;
                try{
                    if(L.get(i)=="/")
                    {
                        a=(int) L.get(i-1);
                        b=(int) L.get(i+1);
                        a=a/b;
                        L.set(i-1,a);
                        L.remove(i);
                        L.remove(i);
                        break;
                    }
                }catch(ArithmeticException e) {
                    a=0;
                    L.set(i-1,a);
                    L.remove(i);
                    L.remove(i);
                    break;
                }
            }

            for(int i=0;i<L.size();i++)
            {
                if(L.get(i)=="*")
                {
                    int a,b;
                    a=(int) L.get(i-1);
                    b=(int) L.get(i+1);
                    a=a*b;
                    L.set(i-1,a);
                    L.remove(i);
                    L.remove(i);
                    break;
                }
            }
            for(int i=0;i<L.size();i++)
            {
                if(L.get(i)=="-")
                {
                    int a,b;
                    a=(int) L.get(i-1);
                    b=(int) L.get(i+1);
                    a=a-b;
                    L.set(i-1,a);
                    L.remove(i);
                    L.remove(i);
                    break;
                }
            }
            for(int i=0;i<L.size();i++)
            {
                if(L.get(i)=="+")
                {
                    int a,b;
                    a=(int) L.get(i-1);
                    b=(int) L.get(i+1);
                    a=a+b;
                    L.set(i-1,a);
                    L.remove(i);
                    L.remove(i);
                    break;
                }
            }
        }
        return (L.get(0)).toString();
    }


    public void onClickNumber(View v)
    {
        Button b=(Button)v;
        disp+=b.getText();
        String s=calculate(disp);
        ta.setText(s);
        update();
    }

    public void onClickOperater(View v)
    {
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
}



