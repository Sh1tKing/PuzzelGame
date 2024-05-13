package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    int [][]data=new int[4][4];
    int [][]win={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
    int x=0,y=0,step=0;
    String path="image\\girl\\girl1\\";

    //对象条目
    JMenuItem restartItem=new JMenuItem("重新开始");
    JMenuItem terminalItem=new JMenuItem("关闭游戏");
    JMenuItem accountItem=new JMenuItem("github地址");
    JMenuItem girlItem=new JMenuItem("女孩");
    JMenuItem animalItem=new JMenuItem("动物");
    JMenuItem sportItem=new JMenuItem("运动");


    //游戏主界面
    public GameJFrame(){
        initJFrame();
        initJMenuBar();
        initData();
        initImage();
        this.setVisible(true);
    }

    private void initData() {
        int []tempdata={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        for (int i=0;i<=15;i++){
            Random r=new Random();
            int index=r.nextInt(15);
            int temp=tempdata[index];
            tempdata[index]=tempdata[i];
            tempdata[i]=temp;
        }
        for(int i=0;i<tempdata.length;i++){
            data[i/4][i%4]=tempdata[i];
        }
    }

    private void initImage() {//初始化图片
        this.getContentPane().removeAll();
        JLabel steplabel=new JLabel("步数："+step);
        steplabel.setForeground(Color.white);
        steplabel.setBounds(30,30,100,50);
        this.getContentPane().add(steplabel);
        if(judgeWin()){
            ImageIcon victor=new ImageIcon("image\\win.png");
            JLabel jLabel = new JLabel(victor);
            jLabel.setBounds(200,60,197,73);
            this.getContentPane().add(jLabel);
        }
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++) {
                ImageIcon icon = new ImageIcon(path + data[i][j] + ".jpg");
                if(data[i][j]==0){
                    x=i;
                    y=j;
                }
                JLabel jLabel = new JLabel(icon);
                jLabel.setBounds(j*105+80, i*105+100, 105, 105);
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //this.add(jLabel);
                this.getContentPane().add(jLabel);
            }
        }
        ImageIcon icon=new ImageIcon("image\\background1.png");
        JLabel jLabel=new JLabel(icon);
        jLabel.setBounds(0,0,700,700);
        this.getContentPane().add(jLabel);

        this.getContentPane().repaint();

    }
    private void initJMenuBar() {
        //初始化菜单
        JMenuBar jMenuBar=new JMenuBar();
        //菜单对象
        JMenu functionJMenu=new JMenu("功能");
        JMenu aboutJMenu=new JMenu("关于作者");

        JMenu changephotomenu=new JMenu("更换图片");

        restartItem.addActionListener(this);
        terminalItem.addActionListener(this);
        accountItem.addActionListener(this);
        girlItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);

        functionJMenu.add(changephotomenu);
        changephotomenu.add(girlItem);
        changephotomenu.add(animalItem);
        changephotomenu.add(sportItem);

        functionJMenu.add(restartItem);
        functionJMenu.add(terminalItem);
        aboutJMenu.add(accountItem);

        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setSize(603,680);
        this.setTitle("拼图小游戏V1.0");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.addKeyListener(this);
    }
    public boolean judgeWin(){
       for(int i=0;i<4;i++){
           for(int j=0;j<4;j++){
               if(data[i][j]!=win[i][j]) return false;
           }
       }
       return true;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(judgeWin()) return;
        int code=e.getKeyCode();
        if(code==65){
            this.getContentPane().removeAll();
            ImageIcon icon=new ImageIcon(path+"all.jpg");
            JLabel jLabel=new JLabel(icon);
            jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
            jLabel.setBounds(80,100,420,420);
            ImageIcon icon1=new ImageIcon("image\\background1.png");
            JLabel jLabel1=new JLabel(icon1);
            jLabel1.setBounds(0,0,700,700);
            this.getContentPane().add(jLabel);
            this.add(jLabel);
            this.add(jLabel1);
            this.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(judgeWin()) return;
        int code=e.getKeyCode();
        if(code==87){
            for(int i=0;i<=15;i++){
                data[i/4][i%4]=i+1;
            }
            data[3][3]=0;
            initImage();
        }else if(code==65){
            initImage();
        }else if(code==38){
            if(x+1<=3 && x+1>=0) {
                data[x][y]=data[x+1][y];
                data[x+1][y]=0;
                step++;
                initImage();
            }
        }else if(code==40){
            if(x-1<=3 && x-1>=0) {
                data[x][y]=data[x-1][y];
                data[x-1][y]=0;
                step++;
                initImage();
            }
        }else if(code==37){
            if(y+1<=3 && y+1>=0) {
                data[x][y]=data[x][y+1];
                data[x][y+1]=0;
                step++;
                initImage();
            }
        }else if(code==39){
            if(y-1<=3 && y-1>=0) {
                data[x][y]=data[x][y-1];
                data[x][y-1]=0;
                step++;
                initImage();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if(obj==restartItem){
            step=0;
            initData();
            initImage();
        }else if(obj==terminalItem){
            System.exit(0);
        }else if(obj==accountItem){
            JDialog jDialog=new JDialog();
            JLabel jLabel=new JLabel("https://github.com/Sh1tKing/PuzzleGame.git");
            jLabel.setBounds(0,0,200,50);
            jDialog.setSize(280,400);
            jDialog.getContentPane().add(jLabel);

            jDialog.setLocationRelativeTo(null);
            jDialog.setAlwaysOnTop(true);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        }else if(obj==girlItem){
            Random r=new Random();
            int seed=r.nextInt(12)+1;
            path="image\\girl\\girl"+seed+"\\";
            initData();
            initImage();
        }else if(obj==sportItem){
            Random r=new Random();
            int seed=r.nextInt(9)+1;
            path="image\\sport\\sport"+seed+"\\";
            initData();
            initImage();
        }else if(obj==animalItem){
            Random r=new Random();
            int seed=r.nextInt(7)+1;
            path="image\\animal\\animal"+seed+"\\";
            initData();
            initImage();
        }
    }
}
