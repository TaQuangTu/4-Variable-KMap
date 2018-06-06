package helloworld.com.taquangtu132gmail.taquangtu.kmapwithintent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

class Solution
{
    private int K[][];
    private int L[][];
    private String result[];
    private int l;  //number of minterm in result
    //object initialization
    public Solution(int k[][])
    {
        result=new String[16];
        K=new int[4][4];
        L=new int[4][4];
        l=0;
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                K[i][j]=k[i][j];
                L[i][j]=0;
            }
        }
    }
    //check if this area has S = 2^n
    public boolean check2n(int x,int y,int x1,int y1,int s)
    {
        int sub1=x-x1;
        if(sub1<0) sub1=0-sub1;
        sub1++;
        int sub2=y-y1;
        if(sub2<0) sub2=0-sub2;
        sub2++;
        return (sub1*sub2)==s;
    }
    //check if this area includes Unplugged cells and rounded cells
    public boolean complexArea(int x,int y, int x1,int y1)
    {
        for(int i=x;i<=x1;i++)
        {
            for(int j=y;j<=y1;j++)
                if(L[i][j]==0) return true;
        }
        return false;
    }
    //check if this area can rounded
    public boolean check1andNotRounded(int x,int y, int x1,int y1)
    {
        for(int i=x;i<=x1;i++)
        {
            for(int j=y;j<=y1;j++)
                if(K[i][j]==0) return false;
        }
        return complexArea(x,y,x1,y1);
    }
    //check whether there is an exitence of any instance that better
    public boolean haveBetter(int x, int y, int x1, int y1)
    {
        if(L[x][y]==1)
        {
            if(y==y1)
            {
                if(y1<=2)
                {
                    if(L[x1][y1]==0&&L[x1][y1+1]==0&&K[x1][y1+1]!=0) return true;
                }
                if(y1>=1)
                {
                    if(L[x1][y1]==0&&L[x1][y1-1]==0&&K[x1][y1-1]!=0) return true;
                }
                if(x1<=2)
                {
                    if(L[x1][y1]==0&&L[x1+1][y1]==0&&K[x1+1][y1]!=0) return true;
                }
            }
            if(x==x1)
            {
                if(y1<=2)
                {
                    if(L[x1][y1]==0&&L[x1][y1+1]==0&&K[x1][y1+1]!=0) return true;
                }
                if(x1<=2)
                {
                    if(L[x1][y1]==0&&L[x1+1][y1]==0&&K[x1+1][y1]!=0) return true;
                }
            }

        }
        if(L[x1][y1]==1)
        {
            if(x==x1)
                if(x<=2)
                {
                    if(L[x+1][y]==0&&L[x][y]==0&&K[x+1][y]!=0) return true;
                }
        }
        if(L[x][y]==0&&L[x1][y1]==0)
        {
            if(x==x1)
            {
                if(x<=2&&y1<=2&&L[x+1][y]==0&&L[x][y1+1]==0&&K[x+1][y]!=0&&K[x][y1+1]!=0) return true;
            }
        }
        if(L[x][y]==1&&L[x1][y1]==0&&x==0&&x1==3&&y==y1)
        {
            if(y1+1<4)
            {
                if(L[x1][y1+1]==0&&K[x1][y1+1]==1) return true;
            }
            if(y1-1>-1)
            {
                if(L[x1][y1-1]==0&&K[x1][y1-1]==1) return true;
            }
        }
        if(L[x][y]==0&&L[x1][y1]==1&&y==0&&y1==3&&x1==x)
        {
            if(x+1<4)
            {
                if(L[x+1][y]==0&&K[x+1][y]==1) return true;
            }
            if(x-1>-1)
            {
                if(L[x-1][y]==0&&K[x-1][y]==1) return true;
            }
        }
        if(L[x][y]==1&&L[x1][y1]==0&&x==0&&x1==3&&y==y1)
        {
            if(y1+1<4)
            {
                if(L[x1][y1+1]==0&&K[x1][y1+1]==1) return true;
            }
            if(y1-1>-1)
            {
                if(L[x1][y1-1]==0&&K[x1][y1-1]==1) return true;
            }
        }
        if(L[x][y]==1&&L[x1][y1]==0&&y==0&&y1==3&&x1==x)
        {
            if(x1+1<4)
            {
                if(L[x1+1][y1]==0&&K[x1+1][y1]==1) return true;
            }
            if(x1-1>-1)
            {
                if(L[x1-1][y1]==0&&K[x1-1][y1]==1) return true;
            }
        }
        return false;
    }
    //get string every time we rounded
    public String getString(int x,int y,int x1,int y1, int s)
    {
        if(s==16) return "1";
        if(s==8)
        {
            if(x==0&&y==0)
            {
                if(x1==1&&y1==3) return "c";
                else return "a";
            }
            if(x==0&&y==1) return "B";
            if(x==1&&y==0) return "D";
            if(x==0&&y==2) return "A";
            if(x==2&&y==0) return "C";
        }
        if(s==4)
        {
            if(x==0&&y==0&&x1==x) return "cd";
            if(x==1&&y==0&&x1==x) return "cD";
            if(x==2&&y==0&&x1==x) return "CD";
            if(x==3&&y==0&&x1==x) return "Cd";

            if(x==0&&y==0&&y1==y) return "ab";
            if(x==0&&y==1&&y1==y) return "aB";
            if(x==0&&y==2&&y1==y) return "AB";
            if(x==0&&y==3&&y1==y) return "Ab";
            if(x==0&&y==0) return "ac";
            if(x==0&&y==1) return "Bc";
            if(x==0&&y==2) return "Ac";
            if(x==1&&y==0) return "aD";
            if(x==1&&y==1) return "BD";
            if(x==1&&y==2) return "AD";
            if(x==2&&y==0) return "aC";
            if(x==2&&y==1) return "BC";
            if(x==2&&y==2) return "AC";
        }
        if(s==2)
        {
            if(x==0&&y==0)
                if(x1==x) return "acd";
                else return "abc";
            if(x==0&&y==1)
                if(x1==x) return "Bcd";
                else return "aBc";
            if(x==0&&y==2)
                if(x1==x) return "Acd";
                else return "ABc"; //done
            if(x==0&&y==3)
                return "Abc";
            if(x==1&&y==0)
                if(x1==x) return "acD";
                else return "abD";//done
            if(x==1&&y==1)
                if(x1==x) return "BcD";
                else return "aBD";//done
            if(x==1&&y==2)
                if(x1==x) return "AcD";
                else return "ABD";
            if(x==1&&y==3)
                return "AbD";
            if(x==2&&y==0)
                if(x1==x) return "aCD";
                else return "abC";//done
            if(x==2&&y==1)
                if(x1==x) return "BCD";
                else return "aBC";
            if(x==2&&y==2)
                if(x1==x) return "ACD";
                else return "ABC";
            if(x==2&&y==3)
                return "AbC";
            //
            if(x==3&&y==0)
                return "aCd";

            if(x==3&&y==1)
                return "BCd";

            if(x==3&&y==2)
                return "ACd";
        }
        if(s==1)
        {
            if(x==0&&y==0) return "abcd";
            if(x==0&&y==1) return "aBcd";
            if(x==0&&y==2) return "ABcd";
            if(x==0&&y==3) return "Abcd";//done
            if(x==1&&y==0) return "abcD";
            if(x==1&&y==1) return "aBcD";//
            if(x==1&&y==2) return "ABcD";
            if(x==1&&y==3) return "AbcD";
            if(x==2&&y==0) return "abCD";
            if(x==2&&y==1) return "aBCD";
            if(x==2&&y==2) return "ABCD";
            if(x==2&&y==3) return "AbCD";
            if(x==3&&y==0) return "abCd";
            if(x==3&&y==1) return "aBCd";
            if(x==3&&y==2) return "ABCd";
            if(x==3&&y==3) return "AbCd";
        }
        return "";//avoid android studio warning.Actually, it does not need it
    }
    //
    public boolean check1(int i, int j)
    {
        return K[i][j]==1;
    }
    public void checkAll(int s)
    {
        if (s==0) return;
        if(s==1)
        {
            for(int i=0;i<4;i++)
            {
                for(int j=0;j<4;j++)
                {
                    if(check1(i,j))
                    {
                        if(L[i][j]==0)
                        {
                            result[l++]=getString(i,j,i,j,1);
                            L[i][j]=1;
                        }

                    }
                }
            }
            return;
        }
        else
        {
            if(s<=8)
            {
                if(s==8)
                {
                    if(check1andNotRounded(0,0,0,3)&&check1andNotRounded(3,0,3,3))
                    {
                        //round and mark as 1
                        result[l++]="d";
                        for(int i=0;i<4;i++)
                        {
                            L[0][i]=L[3][i]=1;
                        }
                    }
                    if(check1andNotRounded(0,0,3,0)&&check1andNotRounded(0,3,3,3))
                    {
                        //round and mark as 1
                        result[l++]="b";
                        for(int i=0;i<4;i++)
                        {
                            L[i][0]=L[i][3]=1;
                        }
                    }
                }
                else
                {
                    if(s==4)
                    {
                        if(K[0][0]==1&&K[1][0]==1&&K[0][3]==1&&K[1][3]==1&&(L[0][0]==1&&L[1][0]==1&&L[0][3]==1&&L[1][3]==1)==false)
                        {
                            //round and mark as 1
                            result[l++]="bc";
                            L[0][0]=L[1][0]=L[0][3]=L[1][3]=1;
                        }
                        if(K[1][0]==1&&K[2][0]==1&&K[1][3]==1&&K[2][3]==1&&(L[1][0]==1&&L[2][0]==1&&L[1][3]==1&&L[2][3]==1)==false)
                        {
                            //round and mark as 1
                            result[l++]="bD";
                            L[1][0]=L[2][0]=L[1][3]=L[2][3]=1;
                        }
                        if(K[2][0]==1&&K[3][0]==1&&K[2][3]==1&&K[3][3]==1&&(L[2][0]==1&&L[3][0]==1&&L[2][3]==1&&L[3][3]==1)==false)
                        {
                            //round and mark as 1
                            result[l++]="bC";
                            L[2][0]=L[3][0]=L[2][3]=L[3][3]=1;
                        }
                        if(K[0][0]==1&&K[3][0]==1&&K[0][3]==1&&K[3][3]==1&&(L[0][0]==1&&L[3][0]==1&&L[0][3]==1&&L[3][3]==1)==false)
                        {
                            //round and mark as 1
                            result[l++]="bd";
                            L[0][0]=L[3][0]=L[0][3]=L[3][3]=1;
                        }
                        if(K[0][0]==1&&K[0][1]==1&&K[3][0]==1&&K[3][1]==1&&(L[0][0]==1&&L[0][1]==1&&L[3][0]==1&&L[3][1]==1)==false)
                        {
                            //round and mark as 1
                            result[l++]="ad";
                            L[0][0]=L[0][1]=L[3][0]=L[3][1]=1;
                        }
                        if(K[0][1]==1&&K[0][2]==1&&K[3][1]==1&&K[3][2]==1&&(L[0][1]==1&&L[0][2]==1&&L[3][1]==1&&L[3][2]==1)==false)
                        {
                            //round and mark as 1
                            result[l++]="Bd";
                            L[0][1]=L[0][2]=L[3][1]=L[3][2]=1;
                        }
                        if(K[0][2]==1&&K[0][3]==1&&K[3][2]==1&&K[3][3]==1&&(L[0][2]==1&&L[0][3]==1&&L[3][2]==1&&L[3][3]==1)==false)
                        {
                            //round and mark as 1
                            result[l++]="Ad";
                            L[0][2]=L[0][3]=L[3][2]=L[3][3]=1;
                        }
                    }
                    else
                    if(s==2)
                    {
                        if(K[0][0]==1&&K[0][3]==1&&(L[0][0]==1&&L[0][3]==1)==false)
                        {
                            result[l++]="bcd";
                            L[0][0]=L[0][3]=1;
                        }
                        if(K[1][0]==1&&K[1][3]==1&&(L[1][0]==1&&L[1][3]==1)==false)
                        {
                            if(K[2][0]==1&&L[2][0]==0&&K[0][3]==1&&L[0][3]==0)
                            {
                                //do nothing
                            }
                            else
                            if(K[0][0]==1&&L[0][0]==0&&K[2][3]==1&&L[2][3]==0)
                            {
                                //do nothing
                            }
                            else
                            {
                                result[l++]="bcD";
                                L[1][0]=L[1][3]=1;
                            }
                        }
                        if(K[2][0]==1&&K[2][3]==1&&(L[2][0]==1&&L[2][3]==1)==false)
                        {
                            if(K[3][0]==1&&L[3][0]==0&&K[1][3]==1&&L[1][3]==0)
                            {
                                //do nothing
                            }
                            else
                            if(K[1][0]==1&&L[1][0]==0&&K[3][3]==1&&L[3][3]==0)
                            {
                                //do nothing
                            }
                            else
                            {
                                result[l++]="bCD";
                                L[2][0]=L[2][3]=1;
                            }

                        }
                        if(K[3][0]==1&&K[3][3]==1&&(L[3][0]==1&&L[3][3]==1)==false)
                        {
                            result[l++]="bCd";
                            L[3][0]=L[3][3]=1;
                        }
                        if(K[0][0]==1&&K[3][0]==1&&(L[0][0]==1&&L[3][0]==1)==false)
                        {
                            result[l++]="abd";
                            L[0][0]=L[3][0]=1;
                        }
                        if(K[0][1]==1&&K[3][1]==1&&(L[0][1]==1&&L[3][1]==1)==false)
                        {
                            if(K[0][0]==1&&L[0][0]==0&&K[3][2]==1&&L[3][2]==0)
                            {
                                //do nothing
                            }
                            else
                            if(K[0][2]==1&&L[0][2]==0&&K[3][0]==1&&L[3][0]==0)
                            {
                                //do nothing
                            }
                            else
                            {
                                result[l++]="aBd";
                                L[0][1]=L[3][1]=1;
                            }
                        }
                        if(K[0][2]==1&&K[3][2]==1&&(L[0][2]==1&&L[3][2]==1)==false)
                        {
                            if(K[0][1]==1&&L[0][1]==0&&K[3][3]==1&&L[3][3]==0)
                            {
                                //do nothing
                            }
                            else
                            if(K[0][3]==1&&L[0][3]==0&&K[3][1]==1&&L[3][1]==0)
                            {
                                //do nothing
                            }
                            else
                            {
                                result[l++]="ABd";
                                L[0][2]=L[3][2]=1;
                            }
                        }
                        if(K[0][3]==1&&K[3][3]==1&&(L[0][3]==1&&L[3][3]==1)==false)
                        {
                            result[l++]="Abd";
                            L[0][3]=L[3][3]=1;
                        }
                    }
                }
            }
            for(int x=0;x<4;x++)
                for(int y=0;y<4;y++)
                {
                    for(int x1=x;x1<4;x1++)
                        for(int y1=y;y1<4;y1++)
                            if(check2n(x,y,x1,y1,s))
                            {
                                if(check1andNotRounded(x,y,x1,y1))
                                {
                                    //them kết quả là một chuỗi gì đó vào result( thêm rồi phần sau sẽ xử lí sau)
                                    //sau đó đánh dấu tất cả những ô đó là đã khoanh
                                    if(s==2)
                                    {
                                        if(haveBetter(x,y,x1,y1)) continue;
                                        else
                                        {
                                            result[l++]=getString(x,y,x1,y1,s);
                                            for(int i=x;i<=x1;i++)
                                                for(int j=y;j<=y1;j++)
                                                {
                                                    L[i][j]=1;
                                                }
                                        }
                                    }
                                    else
                                    {
                                        result[l++]=getString(x,y,x1,y1,s);
                                        for(int i=x;i<=x1;i++)
                                            for(int j=y;j<=y1;j++)
                                            {
                                                L[i][j]=1;
                                            }
                                    }
                                }
                            }
                }
            checkAll(s/2);
        }
    }
    public String changeString(String s)
    {
        String tempString="";
        int length=0;
        for(int i=0;i<s.length();i++)
        {
            if(((int)s.charAt(i))>96)
            {
                tempString+=(char)((int)s.charAt(i)-32);
                tempString+="'";
            }
            else tempString+=s.charAt(i);
        }
        return tempString;
    }
    public String showResult()
    {
        String t="";
        for(int i=0;i<l-1;i++)
        {
            t+=changeString(result[i]);
            t+="+";
        }
        if(l-1>=0) t+=changeString(result[l-1]);
        //col 1
        if(t=="A'C'+A'B'+BC'") return "A'B'+BC'";
        if(t=="A'B'+A'D+BD") return "A'B'+BD";
        if(t=="A'B'+A'C+BC") return "A'B'+BC";
        if(t=="A'D'+BD'+A'B'") return "BD'+A'B'";
        if(t=="B'C'+AB'+AC'") return "AB'+AC'";
        if(t=="B'D+A'B'+AD") return "A'B'+AD";
        if(t=="B'C+A'B'+AC") return "A'B'+AC";
        if(t=="B'D'+AD'+A'B'") return "AD'+A'B'";
//col 2
        if(t=="BC'+AB'+AC'") return "AB'+AC'";
        if(t=="A'B+BD+AD") return "A'B+AD";
        if(t=="A'B+BC+AC") return "A'B+AC";
        if(t=="BD'+AD'+A'B") return "AD'+A'B";
        if(t=="B'C'+A'C'+A'B") return "B'C'+A'B";
        if(t=="B'D'+A'D'+A'B") return "B'D'+A'B";
//col 3
        if(t=="B'C'+AC'+AB") return "AC'+AB";
        if(t=="B'D'+AD'+AB") return "B'D'+AB";
        if(t=="A'C'+BC'+AB") return "A'C'+AB";
        if(t=="A'D'+BD'+AB") return "A'D'+AB";
//col 4
        if(t=="BC'+AC'+AB'") return "BC'+AB'";
        if(t=="BD'+AD'+AB'") return "BD'+AB'";
        if(t=="B'C'+A'C'+AB'") return "A'C'+AB'";
        if(t=="B'D+AB'+A'D") return "AB'+A'D";
        if(t=="B'C+AB'+A'C") return "AB'+A'C";
        if(t=="B'D'+A'D'+AB'") return "A'D'+AB'";//
        return t;
    }
};
//=================================================
public class KmapActivity extends AppCompatActivity {
    private TextView tv_result;
    private ToggleButton c0;
    private ToggleButton c1;
    private ToggleButton c2;
    private ToggleButton c3;
    private ToggleButton c4;
    private ToggleButton c5;
    private ToggleButton c6;
    private ToggleButton c7;
    private ToggleButton c8;
    private ToggleButton c9;
    private ToggleButton c10;
    private ToggleButton c11;
    private ToggleButton c12;
    private ToggleButton c13;
    private ToggleButton c14;
    private ToggleButton c15;
    private ImageButton b_result;
    private ImageButton b_reset;
    private void conectViews()
    {
        b_reset=(ImageButton) findViewById(R.id.bt_reset);
        tv_result=(TextView) findViewById(R.id.textViewResult);
        c0=(ToggleButton) findViewById(R.id.toggleButton0);
        c1=(ToggleButton) findViewById(R.id.toggleButton1);
        c2=(ToggleButton) findViewById(R.id.toggleButton2);
        c3=(ToggleButton) findViewById(R.id.toggleButton3);
        c4=(ToggleButton) findViewById(R.id.toggleButton4);
        c5=(ToggleButton) findViewById(R.id.toggleButton5);
        c6=(ToggleButton) findViewById(R.id.toggleButton6);
        c7=(ToggleButton) findViewById(R.id.toggleButton7);
        c8=(ToggleButton) findViewById(R.id.toggleButton8);
        c9=(ToggleButton) findViewById(R.id.toggleButton9);
        c10=(ToggleButton) findViewById(R.id.toggleButton10);
        c11=(ToggleButton) findViewById(R.id.toggleButton11);
        c12=(ToggleButton) findViewById(R.id.toggleButton12);
        c13=(ToggleButton) findViewById(R.id.toggleButton13);
        c14=(ToggleButton) findViewById(R.id.toggleButton14);
        c15=(ToggleButton) findViewById(R.id.toggleButton15);
        b_result = (ImageButton) findViewById(R.id.buttonResult);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kmap);
        setContentView(R.layout.activity_kmap);
        //Anh xa cac View
        conectViews();
        //thuc thi khi Button duoc nhan
        b_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KmapActivity.this.c0.setChecked(false);
                KmapActivity.this.c1.setChecked(false);
                KmapActivity.this.c2.setChecked(false);
                KmapActivity.this.c3.setChecked(false);
                KmapActivity.this.c4.setChecked(false);
                KmapActivity.this.c5.setChecked(false);
                KmapActivity.this.c6.setChecked(false);
                KmapActivity.this.c7.setChecked(false);
                KmapActivity.this.c8.setChecked(false);
                KmapActivity.this.c9.setChecked(false);
                KmapActivity.this.c10.setChecked(false);
                KmapActivity.this.c11.setChecked(false);
                KmapActivity.this.c12.setChecked(false);
                KmapActivity.this.c13.setChecked(false);
                KmapActivity.this.c14.setChecked(false);
                KmapActivity.this.c15.setChecked(false);
            }
        });
        b_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int A[][]=new int[4][4];
                A[0][0]=Integer.parseInt(c0.getText().toString());
                A[0][1]=Integer.parseInt(c4.getText().toString());
                A[0][2]=Integer.parseInt(c12.getText().toString());
                A[0][3]=Integer.parseInt(c8.getText().toString());
                A[1][0]=Integer.parseInt(c1.getText().toString());
                A[1][1]=Integer.parseInt(c5.getText().toString());
                A[1][2]=Integer.parseInt(c13.getText().toString());
                A[1][3]=Integer.parseInt(c9.getText().toString());
                A[2][0]=Integer.parseInt(c3.getText().toString());
                A[2][1]=Integer.parseInt(c7.getText().toString());
                A[2][2]=Integer.parseInt(c15.getText().toString());
                A[2][3]=Integer.parseInt(c11.getText().toString());
                A[3][0]=Integer.parseInt(c2.getText().toString());
                A[3][1]=Integer.parseInt(c6.getText().toString());
                A[3][2]=Integer.parseInt(c14.getText().toString());
                A[3][3]=Integer.parseInt(c10.getText().toString());
                Solution a=new Solution(A);
                a.checkAll(16);
                tv_result.setText(a.showResult());
            }
        });
    }
}
