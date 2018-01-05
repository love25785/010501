package com.wl.a010501;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{

    int ch;
    int tmp;
    boolean[] chks = new boolean[5];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);//建建構器
        {
            builder.setTitle("this is title");
            builder.setMessage("Hello");

            builder.setPositiveButton("確定", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    Toast.makeText(MainActivity.this, "按下了確定", Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    Toast.makeText(MainActivity.this, "按下了取消", Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNeutralButton("help", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    Toast.makeText(MainActivity.this, "help", Toast.LENGTH_SHORT).show();
                }
            });
        }
        builder.show();
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void click2(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder((MainActivity.this));
        builder.setTitle("this is title");
        final EditText ed = new EditText(MainActivity.this); //要final
        final TextView tv = findViewById(R.id.textView); //要final
        builder.setView(ed); //EditText 是 textView的子類別 可放入
        ed.setText(tv.getText().toString());

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Toast.makeText(MainActivity.this, "按下了確定", Toast.LENGTH_SHORT).show();
                tv.setText(ed.getText().toString());  ////變數要final 這才能用
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Toast.makeText(MainActivity.this, "按下了取消", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNeutralButton("help", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Toast.makeText(MainActivity.this, "help", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

/////////////////////////////////////////////////////setItems////////////////////////////////////////////////////////////

    public void click3(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("列表");
        final String fruits[] = {"蘋果", "香蕉", "梨子"};
        final TextView tv2 = findViewById(R.id.textView2); //一樣要加final
        builder.setItems(fruits, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                tv2.setText(fruits[i]);
            }
        });
        builder.setCancelable(false); //這是不讓  使用者點擊builder視窗以外作取消
        // 但就要再做個setNegative給使用者在builder內作取消
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

            }
        });
        builder.show();
    }

    /////////////////////////////////////////////////setSingleChoiceItems////////////////////////////////////////////////////////////////
    public void click4(View v)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("單選列表");
        final String fruits[] = {"蘋果", "香蕉", "梨子"};
        final TextView tv3 = (TextView) findViewById(R.id.textView3);
        tmp = ch;//3.讓每次點按鍵時，重置tmp為已選的，不然沒這行  當選好某水果，下次選另水果後取消 在下次直接點確定(沒點選所以tmp不會變，直接以上次取消的那個為ch)，又或是將這行寫在取消那
        builder.setSingleChoiceItems(fruits, ch, new DialogInterface.OnClickListener()//第二個引數是起始選哪個，-1是不選
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                tmp = i;  //如果1.這邊直接寫 ch=i; 且 ↓
            }
        });
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                ch = tmp;//且如果2.沒寫這行   當使用者先選了其他水果，再按取消，下次再打開列表時，預選會變成上次選的，而不是之前已確定選的，所以在多個TMP變數來放
                tv3.setText(fruits[ch]);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    /////////////////////////////////////////////////setMultiChoiceItems////////////////////////////////////////////////////////////////
    public void click5(View v)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("多選列表");
        final String fruits[] = {"蘋果", "香蕉", "梨子", "芭樂", "鳳梨"};
        final TextView tv4 = (TextView) findViewById(R.id.textView4);
        builder.setMultiChoiceItems(fruits, chks, new DialogInterface.OnMultiChoiceClickListener()//第二個引數要放boleea值  來告知那些被選
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b)
            {

            }
        });

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                /*
                            如果是用String s="a";    s=s+"b" ; s=s+"c";  會產生 "a"  ,  "ab"  , "abc" 等三個字串各占用記憶體，
                            而S是指向"abc"，而系統有空會回收"a", "ab"，但這樣沒效率，
                            所以StringBuilder是會動態加入字串，直到該記憶體滿才會再增另一記憶體來存放字串↓
                            */
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j <= 4; j++)
                {
                    if (chks[j])
                    {
                        sb.append(fruits[j] + ",");
                    }
                }
                tv4.setText((sb.toString()));
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    ///////////////////////////////////////////////////自訂layout /////////////////////////////////////////////////////////
    public void click6(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder((MainActivity.this));
        builder.setTitle("this is title");
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);//LayoutInflater是Layout解壓器，這行是指從MainActivity解壓出layout
        View v1 = inflater.inflate(R.layout.layout1, null);//指向我們在res資源裡創的layout的自創layout1

        final TextView tv=v1.findViewById(R.id.textView5); //這個物件在我們自創的layout1裡面 所以前面+v1
        Button btn1=v1.findViewById(R.id.button7);//同上
        btn1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                tv.setText("hello world");
            }
        });

        builder.setView(v1);

        builder.setPositiveButton("確定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

            }
        });
        builder.show();
    }
}
