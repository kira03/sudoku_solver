package com.example.sameer.sudoku_solver;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    RadioButton rd1,rd2,rd3,rd4,rd5,rd6,rd7,rd8,rd9;
    RadioGroup rdg;
    Button enter,solve1;
    ImageView imageView;
    TextView in;
    Bitmap bitmap;
    Canvas canvas;
    Paint paint;
    int [][]sudoku=new int[9][9];
    int pos=0;
    int reset=0;
    int row,col;
    int[][] empty=new int[9][9];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enter=(Button)findViewById(R.id.enter);
        rdg=(RadioGroup)findViewById(R.id.rdg);
        rd1=(RadioButton)findViewById(R.id.rd1);rd2=(RadioButton)findViewById(R.id.rd2);rd3=(RadioButton)findViewById(R.id.rd3);rd4=(RadioButton)findViewById(R.id.rd4);rd5=(RadioButton)findViewById(R.id.rd5);rd6=(RadioButton)findViewById(R.id.rd6);rd7=(RadioButton)findViewById(R.id.rd7);rd8=(RadioButton)findViewById(R.id.rd8);rd9=(RadioButton)findViewById(R.id.rd9);
        rd1.setOnClickListener(this);rd2.setOnClickListener(this);rd3.setOnClickListener(this);rd4.setOnClickListener(this);rd5.setOnClickListener(this);rd6.setOnClickListener(this);rd7.setOnClickListener(this);rd8.setOnClickListener(this);rd9.setOnClickListener(this);
        solve1=(Button)findViewById(R.id.solve1);
        in=(TextView) findViewById(R.id.in);
        enter.setOnClickListener(this);
        solve1.setOnClickListener(this);
        draw(0);
    }
    public void onClick(View view) {
        int id=view.getId();

        switch (id) {
            case R.id.rd1:
                pos = 1;
                rd2.setChecked(false);rd3.setChecked(false);rd4.setChecked(false);rd5.setChecked(false);rd6.setChecked(false);rd7.setChecked(false);rd8.setChecked(false);rd9.setChecked(false);
                draw(1);
                break;
            case R.id.rd2:
                pos = 2;
                rd1.setChecked(false);rd3.setChecked(false);rd4.setChecked(false);rd5.setChecked(false);rd6.setChecked(false);rd7.setChecked(false);rd8.setChecked(false);rd9.setChecked(false);
                draw(2);
                break;
            case R.id.rd3:
                pos = 3;
                rd2.setChecked(false);rd1.setChecked(false);rd4.setChecked(false);rd5.setChecked(false);rd6.setChecked(false);rd7.setChecked(false);rd8.setChecked(false);rd9.setChecked(false);
                draw(3);
                break;
            case R.id.rd4:
                pos = 4;
                rd2.setChecked(false);rd3.setChecked(false);rd1.setChecked(false);rd5.setChecked(false);rd6.setChecked(false);rd7.setChecked(false);rd8.setChecked(false);rd9.setChecked(false);
                draw(4);
                break;
            case R.id.rd5:
                pos = 5;
                rd2.setChecked(false);rd3.setChecked(false);rd4.setChecked(false);rd1.setChecked(false);rd6.setChecked(false);rd7.setChecked(false);rd8.setChecked(false);rd9.setChecked(false);
                draw(5);
                break;
            case R.id.rd6:
                pos = 6;
                rd2.setChecked(false);rd3.setChecked(false);rd4.setChecked(false);rd5.setChecked(false);rd1.setChecked(false);rd7.setChecked(false);rd8.setChecked(false);rd9.setChecked(false);
                draw(6);
                break;
            case R.id.rd7:
                pos = 7;
                rd2.setChecked(false);rd3.setChecked(false);rd4.setChecked(false);rd5.setChecked(false);rd6.setChecked(false);rd1.setChecked(false);rd8.setChecked(false);rd9.setChecked(false);
                draw(7);
                break;
            case R.id.rd8:
                pos = 8;
                rd2.setChecked(false);rd3.setChecked(false);rd4.setChecked(false);rd5.setChecked(false);rd6.setChecked(false);rd7.setChecked(false);rd1.setChecked(false);rd9.setChecked(false);
                draw(8);
                break;
            case R.id.rd9:
                pos = 9;
                rd2.setChecked(false);rd3.setChecked(false);rd4.setChecked(false);rd5.setChecked(false);rd6.setChecked(false);rd7.setChecked(false);rd8.setChecked(false);rd1.setChecked(false);
                draw(9);
                break;
            case R.id.enter:
                if(pos!=0) {
                    View view1 = this.getCurrentFocus();
                    if (view1 != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    String a = in.getText().toString();
                    if (a.length() == 9) {
                        for (int i = 0; i < 9; i++) {
                            char b = a.charAt(i);
                            String c = b + "";
                            int d = Integer.parseInt(c);
                            in.setText("");
                            sudoku[pos - 1][i] = d;

                        }
                        draw(pos);
                    } else {
                        try {
                            Toast.makeText(this, "not valid", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                        }

                    }
                }
                break;
            case R.id.solve1:
                boolean solution=true;
                try {
                    for(int i=0;i<9;i++)
                    {
                        for(int j=0;j<9;j++)
                        {
                                if(sudoku[i][j]!=0&&solution==true)
                                {
                                    solution = checkRow1(i,j, sudoku[i][j])&& checkCol1(j,i,sudoku[i][j])&& checkBox1(i, j, sudoku[i][j]);
                                    if (solution == false)
                                        Toast.makeText(MainActivity.this, "input not valid", Toast.LENGTH_SHORT).show();
                                }
                        }
                    }
                    if(solution==true)
                        solve(0, 0);

                }catch (Exception e){}
                draw(0);
        }



    }
    protected boolean  checkRow1( int row,int j, int num )
    {
        for( int col = 0; col < 9; col++ )
            if( sudoku[row][col] == num && col!=j)
                return false;

        return true;
    }


    protected boolean checkCol1( int col, int i,int num )
    {
        for( int row = 0; row < 9; row++ )
            if( sudoku[row][col] == num && row!=i)
                return false;

        return true;
    }


    protected boolean checkBox1( int row, int col, int num )
    {
        int i,j;
        i=row;
        j=col;
        row = (row / 3) * 3 ;
        col = (col / 3) * 3 ;

        for( int r = 0; r < 3; r++ )
            for( int c = 0; c < 3; c++ )
                if( sudoku[row+r][col+c] == num &&i!=r&&j!=c)
                    return false ;

        return true ;
    }
    public void draw(int posi)
    {
        imageView=(ImageView)findViewById(R.id.imageView);
        bitmap = Bitmap.createBitmap(1000,1000, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        imageView.setImageBitmap(bitmap);
        paint = new Paint();

                canvas.drawPaint(paint);
                paint.setTextSize(30);
                paint.setColor(Color.rgb(255, 255, 255));
                //displaying numbers
                for(int i=0;i<9;i++)
                {
                    for(int j=0;j<9;j++) {
                        if (sudoku[i][j] != 0) {
                            if(empty[i][j]==0) {
                                paint.setColor(Color.rgb(255, 255, 255));
                                canvas.drawText(sudoku[i][j] + "", 105 + j * 100, 105 + i * 100, paint);
                            }
                            else{
                                paint.setColor(Color.rgb(255, 0, 0));
                                canvas.drawText(sudoku[i][j] + "", 105 + j * 100, 105 + i * 100, paint);
                            }
                        }
                    }

                }



       if(posi!=0) {
           paint.setColor(Color.rgb(0, 255, 0));
           paint.setStrokeWidth(10);
           int x = 20, y = 50;
           posi = 100 * (posi-1);
           y = posi + y+50;
           canvas.drawPoint(x, y, paint);
       }
           paint.setColor(Color.rgb(255, 255, 255));
            paint.setStrokeWidth(5);


        float off=50,end=50;
        //canvas.drawLine(0,50,900,50,paint);

        for(int i=1;i<=10;i++)
        {

            canvas.drawLine(50,off,950,off,paint);
            off=off+100;
        }
        off=50;
        for(int i=1;i<=10;i++)
        {
            canvas.drawLine(off,50,off,950,paint);
            off=off+100;
        }



    }
    protected boolean checkRow( int row, int num )
    {
        for( int col = 0; col < 9; col++ )
            if( sudoku[row][col] == num && sudoku[row][col]!=0)
                return false ;

        return true ;
    }


    protected boolean checkCol( int col, int num )
    {
        for( int row = 0; row < 9; row++ )
            if( sudoku[row][col] == num && sudoku[row][col]!=0)
                return false ;

        return true ;
    }


    protected boolean checkBox( int row, int col, int num )
    {
        row = (row / 3) * 3 ;
        col = (col / 3) * 3 ;

        for( int r = 0; r < 3; r++ )
            for( int c = 0; c < 3; c++ )
                if( sudoku[row+r][col+c] == num && sudoku[row+r][col+c]!=0)
                    return false ;

        return true ;
    }
    public void solve( int row, int col ) throws Exception
    {

        if( row > 8 )
            throw new Exception( "Solution found" ) ;
        if( sudoku[row][col] != 0 )
            next( row, col ) ;
        else
        {
            for( int num = 1; num < 10; num++ )
            {
                if( checkRow(row,num) && checkCol(col,num) && checkBox(row,col,num) )
                {
                    empty[row][col]=1;
                    sudoku[row][col] = num ;
                    next( row, col ) ;
                }
            }
            sudoku[row][col] = 0 ;

        }
    }


    public void next( int row, int col ) throws Exception
    {
        if( col < 8 )
            solve( row, col + 1 ) ;
        else
            solve( row + 1, 0 ) ;
    }


}
